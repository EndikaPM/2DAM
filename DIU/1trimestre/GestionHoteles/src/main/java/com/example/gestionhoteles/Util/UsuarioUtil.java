package com.example.gestionhoteles.Util;

import com.example.gestionhoteles.Model.Usuario.Usuario;
import com.example.gestionhoteles.Model.Usuario.UsuarioVO;

public class UsuarioUtil {

    public static UsuarioVO getUsuario(Usuario temUsuario){
        UsuarioVO usuarioVO = new UsuarioVO();
        usuarioVO.setDni(temUsuario.getDni());
        usuarioVO.setNombre(temUsuario.getNombre());
        usuarioVO.setApellido(temUsuario.getApellido());
        usuarioVO.setDireccion(temUsuario.getDireccion());
        usuarioVO.setLocalidad(temUsuario.getLocalidad());
        usuarioVO.setProvincia(temUsuario.getProvincia());
        usuarioVO.setCodigoPostal(temUsuario.getCodigoPostal());
        return usuarioVO;
    }

    public static Usuario getUsuario(UsuarioVO TemUsuarioVO){
        Usuario usuario = new Usuario();
        usuario.setDni(TemUsuarioVO.getDni());
        usuario.setNombre(TemUsuarioVO.getNombre());
        usuario.setApellido(TemUsuarioVO.getApellido());
        usuario.setDireccion(TemUsuarioVO.getDireccion());
        usuario.setLocalidad(TemUsuarioVO.getLocalidad());
        usuario.setProvincia(TemUsuarioVO.getProvincia());
        usuario.setCodigoPostal(TemUsuarioVO.getCodigoPostal());
        return usuario;
    }
}
