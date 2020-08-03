package hn.miapp.comercializadora.controllers;

import hn.miapp.comercializadora.dao.ClienteJDBCDAO;
import hn.miapp.comercializadora.dao.DetalleOrdenJDBCDAO;
import hn.miapp.comercializadora.dao.EmpleadoJDBCDAO;
import hn.miapp.comercializadora.dao.OrdenJDBCDAO;
import hn.miapp.comercializadora.dao.ProductoJDBCDAO;
import hn.miapp.comercializadora.modelos.Cliente;
import hn.miapp.comercializadora.modelos.DetalleOrden;
import hn.miapp.comercializadora.modelos.Empleado;
import hn.miapp.comercializadora.modelos.Orden;
import hn.miapp.comercializadora.modelos.Producto;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PedidosController", urlPatterns = {"/pedidos"})
public class PedidosController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("accion") != null) {

            String accion = request.getParameter("accion");

            switch (accion) {
                case "verPedidos":
                    formVerPedidos(request, response);
                    break;
                case "hacerPedido":
                    hacerPedido(request, response);
                    break;
                case "verPedido":
                    verPedido(request, response);
                    break;
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/pedidos/dashboard.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("accion") != null) {

            String accion = request.getParameter("accion");

            switch (accion) {

                case "addProducto":
                    addProducto(request, response);
                    break;
                case "terminarPedido":
                    terminarPedido(request, response);
                    break;
            }

        }

    }

    private void formVerPedidos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        OrdenJDBCDAO daoOrden = new OrdenJDBCDAO();
        
        List<Orden> listaOrdenes = daoOrden.listAll();
        
        request.setAttribute("listaOrdenes", listaOrdenes);
        request.getRequestDispatcher("WEB-INF/pedidos/index.jsp").forward(request, response);
        
    }

    private void hacerPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Orden orden = (Orden) request.getSession().getAttribute("orden");

        EmpleadoJDBCDAO daoEmpleados = new EmpleadoJDBCDAO();
        ClienteJDBCDAO daoClientes = new ClienteJDBCDAO();

        List<Empleado> listaEmpleados = daoEmpleados.listAll();
        List<Cliente> listaClientes = daoClientes.listAll();
        List<Producto> listaProductos = new ProductoJDBCDAO().listAll();

        request.setAttribute("productos", listaProductos);
        request.setAttribute("empleados", listaEmpleados);
        request.setAttribute("clientes", listaClientes);

        if (orden == null) {
            orden = new Orden();
            orden.setImporte(0.0);
            orden.setFechaOrden(new java.sql.Date(new java.util.Date().getTime()));
        } else {
            double importeOrden = 0.0;
            for (DetalleOrden detalle : orden.getDetalles()) {
                importeOrden += detalle.getImporte();
            }
            orden.setImporte(importeOrden);
        }
        request.getSession().setAttribute("orden", orden);
        request.getRequestDispatcher("WEB-INF/pedidos/new.jsp").forward(request, response);
    }

    private void addProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long productoId = Long.parseLong(request.getParameter("productoId"));
        double cantidad = Double.parseDouble(request.getParameter("cantidad"));

        ProductoJDBCDAO daoProducto = new ProductoJDBCDAO();
        Producto producto = daoProducto.findById(productoId);
        double importe = producto.getPrecioUnit() * cantidad;

        Orden orden = (Orden) request.getSession().getAttribute("orden");

        DetalleOrden detalle = new DetalleOrden();

        detalle.setCantidad(cantidad);
        detalle.setOrden(orden);
        detalle.setImporte(importe);
        detalle.setProducto(producto);

        if (orden == null) {
            
            orden = new Orden();
            request.getSession().setAttribute("orden", orden);
       
        }

        orden.getDetalles().add(detalle);
        response.sendRedirect(request.getContextPath() + "/pedidos?accion=hacerPedido");

    }

    private void terminarPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long clienteId = Long.parseLong(request.getParameter("clienteId"));
        long empleadoId = Long.parseLong(request.getParameter("empleadoId"));

        ClienteJDBCDAO daoCliente = new ClienteJDBCDAO();
        EmpleadoJDBCDAO daoEmpleado = new EmpleadoJDBCDAO();

        Cliente cliente = daoCliente.findById(new Cliente(clienteId));
        Empleado empleado = daoEmpleado.findById(empleadoId);

        Orden orden = (Orden) request.getSession().getAttribute("orden");

        orden.setCliente(cliente);
        orden.setEmpleado(empleado);

        OrdenJDBCDAO daoOrden = new OrdenJDBCDAO();

        Orden ordenCreada = daoOrden.insert(orden);

        if (ordenCreada != null) {

            request.getSession().setAttribute("ordenCreada", ordenCreada);
            response.sendRedirect("/sistema_comercializadora/pedidos?accion=verPedidos");
        }

    }

    private void verPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        long ordenId = Long.parseLong(request.getParameter("ordenId"));
        OrdenJDBCDAO daoOrden = new OrdenJDBCDAO();
        DetalleOrdenJDBCDAO daoDetalleOrden = new DetalleOrdenJDBCDAO();
        
        Orden orden = daoOrden.findById(ordenId);
        
        List<DetalleOrden> detalles = daoDetalleOrden.listAll(orden);
        
        orden.setDetallesOrden(detalles);
       
        request.setAttribute("orden", orden);
        request.getRequestDispatcher("WEB-INF/pedidos/show.jsp").forward(request, response);
        
    }

}
