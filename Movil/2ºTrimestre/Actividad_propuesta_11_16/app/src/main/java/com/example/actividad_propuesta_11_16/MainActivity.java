package com.example.actividad_propuesta_11_16;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView visor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        visor = findViewById(R.id.visor);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.
                checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            //solicitamos permisos al usuario
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            iniciarLocalizacion();
        }

    }

    private void iniciarLocalizacion() {
        LocationManager miGestorDeLocalizaciones = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            miGestorDeLocalizaciones.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    5000,
                    5,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(@NonNull Location location) {
                            runOnUiThread(() -> {
                                String localizacion = "Latitud: " + location.getLatitude() + "\n" +
                                        "Longitud: " + location.getLongitude();
                                visor.setText(localizacion);
                            });
                        }
                    }
            );
        }
    }
}