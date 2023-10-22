package PruebaCliente;

import java.util.List;

import Maestros.client;
import Modelos.ModeloClient;

public class ListasActivos {

    public static void main(String[] args) {
        try {
            client userService = new client();
            List<ModeloClient> lista = userService.getActive();
            for (ModeloClient client : lista) {
                System.out.println(client);
            }
        } catch (Exception e) {
            System.err.println("Hubo un error");
        }
    }
}
