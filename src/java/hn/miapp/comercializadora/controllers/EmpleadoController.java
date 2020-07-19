package hn.miapp.comercializadora.controllers;

import hn.miapp.comercializadora.dao.EmpleadoJDBCDAO;
import hn.miapp.comercializadora.modelos.Empleado;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EmpleadoController", urlPatterns = {"/empleados"})
public class EmpleadoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EmpleadoJDBCDAO empleadoJDBCDAO = new EmpleadoJDBCDAO();

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
            List<Empleado> listaEmpleados = empleadoJDBCDAO.listAll();

            request.setAttribute("empleados", listaEmpleados);
            request.getRequestDispatcher("WEB-INF/empleados/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("accion") != null) {

            String accion = request.getParameter("accion");

            switch (accion) {
                case "crear":
                    insertarEmpleado(request, response);
                    break;
                case "borrar":
                    borrarEmpleado(request, response);
                    break;
                case "actualizar":
                    actualizarEmpleado(request, response);
                    break;
            }
        }
    }

    private void formNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EmpleadoJDBCDAO daoEmpleado = new EmpleadoJDBCDAO();

        List<Empleado> empleados = daoEmpleado.listAll();

        request.setAttribute("empleados", empleados);
        request.setAttribute("tipoForm", "crear");

        request.getRequestDispatcher("WEB-INF/empleados/form.jsp").forward(request, response);

    }

    private void insertarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Empleado empleado = null;
        EmpleadoJDBCDAO daoEmpleado = new EmpleadoJDBCDAO();
        String mensaje = "";

        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            Date fechaNac = formato.parse(request.getParameter("fechaNac"));
            java.sql.Date fechaSQL = new java.sql.Date(fechaNac.getTime());
            int reportaA = Integer.parseInt(request.getParameter("reportaA"));
            int extension = Integer.parseInt(request.getParameter("extension"));

            mensaje = daoEmpleado.insert(new Empleado(nombre, apellido, fechaSQL, reportaA, extension));

            request.getSession().setAttribute("operacionEmpleado", mensaje);

            response.sendRedirect("/sistema_comercializadora/empleados");

        } catch (ParseException ex) {
            System.out.println("Error en formato de fecha: " + ex.getMessage());
        }

    }

    private void formEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmpleadoJDBCDAO daoEmpleado = new EmpleadoJDBCDAO();
        long idEmp = Long.parseLong(request.getParameter("idEmp"));

        Empleado empleado = daoEmpleado.findById(idEmp);
        
        List<Empleado> empleados = daoEmpleado.listAll();

        if (empleado != null) {

            request.setAttribute("tipoForm", "actualizar");
            request.setAttribute("empleado", empleado);
            request.setAttribute("empleados", empleados);
            request.getRequestDispatcher("WEB-INF/empleados/form.jsp").forward(request, response);

        }
    }

    private void borrarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmpleadoJDBCDAO daoEmpleado = new EmpleadoJDBCDAO();
        String mensaje = "";
        long claveEmp = Long.parseLong(request.getParameter("idEmp"));

        mensaje = daoEmpleado.delete(new Empleado(claveEmp));

        request.getSession().setAttribute("operacionEmpleado", mensaje);
        response.sendRedirect("/sistema_comercializadora/empleados");

    }

    private void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            
            String mensaje = "";
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            
            long idEmp = Integer.parseInt(request.getParameter("idEmp"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            Date fechaNac = formato.parse(request.getParameter("fechaNac"));
            java.sql.Date fechaSQL = new java.sql.Date(fechaNac.getTime());
            int reportaA = Integer.parseInt(request.getParameter("reportaA"));
            int extension = Integer.parseInt(request.getParameter("extension"));
            
            EmpleadoJDBCDAO daoEmpleado = new EmpleadoJDBCDAO();
            
            mensaje = daoEmpleado.update(new Empleado(idEmp, nombre, apellido, fechaSQL,reportaA, extension));
            
            request.getSession().setAttribute("operacionEmpleado", mensaje);
            
            response.sendRedirect("/sistema_comercializadora/empleados");
            
            
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
        
    }
}
