package com.example.preparacion_inclinacion;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // -------------------------------------------------------------------------
    // CONSTANTES
    // -------------------------------------------------------------------------

    // Umbral para detectar sacudida (m/s²). Si la aceleración total supera
    // este valor por encima de la gravedad, se considera una sacudida.
    private static final double SHAKE_THRESHOLD = 12.0;

    // Factor de escala: cuántos píxeles se mueve la imagen por cada unidad
    // de aceleración (m/s²). Auméntalo para un movimiento más brusco.
    private static final float SENSIBILIDAD = 15f;

    // Desplazamiento máximo permitido en píxeles desde el centro.
    // Evita que la imagen salga completamente de la pantalla.
    private static final float MAX_DESPLAZAMIENTO = 400f;

    // Factor de suavizado del filtro de paso bajo (entre 0.0 y 1.0).
    // Valores bajos → movimiento más suave pero con más retraso.
    // Valores altos → movimiento más reactivo pero más tembloroso.
    private static final double ALPHA = 0.15;

    // -------------------------------------------------------------------------
    // VARIABLES DE SENSOR
    // -------------------------------------------------------------------------

    private SensorManager sensorManager; // Gestor de sensores del sistema
    private Sensor accelerometro;        // Referencia al sensor acelerómetro

    // Valores crudos del acelerómetro (en m/s²)
    double x, y, z;

    // Aceleración total (módulo del vector) y su máximo histórico
    double a, amax;

    // Valor de la gravedad terrestre estándar (~9.8 m/s²)
    double gravedad = SensorManager.STANDARD_GRAVITY;

    // Valores filtrados con el filtro de paso bajo para suavizar el movimiento
    private double xFiltrada = 0;
    private double yFiltrada = 0;

    // -------------------------------------------------------------------------
    // VISTAS
    // -------------------------------------------------------------------------

    TextView txt; // TextView para mostrar los datos del sensor en pantalla
    ImageView muneco; // ImageView que se desplazará según la inclinación

    // -------------------------------------------------------------------------
    // HANDLER PARA ACTUALIZAR LA UI PERIÓDICAMENTE
    // -------------------------------------------------------------------------

    // Handler asociado al hilo principal (UI thread)
    private Handler handler = new Handler(Looper.getMainLooper());

    int contador = 0; // Contador de actualizaciones de la UI

    // Runnable que se ejecuta cada 100ms para refrescar el TextView con los
    // datos actuales del sensor. Se reprograma a sí mismo con postDelayed.
    private final Runnable updater = new Runnable() {
        @Override
        public void run() {
            contador++;

            // Actualizamos el texto con los valores más recientes
            txt.setText(
                    "X: " + x +
                            "\nY: " + y +
                            "\nZ: " + z +
                            "\nA: " + a +
                            "\nMAX: " + amax +
                            "\nGravedad: " + gravedad +
                            "\nCONTADOR: " + contador
            );

            // Volvemos a programar este Runnable para que se ejecute
            // de nuevo dentro de 100 milisegundos
            handler.postDelayed(this, 100);
        }
    };

    // -------------------------------------------------------------------------
    // CICLO DE VIDA DE LA ACTIVIDAD
    // -------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enlazamos las vistas con sus IDs del layout XML
        txt = (TextView) findViewById(R.id.txt);
        muneco = (ImageView) findViewById(R.id.muneco);

        // Obtenemos el servicio de sensores del sistema
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Obtenemos la referencia al acelerómetro por defecto del dispositivo
        // Devuelve null si el dispositivo no tiene acelerómetro
        accelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Registramos el listener para empezar a recibir datos del sensor.
        // SENSOR_DELAY_GAME ofrece una frecuencia de muestreo alta (~50Hz),
        // adecuada para movimientos en tiempo real.
        sensorManager.registerListener(this, accelerometro, SensorManager.SENSOR_DELAY_GAME);

        // Iniciamos el bucle de actualización de la UI
        handler.post(updater);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Desregistramos el listener cuando la app pasa a segundo plano.
        // Esto es IMPORTANTE para no consumir batería innecesariamente.
        sensorManager.unregisterListener(this);

        // Detenemos el bucle de actualización de la UI
        handler.removeCallbacks(updater);
    }

    // -------------------------------------------------------------------------
    // CALLBACKS DEL SENSOR
    // -------------------------------------------------------------------------

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Este método se llama cada vez que el sensor tiene un nuevo valor.
        // Se ejecuta en el hilo principal automáticamente.

        // Leemos los tres ejes del acelerómetro (en m/s²):
        // x: eje horizontal (+ = izquierda, - = derecha)
        // y: eje vertical   (+ = abajo,     - = arriba)
        // z: eje profundidad(+ = hacia ti,  - = alejándose)
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];

        // Calculamos el módulo total de la aceleración (teorema de Pitágoras en 3D)
        a = Math.sqrt(x * x + y * y + z * z);

        // Guardamos el máximo histórico de aceleración
        if (a > amax) amax = a;

        // --- Detección de sacudida ---
        // Si la diferencia entre la aceleración total y la gravedad supera
        // el umbral, el usuario está agitando el dispositivo
        if (Math.abs(a - gravedad) > SHAKE_THRESHOLD) {
            Log.d("SENSOR", "¡SACUDIDA!");
        }

        // --- Detección de inclinación (solo para Log) ---
        if (x > 3)       Log.d("SENSOR", "Inclinación IZQUIERDA");
        else if (x < -3) Log.d("SENSOR", "Inclinación DERECHA");

        if (y > 3)       Log.d("SENSOR", "Inclinación ABAJO");
        else if (y < -3) Log.d("SENSOR", "Inclinación ARRIBA");

        // Movemos la imagen en base a la inclinación actual
        moverImagen();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Se llama cuando cambia la precisión del sensor.
        // No necesitamos hacer nada aquí para este ejercicio.
    }

    // -------------------------------------------------------------------------
    // LÓGICA DE MOVIMIENTO DE LA IMAGEN
    // -------------------------------------------------------------------------

    private void moverImagen() {

        // --- Paso 1: Filtro de paso bajo ---
        // Suavizamos los valores del sensor mezclando el valor nuevo (ALPHA)
        // con el valor filtrado anterior (1 - ALPHA).
        // Esto elimina los picos bruscos y hace el movimiento más fluido.
        //xFiltrada = ALPHA * x + (1 - ALPHA) * xFiltrada;
        //yFiltrada = ALPHA * y + (1 - ALPHA) * yFiltrada;

        // --- Paso 2: Convertir inclinación a píxeles ---
        // Multiplicamos el valor filtrado por la sensibilidad para obtener
        // cuántos píxeles debe desplazarse la imagen.
        //
        // Negamos X porque el eje X del acelerómetro es opuesto al eje X
        // de la pantalla: inclinar a la izquierda da X positivo, pero
        // queremos que la imagen se mueva a la derecha (X positivo en pantalla).
        //
        // Y no se niega porque inclinar el móvil hacia ti (Y positivo en
        // acelerómetro) debe mover la imagen hacia abajo (Y positivo en pantalla).
        float desplazamientoX = (float) (-x * SENSIBILIDAD);
        float desplazamientoY = (float) (y * SENSIBILIDAD);

        // --- Paso 3: Limitar el desplazamiento ---
        // Usamos Math.max y Math.min para "recortar" el valor entre
        // -MAX_DESPLAZAMIENTO y +MAX_DESPLAZAMIENTO.
        // Así la imagen nunca se irá completamente fuera de la pantalla.
        desplazamientoX = Math.max(-MAX_DESPLAZAMIENTO, Math.min(MAX_DESPLAZAMIENTO, desplazamientoX));
        desplazamientoY = Math.max(-MAX_DESPLAZAMIENTO, Math.min(MAX_DESPLAZAMIENTO, desplazamientoY));

        // --- Paso 4: Aplicar la traslación a la ImageView ---
        // setTranslationX/Y desplaza la vista desde su posición original
        // definida en el XML, sin afectar al layout de otras vistas.
        muneco.setTranslationX(desplazamientoX);
        muneco.setTranslationY(desplazamientoY);
    }
}