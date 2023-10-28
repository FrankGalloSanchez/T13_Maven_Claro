package PruebaCliente;

import Maestros.client;
import Modelos.ModeloClient;

public class Editar {

	public static void main(String[] args) {
		try {
			ModeloClient bean = new ModeloClient(12, "Maria", "Avila", "DNI", "72898989", "902632666", "MariaAvila@example.com", "A");
			client client = new client();
			client .update(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
