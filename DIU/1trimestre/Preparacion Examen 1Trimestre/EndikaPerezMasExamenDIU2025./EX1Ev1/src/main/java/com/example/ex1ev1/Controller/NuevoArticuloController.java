package com.example.ex1ev1.Controller;

import com.example.ex1ev1.Model.Articulo;
import com.example.ex1ev1.Model.Catalogo;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NuevoArticuloController {
    private Catalogo catalogo;

    @FXML
    private Label NumProducDispoAdd;
    @FXML
    private TextField textFielNombre;
    @FXML
    private TextField textFielDescrip;
    @FXML
    private TextField textFielPrecio;
    @FXML
    private TextField textFielStock;

    private Stage dialogStage;
    private Articulo articulo;
    private IntegerProperty numArticulos=new SimpleIntegerProperty();
    private boolean okClicked = false;

    public NuevoArticuloController() {}
    @FXML
    public void initialize() {}

    public void setCatalogoModelo(Catalogo catalogoModelo) {
        this.catalogo = catalogoModelo;
    }
    public void setDialogStage(Stage dialogStage) {this.dialogStage = dialogStage;}
    public boolean isOkClicked() {return okClicked;}

    public void setNumProductoDisponible(){
        this.numArticulos.bindBidirectional(catalogo.getNumeroArticuloProperty());
        NumProducDispoAdd.setText(String.valueOf(numArticulos.getValue()));//mirar si se puuede borrar
        numArticulos.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                NumProducDispoAdd.setText(String.valueOf(numArticulos.getValue()));
                System.out.println(numArticulos.getValue()+" Esta en el lisener..");
            }
        });
    }

    public void setArticulo(Articulo articulo){
        //una vez que articulo ya tiene valores se los
        this.articulo=articulo;
        textFielNombre.setText(articulo.getNombre());
        textFielDescrip.setText(articulo.getDescripcion());
        textFielPrecio.setText(String.valueOf(articulo.getPrecio()));
        textFielStock.setText(String.valueOf(articulo.getStock()));
    }

    @FXML
    private void handleAceptar(ActionEvent event) {
        if (isInputValid()){
            //añadimos los valores de las fiel a el articulo
            articulo.setNombre(textFielNombre.getText());
            articulo.setDescripcion(textFielDescrip.getText());
            articulo.setPrecio(Float.parseFloat(textFielPrecio.getText()));
            articulo.setStock(Integer.parseInt(textFielStock.getText()));

            okClicked=true;
            dialogStage.close();
        }
    }
    @FXML
    private void handleCancelar(ActionEvent event) {dialogStage.close();}
    private boolean isInputValid() {
        String errorMessage = "";

        if (textFielNombre.getText() == null || textFielNombre.getText().length() == 0) {
            errorMessage += "Nombre invalido\n";
        }
        if (textFielDescrip.getText() == null || textFielDescrip.getText().length() == 0) {
            errorMessage += "Descripcion invalida\n";
        }
        if (textFielPrecio.getText() == null || textFielPrecio.getText().length() == 0) {
            errorMessage += "Precio invalido\n";
        }else {
            try {
                Float.parseFloat(textFielPrecio.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Debe introducir un numero\n";
            }
        }

        if (textFielStock.getText() == null || textFielStock.getText().length() == 0) {
            errorMessage += "Stock invalido\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(textFielStock.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Debe introducir un numero\n";
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Campos invalidos");
            alerta.setHeaderText("Introduzca correctamente los campos");
            alerta.setContentText(errorMessage);
            alerta.showAndWait();
            return false;
        }
    }

}
