package PruebaCliente;

import Maestros.client;
import Modelos.ModeloClient;

public class Insertar {

	public static void main(String[] args) {
		try {
			ModeloClient bean = new ModeloClient(2, "Frank", "Gallo", "DNI", "72658875", "902632666", "GalloF@example.com", "A");
			client client = new client();
			client.insert(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}