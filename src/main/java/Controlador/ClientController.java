package Controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Maestros.client;
import Modelos.ModeloClient;

@WebServlet({ "/ClientBuscar", "/ClientProcesar", "/ClientActualizar", "/ClientHistorial" })
public class ClientController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private client service = new client();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/ClientBuscar":
                buscar(request, response);
                break;
            case "/ClientProcesar":
                procesar(request, response);
                break;
            case "/ClientActualizar":
                actualizar(request, response);
                break;
            case "/ClientHistorial":
                historial(request, response);
                break;
        }
    }

    private void procesar(HttpServletRequest request, HttpServletResponse response) {
        // Datos
        String accion = request.getParameter("accion");
        ModeloClient bean = new ModeloClient();
        bean.setId(Integer.parseInt(request.getParameter("id")));
        bean.setName(request.getParameter("names"));
        bean.setLast_name(request.getParameter("last_name"));
        bean.setType_document(request.getParameter("type_document"));
        bean.setNumber_document(request.getParameter("number_document"));
        bean.setEmail(request.getParameter("email"));
        bean.setCell_phone(request.getParameter("cellphone"));
        bean.setStatus(request.getParameter("status"));
        // Proceso
        try {
            switch (accion) {
                case ControllerUtil.CRUD_NUEVO:
                    service.insert(bean);
                    break;
                case ControllerUtil.CRUD_EDITAR:
                    service.update(bean);
                    break;
                case ControllerUtil.CRUD_ELIMINAR:
                    service.delete(bean.getId().toString());
                    break;
                case ControllerUtil.CRUD_RESTAURAR:
                    service.restore(bean.getId().toString());
                    break;
                case ControllerUtil.CRUD_ELIMINATE:
                    service.eliminate(bean.getId().toString());
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected value: " + accion);
            }
            ControllerUtil.responseJson(response, "Proceso ok.");
        } catch (Exception e) {
            ControllerUtil.responseJson(response, e.getMessage());
        }
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Datos
        String names = request.getParameter("names");
        String last_name = request.getParameter("last_name");
        // Proceso
        ModeloClient bean = new ModeloClient();
        bean.setName(names);
        bean.setLast_name(last_name);
        List<ModeloClient> lista = service.get(bean);
        // Preparando el JSON
        Gson gson = new Gson();
        String data = gson.toJson(lista);
        // Reporte
        ControllerUtil.responseJson(response, data);
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ModeloClient> lista = service.getActive();
        Gson gson = new Gson();
        String data = gson.toJson(lista);
        ControllerUtil.responseJson(response, data);
    }

    private void historial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ModeloClient> lista = service.getInactive();
        Gson gson = new Gson();
        String data = gson.toJson(lista);
        ControllerUtil.responseJson(response, data);
    }
}
