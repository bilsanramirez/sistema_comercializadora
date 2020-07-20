package hn.miapp.comercializadora.controllers;

import hn.miapp.comercializadora.dao.ClienteJDBCDAO;
import hn.miapp.comercializadora.modelos.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ClientesController", urlPatterns = {"/clientes"})
public class ClientesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("accion") != null) {

            String accion = request.getParameter("accion");

            switch (accion) {

                case "nuevo":
                    formNuevo(request, response);
                    break;
                case "actualizar":
                    formActualizar(request, response);

            }

        } else {
            ClienteJDBCDAO daoCliente = new ClienteJDBCDAO();
            List<Cliente> listaClientes = daoCliente.listAll();
            request.setAttribute("clientes", listaClientes);
            request.getRequestDispatcher("WEB-INF/clientes/index.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("accion") != null) {

            String accion = request.getParameter("accion");

            switch (accion) {
                case "nuevo":
                    insertarCliente(request, response);
                    break;
                case "actualizar": 
                    actualizarCliente(request, response);
                    break;
                case "borrar":
                    borrarCliente(request, response);
            }

        }

    }

    private void formNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("tipoForm", "nuevo");
        request.getRequestDispatcher("WEB-INF/clientes/form.jsp").forward(request, response);

    }

    private void formActualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long idCli = Long.parseLong(request.getParameter("idCli"));

        ClienteJDBCDAO daoCliente = new ClienteJDBCDAO();

        Cliente cliente = daoCliente.findById(new Cliente(idCli));

        request.setAttribute("cliente", cliente);
        request.setAttribute("tipoForm", "actualizar");
        request.getRequestDispatcher("WEB-INF/clientes/form.jsp").forward(request, response);

    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String mensaje = "";
        
        String cedulaRuc = request.getParameter("cedulaRuc");
        String nombreCia = request.getParameter("nombreCia");
        String nombreContacto = request.getParameter("nombreContacto");
        String direccionCli = request.getParameter("direccionCli");
        String fax = request.getParameter("fax");
        String email = request.getParameter("email");
        String celular = request.getParameter("celular");
        String fijo = request.getParameter("fijo");
        
        ClienteJDBCDAO daoCliente = new ClienteJDBCDAO();
        
        Cliente cliente = new Cliente(cedulaRuc, nombreCia, nombreContacto, direccionCli, fax, email, celular, fijo);
        
        mensaje = daoCliente.insert(cliente);
        
        request.getSession().setAttribute("operacionCliente", mensaje);
        response.sendRedirect("/sistema_comercializadora/clientes");
    }

    private void actualizarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mensaje = "";
        
        long clienteId = Long.parseLong(request.getParameter("idCli"));
        String cedulaRuc = request.getParameter("cedulaRuc");
        String nombreCia = request.getParameter("nombreCia");
        String nombreContacto = request.getParameter("nombreContacto");
        String direccionCli = request.getParameter("direccionCli");
        String fax = request.getParameter("fax");
        String email = request.getParameter("email");
        String celular = request.getParameter("celular");
        String fijo = request.getParameter("fijo");
        
        ClienteJDBCDAO daoCliente = new ClienteJDBCDAO();
        
        Cliente cliente = new Cliente(clienteId, cedulaRuc, nombreCia, nombreContacto, direccionCli, fax, email, celular, fijo);
        
        mensaje = daoCliente.update(cliente);
        
        request.getSession().setAttribute("operacionCliente", mensaje);
        response.sendRedirect("/sistema_comercializadora/clientes");
    }

    private void borrarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String mensaje = "";
        
        long clienteId = Long.parseLong(request.getParameter("idCli"));
        
        ClienteJDBCDAO daoCliente = new ClienteJDBCDAO();
        
        Cliente cliente = new Cliente(clienteId);
        
        mensaje = daoCliente.delete(cliente);
        
        request.getSession().setAttribute("operacionCliente", mensaje);
        response.sendRedirect("/sistema_comercializadora/clientes");
    }

}
