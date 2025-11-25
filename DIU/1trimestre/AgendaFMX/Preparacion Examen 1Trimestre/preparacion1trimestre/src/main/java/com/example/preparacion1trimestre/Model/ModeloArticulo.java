package com.example.preparacion1trimestre.Model;

import Modelo.ArticuloVO;
import Modelo.ExcepcionArticulo;
import Modelo.Repository.ArticuloRepository;

import java.util.ArrayList;

public class ModeloArticulo {
    private ArticuloRepository articuloRepository;
    public ModeloArticulo(ArticuloRepository articuloRepository) {this.articuloRepository = articuloRepository;}

    public ArticuloVO consulta(int var1) throws ExcepcionArticulo{
        return  this.articuloRepository.consulta(var1);
    }
    public void addArticulo(ArticuloVO var1) throws ExcepcionArticulo{
        this.articuloRepository.addArticulo(var1);
    }

    public ArrayList<ArticuloVO> obternerListaArticulos() throws ExcepcionArticulo{
        return this.articuloRepository.obternerListaArticulos();
    }
}
