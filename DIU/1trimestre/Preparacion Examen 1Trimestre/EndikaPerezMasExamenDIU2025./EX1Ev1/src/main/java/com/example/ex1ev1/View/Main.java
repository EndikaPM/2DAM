package com.example.ex1ev1.View;

import Modelo.ArticuloVO;
import Modelo.ExcepcionArticulo;
import Modelo.Repository.ArticuloRepository;
import Modelo.Repository.Impl.ArticuloRepositoryJDBC;
import com.example.ex1ev1.Controller.ListaArticulos;
import com.example.ex1ev1.Controller.NuevoArticuloController;
import com.example.ex1ev1.Controller.RootController;
import com.example.ex1ev1.Model.Articulo;
import com.example.ex1ev1.Model.Catalogo;
import com.example.ex1ev1.Model.ModelArticulo;
import com.example.ex1ev1.Util.ArticuloUtil;
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
import java.sql.SQLException;
import java.util.ArrayList;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private Catalogo catalogo;
    private ArticuloRepository articuloRepository;
    private ModelArticulo modelArticulo;

    private ObservableList<Articulo> articulosData = FXCollections.observableArrayList();
    public ObservableList<Articulo> getArticulosData() {return this.articulosData;}
    public ModelArticulo getModeloArticulo() {return this.modelArticulo;}

    public Main() throws SQLException {
            catalogo = new Catalogo();
            rootLayout = new BorderPane();
            articuloRepository = new ArticuloRepositoryJDBC();
            modelArticulo = new ModelArticulo(articuloRepository);
    }

    @Override
    public void start(Stage stage) throws ExcepcionArticulo {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Gestor de Catalogos");

        cargarDatosIniciales();
        initRootLayout();
        showCatalogoOverview();
    }

    public void initRootLayout() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ex1ev1/root_controller.fxml"));
            rootLayout = (BorderPane) loader.load();
            RootController controlRoot = loader.getController();
            controlRoot.setMainApp(this);
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al Iniciar el root.");
            alert.setTitle("Error");
            alert.setContentText("No se puede conectar el root de la app");
            alert.showAndWait();
        }
    }


    public void showCatalogoOverview() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ex1ev1/hello-view.fxml"));
            AnchorPane categoriaView = (AnchorPane) loader.load();

            rootLayout.setCenter(categoriaView);

            ListaArticulos controlCatalogo = loader.getController();
            controlCatalogo.setCatalogoModelo(catalogo);
            controlCatalogo.setMainapp(this);
        }catch (IOException | ExcepcionArticulo e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al Iniciar el catalogo.");
            alert.setTitle("Error");
            alert.setContentText("No se puede conectar el catalogo.");
            alert.showAndWait();
        }

    }
    public void cargarDatosIniciales() {
        ArrayList<ArticuloVO> listaArticuloVO = new ArrayList<>();
        try{
            catalogo.setArticuloRepository(articuloRepository);
            ArticuloVO pruebaVO1 = new ArticuloVO("descripción1", 15.31F, "Nombre1", 201);
            ArticuloVO pruebaVO2 = new ArticuloVO("descripción2", 15.32F, "Nombre2", 202);
            ArticuloVO pruebaVO3 = new ArticuloVO("descripción3", 15.33F, "Nombre3", 203);
            ArticuloVO pruebaVO4 = new ArticuloVO("descripción4", 15.34F, "Nombre4", 204);
            listaArticuloVO.add(pruebaVO2);
            listaArticuloVO.add(pruebaVO1);
            listaArticuloVO.add(pruebaVO3);
            listaArticuloVO.add(pruebaVO4);

            for (ArticuloVO articuloVO : listaArticuloVO) {
                articulosData.add(ArticuloUtil.convertirArticulo(articuloVO));
            }

            System.out.println(listaArticuloVO.size());
            catalogo.setNumeroArticulos(listaArticuloVO.size());

        }catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al listar.");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos");
            alert.showAndWait();
        }
    }

    public boolean showNuevoArticulo(Articulo articulo){
        try {
            //cargamos la viata de los objetos nuevos
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ex1ev1/nuevo_articulo.fxml"));
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
            controlNuevoArticulo.setCatalogoModelo(catalogo);

            controlNuevoArticulo.setNumProductoDisponible();
            dialogStage.showAndWait();

            return controlNuevoArticulo.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
