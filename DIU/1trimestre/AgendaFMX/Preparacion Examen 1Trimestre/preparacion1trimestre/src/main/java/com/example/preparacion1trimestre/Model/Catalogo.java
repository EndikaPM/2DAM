package com.example.preparacion1trimestre.Model;

import Modelo.ArticuloVO;
import Modelo.ExcepcionArticulo;
import Modelo.Repository.ArticuloRepository;
import Modelo.Repository.Impl.ArticuloRepositoryJDBC;
import com.example.preparacion1trimestre.controller.TotalInterface;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Catalogo {

    public Catalogo() {}

    private ArticuloRepository articuloRepository = new ArticuloRepositoryJDBC();
    private TotalInterface total;

    private IntegerProperty numeroArticulo = new SimpleIntegerProperty();
    public void setNumeroArticulos(Integer numero) {this.numeroArticulo.set(numero);}
    public void decrementarNumeroArticulos() {this.numeroArticulo.set(this.numeroArticulo.get()-1);}
    public void incrementarNumeroArticulos() {this.numeroArticulo.set(this.numeroArticulo.get()+1);}

    public IntegerProperty getNumeroArticuloProperty() {return numeroArticulo;}

    public void setArticuloRepository(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }
    public ArticuloVO getArticuloVO(int id) throws ExcepcionArticulo {
        return articuloRepository.consulta(id);
    }
    public void addArticulo(ArticuloVO articulo) throws ExcepcionArticulo {
        articuloRepository.addArticulo(articulo);
    }
    public ArrayList<ArticuloVO> getListaArticulos() throws ExcepcionArticulo {
        return articuloRepository.obternerListaArticulos();
    }
    public float getTotalImporte(Integer cantidad, Float precio) {return total.calcularTotal(cantidad,precio);}
    public void setArticulosRespostory(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }
    public ArticuloRepository getArticuloRepository() {
        return articuloRepository;
    }
    public void setTotalInterface(TotalInterface total) {
        this.total = total;
    }
}
