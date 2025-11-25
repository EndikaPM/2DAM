package com.example.ex1ev1.Util;

import Modelo.ArticuloVO;
import com.example.ex1ev1.Model.Articulo;

public class ArticuloUtil {
    public static Articulo convertirArticulo(ArticuloVO articuloVO){
        Articulo articulo = new Articulo();
        articulo.setId(articuloVO.getCodigo());
        articulo.setNombre(articuloVO.getNombre());
        articulo.setDescripcion(articuloVO.getDescripcion());
        articulo.setPrecio(articuloVO.getPrecio());
        articulo.setStock(articuloVO.getCantidad());
        return articulo;
    }

    public static ArticuloVO convertirArticulo(Articulo articulo){
        ArticuloVO articuloVO = new ArticuloVO();
        articuloVO.setCodigo(articulo.getId());
        articuloVO.setNombre(articulo.getNombre());
        articuloVO.setDescripcion(articulo.getDescripcion());
        articuloVO.setPrecio(articulo.getPrecio());
        articuloVO.setCantidad(articulo.getStock());
        return articuloVO;
    }
}
