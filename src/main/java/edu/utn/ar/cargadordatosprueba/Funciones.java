/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utn.ar.cargadordatosprueba;

/**
 *
 * @author flavio
 */
public interface Funciones {

    public String getUsuariosDelSistema(String token);

    public String obtenerTokenSesion(String username, String password);

    public void generarPublicacion(String publicacion, String token);
    
    public void generarOfertaTransporte(Integer idPubli, String oferta, String token);
}
