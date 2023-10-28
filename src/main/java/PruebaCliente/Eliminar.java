package PruebaCliente;

import Maestros.client;

public class Eliminar {

	public static void main(String[] args) {
		try {
			client userService = new client();
			userService.delete("12");
			System.out.println("Usuario eliminado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
