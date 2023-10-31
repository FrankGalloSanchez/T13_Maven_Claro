package PruebaCliente;

import Maestros.client;
import Modelos.ModeloClient;

public class Insertar {

	public static void main(String[] args) {
		try {
			ModeloClient bean = new ModeloClient(1, "Cristopher", "Socalay", "DNI", "72658875", "902632666", "Cristopher@example.com", "A");
			client client = new client();
			client.insert(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
