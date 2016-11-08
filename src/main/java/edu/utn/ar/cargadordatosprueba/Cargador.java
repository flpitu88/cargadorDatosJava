/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utn.ar.cargadordatosprueba;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author flavio
 */
public class Cargador {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final Funciones funciones = new FuncionesImpl();

    private static final String tokenIni = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoianVhbmVzIiwiZXhwIjoxNDgxMTY2Nzc0ODUzLCJpYXQiOjE0Nzg1NzQ3NzQ4NTN9.zm9NIbOxvJVm10_BLDAqcQIi-BDqCZuiffw8yxSD88U";

    public static void main(String[] args) throws IOException {
        String json = funciones.getUsuariosDelSistema(tokenIni);

        List<Map<String, Object>> listado = new ArrayList<>();

        // convert JSON string to Map
        listado = mapper.readValue(json, new TypeReference<List<Map<String, Object>>>() {
        });

        for (Map<String, Object> usuario : listado) {
            System.out.println("Usuario: " + usuario.get("id") + " " + usuario.get("nombreUsuario"));

            String token = funciones.obtenerTokenSesion(
                    (String) usuario.get("nombreUsuario"),
                    (String) usuario.get("clave"));

            System.out.println("Token vale: " + token);

            if (((Integer) usuario.get("id")).equals(1)) {
                // Genero una publicacion para ser posteada
                Map origenPub = new HashMap<>();
                origenPub.put("latitud", 35.15);
                origenPub.put("longitud", 35.15);
                origenPub.put("direccion", "Quintana 7105");

                Map destinoPub = new HashMap<>();
                destinoPub.put("latitud", 33.33);
                destinoPub.put("longitud", 33.33);
                destinoPub.put("direccion", "Roldan 2400");

                Map publicacion = new HashMap<>();
                publicacion.put("id", null);
                publicacion.put("tamanio", "chico");

                publicacion.put("origen", origenPub);
                publicacion.put("destino", destinoPub);
                publicacion.put("receptor", "tincho");

                publicacion.put("fechaMaxEntrega", "10/12/2016");
                publicacion.put("rangoInicio", "10");
                publicacion.put("rangoFin", "18");
                publicacion.put("usuario", "juanes");

                publicacion.put("distancia", 0);
                publicacion.put("precioMinimo", null);
                publicacion.put("fecha", "03/11/2016 11:11");
                publicacion.put("comentario", "probando nueva desde app");

                String jsonPublicacion = mapper.writeValueAsString(publicacion);

                funciones.generarPublicacion(jsonPublicacion, token);
            }

        }

    }

}
