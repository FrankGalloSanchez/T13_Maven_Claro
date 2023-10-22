package PruebaCliente;

import java.util.List;

import Maestros.client;
import Modelos.ModeloClient;

public class ListaInactivos {

	public static void main(String[] args) {
		try {
			client userService = new client();
			List<ModeloClient> lista = userService.getInactive();
			for (ModeloClient user : lista) {
				System.out.println(user);
			}
		} catch (Exception e) {
			System.err.println("Hubo un error");
		}
	}
}
