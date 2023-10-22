package PruebaCliente;

import java.util.List;

import Maestros.client;
import Modelos.ModeloClient;

public class ConsultaFiltros {

	public static void main(String[] args) {
		try {
			ModeloClient bean = new ModeloClient();
			bean.setName("Gimena");
			bean.setLast_name("");
			client userService = new client();
			List<ModeloClient> lista = userService.get(bean);
			for (ModeloClient user : lista) {
				System.out.println(user);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
