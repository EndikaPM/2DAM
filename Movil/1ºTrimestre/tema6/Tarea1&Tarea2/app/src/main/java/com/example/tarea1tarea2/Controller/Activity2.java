package com.example.tarea1tarea2.Controller;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tarea1tarea2.Model.Libro;
import com.example.tarea1tarea2.R;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {
    private ListView lista;
    private RatingBar valora;
    private RadioButton radioButton_pulsado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        lista = findViewById(R.id.principa_lista_view);

        ArrayList<Libro> datos = new ArrayList<>();
        datos.add(new Libro(R.drawable.ima1, "La mano izquierda de dios", "Thomas Cale ha sido criado en un monasterio donde se entrena a niños para convertirse en guerreros sin piedad. Cuando escapa, descubre un mundo que nunca imaginó y se ve envuelto en una guerra que podría cambiar el destino de la humanidad.", "fecha publicación:\n2010",true));

        datos.add(new Libro(R.drawable.ima2, "Las cuatro Postrimeras", "Segunda parte de la trilogía iniciada con 'La mano izquierda de Dios'. Thomas Cale continúa su viaje enfrentándose a enemigos poderosos mientras intenta comprender su destino y el papel que debe jugar en una guerra apocalíptica.","fecha publicación:\n2011" ,false));

        datos.add(new Libro(R.drawable.ima3, "Batir de alas", "Última entrega de la trilogía de Paul Hoffman. Thomas Cale debe enfrentar su destino final en una batalla épica que determinará el futuro del mundo. Una conclusión intensa y emotiva de una saga oscura y fascinante.","fecha publicación:\n2013" ,false));

        datos.add(new Libro(R.drawable.ima4, "La niña del bosque", "Novela independiente de Paul Hoffman ambientada en un mundo de fantasía donde una joven con habilidades especiales debe sobrevivir en un bosque misterioso mientras descubre secretos sobre su origen y su poder.","fecha publicación:\n2015" ,false));

        datos.add(new Libro(R.drawable.ima5, "La sombra del viento", "Barcelona, 1945. Daniel Sempere descubre un libro maldito que cambiará su vida para siempre. Una historia de amor, misterio y tragedias del pasado en la Barcelona de posguerra. Primera novela de la saga del Cementerio de los Libros Olvidados de Carlos Ruiz Zafón.","fecha publicación:\n2001" ,false));

        datos.add(new Libro(R.drawable.ima6, "El corazón con que vivo", "Novela de Erich Maria Remarque que explora temas de amor, pérdida y supervivencia en la Europa de posguerra. Una historia profundamente emotiva sobre la búsqueda de significado y conexión humana en tiempos difíciles.","fecha publicación:\n1952" ,false));

        datos.add(new Libro(R.drawable.ima7, "La Odisea", "Poema épico griego atribuido a Homero. Narra las aventuras de Odiseo en su viaje de regreso a Ítaca tras la Guerra de Troya. Una obra fundamental de la literatura universal que ha influenciado la cultura occidental durante milenios.","fecha publicación: Siglo\nVII a.c." ,false));

        lista.setAdapter(new Adaptador(this, R.layout.entrada, datos) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null){
                    TextView texto_superior_entrada = (TextView) view.findViewById(R.id.texto_titulo);
                    TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.texto_datos);
                    ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imagen);
                    RadioButton miRadioButon = (RadioButton) view.findViewById(R.id.boton);
                    RatingBar rankin = (RatingBar) view.findViewById(R.id.valoracion);
                    TextView fecha = (TextView) view.findViewById(R.id.fecha);

                    texto_superior_entrada.setText(((Libro) entrada).getTitulo());
                    texto_inferior_entrada.setText(((Libro) entrada).getContenido());
                    imagen_entrada.setImageResource(((Libro) entrada).getImagenId());
                    fecha.setText(((Libro) entrada).getFecha());
                    


                    miRadioButon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(radioButton_pulsado != null) radioButton_pulsado.setChecked(false);
                            radioButton_pulsado = (RadioButton) v;

                            //Lo he hecho pero con trampas
                        }
                    });
                }
            }
        });
        //Este nunca se ejecuta?????
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Libro elegido = (Libro) parent.getItemAtPosition(position);
                CharSequence textoElegido = "Seleccionado: " + elegido.getContenido();
                //texto.setText(textoElegido);//preguntar a Eva porque en su diapositiva no se usa así
            }
        });
    }
}