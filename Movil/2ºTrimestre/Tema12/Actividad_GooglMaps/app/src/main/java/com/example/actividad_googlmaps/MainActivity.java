package com.example.actividad_googlmaps;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.actividad_googlmaps.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
//CLAVE API: AIzaSyDJy5yOuvhlYf7PKIf8VAYu9Ce9D_S-b3g

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // CLAVE DE LA API: AIzaSyBd3Zs13hjMR0jUsiWQoJCHTP8kqoOq4vA

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtener el fragmento del mapa
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        /*mMap = googleMap;

        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(
                new MarkerOptions()
                        .position(sydney)
                        .title("Marker in Sydney")
        );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10f));*/
        mMap = googleMap;

        // 1. Cambiamos las coordenadas a Madrid
        LatLng madrid = new LatLng(40.4168, -3.7038);

        // 2. Añadimos el marcador
        mMap.addMarker(
                new MarkerOptions()
                        .position(madrid)
                        .title("Marcador en Madrid")
        );

        // 3. Movemos la cámara con un ZOOM (ejemplo: 15f es nivel calle)
        // Si usas solo newLatLng, verás el mapa muy lejos.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(madrid, 15f));
    }
}