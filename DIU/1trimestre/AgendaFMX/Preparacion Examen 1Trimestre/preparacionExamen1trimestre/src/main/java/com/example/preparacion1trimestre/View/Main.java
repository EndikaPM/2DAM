package com.example.preparacion1trimestre.View;

import Modelo.ArticuloVO;
import Modelo.ExcepcionArticulo;
import Modelo.Repository.ArticuloRepository;
import Modelo.Repository.Impl.ArticuloRepositoryJDBC;
import com.example.preparacion1trimestre.Model.Articulo;
import com.example.preparacion1trimestre.Model.Catalogo;
import com.example.preparacion1trimestre.Model.ModeloArticulo;
import com.example.preparacion1trimestre.Util.Conversor;
import com.example.preparacion1trimestre.controller.CategoriaController;
import com.example.preparacion1trimestre.controller.NuevoArticuloController;
import com.example.preparacion1trimestre.controller.RootController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private Catalogo catalogoModelo = new Catalogo();
    private ArticuloRepository articuloRepository = new ArticuloRepositoryJDBC();
    private ModeloArticulo modeloArticulo = new ModeloArticulo(articuloRepository);

    private ObservableList<Articulo> articulosData = FXCollections.observableArrayList();

    public Main() {}

    public ObservableList<Articulo> getArticulosData() {return this.articulosData;}
    public ModeloArticulo getModeloArticulo() {return this.modeloArticulo;}

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Gestor de Catalogos");

        cargarDatosIniciales();
        initRootLayout();
        showCatalogoOverview();
    }

    private void cargarDatosIniciales() {
        ArrayList<ArticuloVO> listaArticuloVO;
        try{
            catalogoModelo.setArticuloRepository(articuloRepository);
            listaArticuloVO = catalogoModelo.getListaArticulos();
            catalogoModelo.setNumeroArticulos(listaArticuloVO.size());

            for (ArticuloVO articuloVO : listaArticuloVO) {
                articulosData.add(Conversor.convertirArticulo(articuloVO));
            }
        }catch (ExcepcionArticulo e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al listar.");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos");
            alert.showAndWait();
        }
    }

    public void initRootLayout() {
        try{
            //cargamos la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/preparacion1trimestre/view/rooot_view.fxml"));
            rootLayout = (BorderPane) loader.load();
            RootController controlRoot = loader.getController();
            controlRoot.setMainApp(this);
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IOException e){}
    }

    public void showCatalogoOverview() {
        try{
           //cargamos la vista de todos los objetos
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/preparacion1trimestre/view/categoria.fxml"));
            AnchorPane categoriaView = (AnchorPane) loader.load();

            rootLayout.setCenter(categoriaView);

            CategoriaController controlCatalogo = loader.getController();
            controlCatalogo.setCatalogoModelo(catalogoModelo);
            controlCatalogo.setMainapp(this);
        }catch (IOException | ExcepcionArticulo e){}
    }

    public boolean showNuevoArticulo(Articulo articulo){
        try {
            //cargamos la viata de los objetos nuevos
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/preparacion1trimestre/view/nuevo_articulo.fxml"));
            AnchorPane vistaNuevoArticulo = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nuevo Articulo");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(vistaNuevoArticulo);
            dialogStage.setScene(scene);

            NuevoArticuloController controlNuevoArticulo = loader.getController();
            controlNuevoArticulo.setDialogStage(dialogStage);
            controlNuevoArticulo.setArticulo(articulo);
            controlNuevoArticulo.setCatalogoModelo(catalogoModelo);

            controlNuevoArticulo.setNumProductoDisponible();
            dialogStage.showAndWait();

            return controlNuevoArticulo.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
