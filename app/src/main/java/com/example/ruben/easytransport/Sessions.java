package com.example.ruben.easytransport;

import Objetos.Usuario;

/**
 * Created by JD on 13/12/2014.
 */
public class Sessions {

    private static Usuario usuarioLogeado;

    public static void setUsuarioLogeado(Usuario usuarioLogeado) {
        Sessions.usuarioLogeado = usuarioLogeado;
    }
    public static Usuario getUsuarioLogeado(){
        return usuarioLogeado;
    }
}
