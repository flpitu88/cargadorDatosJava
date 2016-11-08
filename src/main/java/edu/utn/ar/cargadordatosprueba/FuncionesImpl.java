/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utn.ar.cargadordatosprueba;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 *
 * @author flavio
 */
public class FuncionesImpl implements Funciones {

    private final String baseUrl = "http://52.38.176.160/ServerEnvioLibre/rest";

    public FuncionesImpl() {
    }

    @Override
    public String getUsuariosDelSistema(String token) {
        try {

            Client client = Client.create();

            WebResource webResource = client
                    .resource(baseUrl + "/usuarios");

            ClientResponse response = webResource.accept("application/json")
                    .header("Authorization", "Bearer " + token)
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);

            return output;

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }
    }

    @Override
    public String obtenerTokenSesion(String username, String password) {
        try {

            Client client = Client.create();

            WebResource webResource = client
                    .resource(baseUrl + "/autenticacion");

            String input = "username=" + username + "&password=" + password;

            ClientResponse response = webResource
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Accept", "text/plain")
                    .post(ClientResponse.class, input);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            return response.getEntity(String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void generarPublicacion(String publicacion, String token) {
        try {

            Client client = Client.create();

            WebResource webResource = client
                    .resource(baseUrl + "/publicaciones");

            ClientResponse response = webResource
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .post(ClientResponse.class, publicacion);

            if (response.getStatus() != 201) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generarOfertaTransporte(Integer idPubli, String oferta, String token) {
        try {

            Client client = Client.create();

            WebResource webResource = client
                    .resource(baseUrl + "/ofertas/" + idPubli);

            ClientResponse response = webResource
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .post(ClientResponse.class, oferta);

            if (response.getStatus() != 201) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
