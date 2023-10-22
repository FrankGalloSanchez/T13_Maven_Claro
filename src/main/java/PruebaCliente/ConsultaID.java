package PruebaCliente;

import Maestros.client;
import Modelos.ModeloClient;

public class ConsultaID {

	public static void main(String[] args) {
		try {
			client userService = new client();
			ModeloClient bean = userService.getForId("2");
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
