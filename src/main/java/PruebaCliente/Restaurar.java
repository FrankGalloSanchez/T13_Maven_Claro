package PruebaCliente;

import Maestros.client;

public class Restaurar {

	public static void main(String[] args) {
		try {
			client userService = new client();
			userService.restore("11");
			System.out.println("Usuario restaurado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
