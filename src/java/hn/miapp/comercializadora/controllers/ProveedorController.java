package hn.miapp.comercializadora.controllers;

import hn.miapp.comercializadora.dao.ProveedorJDBCDAO;
import hn.miapp.comercializadora.modelos.Proveedor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProveedorController", urlPatterns = {"/proveedores"})
public class ProveedorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("accion") != null) {

            String accion = request.getParameter("accion");

            switch (accion) {
                case "nuevo":
                    formNuevo(request, response);
                    break;
                case "editar":
                    formEditar(request, response);
                    break;
            }

        } else {
            ProveedorJDBCDAO daoProveedor = new ProveedorJDBCDAO();
            List<Proveedor> listaProveedores = daoProveedor.listAll();

            request.setAttribute("proveedores", listaProveedores);
            request.getRequestDispatcher("/WEB-INF/proveedores/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("accion") != null) {

            String accion = request.getParameter("accion");

            switch (accion) {

                case "nuevo":
                    insertarProveedor(request, response);
                    break;
                case "actualizar":
                    actualizarProveedor(request, response);
                    break;
                case "borrar":
                    borrarProveedor(request, response);
                    break;
            }
        }
    }

    private void formNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("tipoForm", "nuevo");
        request.getRequestDispatcher("WEB-INF/proveedores/form.jsp").forward(request, response);

    }

    private void insertarProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String mensaje = "";

        String nombreProv = request.getParameter("nombreProv");
        String contacto = request.getParameter("contacto");
        String celuProv = request.getParameter("celuProv");
        String fijoProv = request.getParameter("fijoProv");

        ProveedorJDBCDAO daoProveedor = new ProveedorJDBCDAO();

        mensaje = daoProveedor.insert(new Proveedor(nombreProv, contacto, celuProv, fijoProv));

        request.getSession().setAttribute("operacionProveedor", mensaje);

        response.sendRedirect("/sistema_comercializadora/proveedores");

    }

    private void formEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProveedorJDBCDAO daoProveedor = new ProveedorJDBCDAO();

        long idProv = Long.parseLong(request.getParameter("idProv"));

        Proveedor proveedor = daoProveedor.findById(idProv);

        if (proveedor != null) {
            request.setAttribute("tipoForm", "actualizar");
            request.setAttribute("proveedor", proveedor);

            request.getRequestDispatcher("/WEB-INF/proveedores/form.jsp").forward(request, response);
        }
    }

    private void actualizarProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        ProveedorJDBCDAO daoProveedor = new ProveedorJDBCDAO();
        String mensaje = "";
        
        long idProv = Long.parseLong(request.getParameter("idProv"));
        String nombreProv = request.getParameter("nombreProv");
        String contacto = request.getParameter("contacto");
        String celuProv = request.getParameter("celuProv");
        String fijoProv = request.getParameter("fijoProv");
        
        mensaje = daoProveedor.update(new Proveedor(idProv, nombreProv, contacto, celuProv, fijoProv));
        
        request.getSession().setAttribute("operacionProveedor", mensaje);
        response.sendRedirect("/sistema_comercializadora/proveedores");
        
    }

    private void borrarProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mensaje = "";
        ProveedorJDBCDAO daoProveedor = new ProveedorJDBCDAO();
        long idProv = Long.parseLong(request.getParameter("idProv"));
        
        mensaje = daoProveedor.delete(new Proveedor(idProv));
        
        request.getSession().setAttribute("operacionProveedor", mensaje);
        response.sendRedirect("/sistema_comercializadora/proveedores");
        
    }

}
