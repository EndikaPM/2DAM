package com.example.preparacion1trimestre.controller;

import Modelo.ExcepcionArticulo;
import com.example.preparacion1trimestre.Model.Articulo;
import com.example.preparacion1trimestre.Model.Catalogo;
import com.example.preparacion1trimestre.Util.Conversor;
import com.example.preparacion1trimestre.View.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CategoriaController {
    @FXML
    private TableView <Articulo> articuloTable;
    @FXML
    private TableColumn<Articulo,String> nameColum;
    @FXML
    private TableColumn<Articulo,String> precioColum;

    @FXML
    private Label LabelName;
    @FXML
    private Label LAbelDescripcion;
    @FXML
    private Label labelPrecio;
    @FXML
    private Label LabelStock;
    @FXML
    private TextField LabelUnidades;
    @FXML
    private Label labelTotal;

    private Main mainapp;
    private Catalogo catalogo;
    private TotalInterface totalInterface;
    private int id = 1;

    public void setMainapp(Main mainapp) throws ExcepcionArticulo {
        this.mainapp = mainapp;
        //añadimos los datos a la tabla de articulos
        articuloTable.setItems(mainapp.getArticulosData());
    }
    public void setCatalogoModelo(Catalogo catalogo) {this.catalogo = catalogo;}
    public CategoriaController(){totalInterface = new TotaInterfaceImplement();}

    @FXML
    private void initialize() {
        //inicializamos las columnas de nombre y precio
        nameColum.setCellValueFactory(cellData-> cellData.getValue().nombreProperty());
        precioColum.setCellValueFactory(cellData->cellData.getValue().nombreProperty());

        // ponemos los datos para ver nulos en detalles
        showArticuloDetails(null);

        //listener para cuando selecionemos salga los detalles
        articuloTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->
                {showArticuloDetails(newValue);});
    }


    private void showArticuloDetails(Articulo articulo){
        if(articulo!=null){
            LabelName.setText(articulo.getNombre());
            LAbelDescripcion.setText(articulo.getDescripcion());
            labelPrecio.setText(String.valueOf(articulo.getPrecio()));
            LabelStock.setText(String.valueOf(articulo.getStock()));
        }else {
            LabelName.setText("");
            LAbelDescripcion.setText("");
            labelPrecio.setText("");
        }
    }
    @FXML
    private void handleNewArticulo(ActionEvent event) {
        Articulo temArticulo = new Articulo();
        boolean okClicked = mainapp.showNuevoArticulo(temArticulo);
        if(okClicked) {
            try {
                temArticulo.setId(++id);
                catalogo.addArticulo(Conversor.convertirArticulo(temArticulo));
                catalogo.incrementarNumeroArticulos();
                mainapp.getArticulosData().add(temArticulo);

            }catch (ExcepcionArticulo e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error al añadir articulo");
                alert.setTitle("Error con la base de datos");
                alert.setContentText("No se puede conectar con la base de \ndatos para añadir el articulo");
                alert.showAndWait();
            }
        }
    }
    @FXML
    private void handleTotal(ActionEvent event) {
        try{
            if(Integer.parseInt(LabelStock.getText())>=Integer.parseInt(LabelUnidades.getText())){
                if (Integer.parseInt(LabelStock.getText())>0){
                    catalogo.setTotalInterface(totalInterface);
                    labelTotal.setText(String.valueOf(catalogo.getTotalImporte(Integer.parseInt(LabelUnidades.getText())
                            ,Float.parseFloat(labelPrecio.getText()))+"€"));
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error con las unidades");
                    alert.setTitle("El numero es incorrecto");
                    alert.setContentText("Las unidades no pueden ser negativas");
                    alert.showAndWait();
                }
            }else  {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error al calcular");
                alert.setTitle("Error con las unidades");
                alert.setContentText("Las unidades no pueden superar al stock actual");
                alert.showAndWait();
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al calcular");
            alert.setTitle("Error no ha introducido datos");
            alert.setContentText("Debe introducir un numero de unidades\n y seleccionar al articulo");
            alert.showAndWait();
        }
    }
    public void handleCancel(ActionEvent event) {
        Platform.exit();
    }
}
