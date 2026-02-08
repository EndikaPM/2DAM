package com.example.avtividad_propuesta_11_16_gps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    TextView visor;
    LocationManager miGestorDeLocalizaciones;
    private static final int CODIGO_PERMISOS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        visor = findViewById(R.id.visor);

        miGestorDeLocalizaciones = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    CODIGO_PERMISOS
            );
        } else {
            iniciarLocalizacion();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CODIGO_PERMISOS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                iniciarLocalizacion();
            } else {
                visor.setText("Permiso de ubicación denegado");
            }
        }
    }

    private void iniciarLocalizacion() {

        if (!miGestorDeLocalizaciones.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            visor.setText("GPS desactivado. Por favor actívalo en Configuración");
            return;
        }

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            miGestorDeLocalizaciones.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    5000,   // 5 segundos
                    5,      // 5 metros
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(@NonNull Location location) {
                            runOnUiThread(() -> {
                                String localizacion =
                                        "Latitud: " + location.getLatitude() + "\n" +
                                                "Longitud: " + location.getLongitude() + "\n" +
                                                "Precisión: " + location.getAccuracy() + "m";
                                visor.setText(localizacion);
                            });
                        }
                    }
            );
        }
    }
}