package com.example.gestionhoteles.Controller;

import com.example.gestionhoteles.Main;
import com.example.gestionhoteles.Model.Repositorio.ExeptionReserva;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HabitacionesViewController {
    @FXML
    private Label tituDoble;
    @FXML
    private Label reservaTable;

    @FXML
    private ImageView imagenDoble;
    @FXML
    private ImageView imagenDobleIndividual;
    @FXML
    private ImageView imagenJuniorSuite;
    @FXML
    private ImageView imagenSuite;
    @FXML
    private ProgressIndicator progressDoble;
    @FXML
    private ProgressIndicator progressDobleIndividual;
    @FXML
    private ProgressIndicator progresJuniorSuit;
    @FXML
    private ProgressIndicator progressSuit;

    private Main mainapp;

    // Índices para cada carrusel
    private int indiceDoble = 0;
    private int indiceDobleIndividual = 0;
    private int indiceJuniorSuit = 0;
    private int indiceSuite = 0;

    // Usa la misma ruta base que el FXML (@../images/)
    private final String[] imagenesDoble = {
            "/images/habitacionesDoble.jpg",
            "/images/habitacionesDoble2.jpg",
            "/images/habitacionesDoble3.jpg"
    };

    private final String[] imagenesDobleIndividual = {
            "/images/habitacionDobleNoIndividual.jpg",
            "/images/habitacionDobleNoIndividual2.jpg",
            "/images/habitacionDobleNoIndividual3.jpeg"
    };

    private final String[] imagenesJuniorSuite = {
            "/images/juniorSuite.jpg",
            "/images/juniorSuite2.jpg",
            "/images/juniorSuite3.jpg"
    };

    private final String[] imagenesSuite = {
            "/images/suite.jpeg",
            "/images/suite2.jpg",
            "/images/suite3.jpg"
    };

    @FXML
    public void initialize() {
        // Cargar las primeras imágenes
        cargarImagen(imagenDoble, imagenesDoble[0]);
        cargarImagen(imagenDobleIndividual, imagenesDobleIndividual[0]);
        cargarImagen(imagenJuniorSuite, imagenesJuniorSuite[0]);
        cargarImagen(imagenSuite, imagenesSuite[0]);
    }

    public void setMain(Main mainapp) throws ExeptionReserva {
        this.mainapp = mainapp;
        cargarDatosHabitaciones();
    }

    private void cargarDatosHabitaciones() throws ExeptionReserva {
        Double[] room = mainapp.getReserva().porcentajeReserRoom();

        progressDoble.setProgress(room[0]);
        progressDobleIndividual.setProgress(room[1]);
        progresJuniorSuit.setProgress(room[2]);
        progressSuit.setProgress(room[3]);
    }

    // Método auxiliar para cargar imagen con manejo de errores
    private void cargarImagen(ImageView imageView, String ruta) {
        try {
            // Primero intenta cargar desde la misma ubicación que el controlador
            Image imagen = new Image(getClass().getResource(ruta).toExternalForm());
            imageView.setImage(imagen);
        } catch (Exception e) {
            System.err.println("Error al cargar imagen: " + ruta);
            System.err.println("Intentando ruta alternativa...");
            try {
                // Intenta ruta absoluta desde resources
                Image imagen = new Image(getClass().getResource("/" + ruta).toExternalForm());
                imageView.setImage(imagen);
            } catch (Exception e2) {
                System.err.println("Error también con ruta alternativa");
                e.printStackTrace();
            }
        }
    }

    // Métodos para Habitación Doble
    public void backdoble(ActionEvent actionEvent) {
        indiceDoble--;
        if (indiceDoble < 0) {
            indiceDoble = imagenesDoble.length - 1;
        }
        cargarImagen(imagenDoble, imagenesDoble[indiceDoble]);
    }

    public void nextDoble(ActionEvent actionEvent) {
        indiceDoble++;
        if (indiceDoble >= imagenesDoble.length) {
            indiceDoble = 0;
        }
        cargarImagen(imagenDoble, imagenesDoble[indiceDoble]);
    }

    // Métodos para Habitación Doble Individual
    public void backDobleInvidial(ActionEvent actionEvent) {
        indiceDobleIndividual--;
        if (indiceDobleIndividual < 0) {
            indiceDobleIndividual = imagenesDobleIndividual.length - 1;
        }
        cargarImagen(imagenDobleIndividual, imagenesDobleIndividual[indiceDobleIndividual]);
    }

    public void nextDobleIndi(ActionEvent actionEvent) {
        indiceDobleIndividual++;
        if (indiceDobleIndividual >= imagenesDobleIndividual.length) {
            indiceDobleIndividual = 0;
        }
        cargarImagen(imagenDobleIndividual, imagenesDobleIndividual[indiceDobleIndividual]);
    }

    // Métodos para Junior Suite
    public void backJuniorSuit(ActionEvent actionEvent) {
        indiceJuniorSuit--;
        if (indiceJuniorSuit < 0) {
            indiceJuniorSuit = imagenesJuniorSuite.length - 1;
        }
        cargarImagen(imagenJuniorSuite, imagenesJuniorSuite[indiceJuniorSuit]);
    }

    public void nextJuniorSuit(ActionEvent actionEvent) {
        indiceJuniorSuit++;
        if (indiceJuniorSuit >= imagenesJuniorSuite.length) {
            indiceJuniorSuit = 0;
        }
        cargarImagen(imagenJuniorSuite, imagenesJuniorSuite[indiceJuniorSuit]);
    }

    // Métodos para Suite
    public void backSuit(ActionEvent actionEvent) {
        indiceSuite--;
        if (indiceSuite < 0) {
            indiceSuite = imagenesSuite.length - 1;
        }
        cargarImagen(imagenSuite, imagenesSuite[indiceSuite]);
    }

    public void nextSuite(ActionEvent actionEvent) {
        indiceSuite++;
        if (indiceSuite >= imagenesSuite.length) {
            indiceSuite = 0;
        }
        cargarImagen(imagenSuite, imagenesSuite[indiceSuite]);
    }
}