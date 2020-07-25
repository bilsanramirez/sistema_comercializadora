package hn.miapp.comercializadora.controllers;

import hn.miapp.comercializadora.dao.CategoriaJDBCDAO;
import hn.miapp.comercializadora.dao.ProductoJDBCDAO;
import hn.miapp.comercializadora.dao.ProveedorJDBCDAO;
import hn.miapp.comercializadora.modelos.Categoria;
import hn.miapp.comercializadora.modelos.Producto;
import hn.miapp.comercializadora.modelos.Proveedor;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductosController", urlPatterns = {"/productos"})
public class ProductosController extends HttpServlet {

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
                    break;
            }

        } else {

            ProductoJDBCDAO daoProducto = new ProductoJDBCDAO();

            List<Producto> listaProductos = daoProducto.listAll();

            request.setAttribute("productos", listaProductos);
            request.getRequestDispatcher("WEB-INF/productos/index.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("accion") != null) {

            String accion = request.getParameter("accion");

            switch (accion) {

                case "nuevo":
                    insertarProducto(request, response);
                    break;
                case "actualizar":
                    actualizarProducto(request, response);
                    break;
                case "borrar":
                    eliminarProducto(request, response);
                    break;
            }

        }

    }

    private void formNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Categoria> listaCategorias = new CategoriaJDBCDAO().listAll();
        List<Proveedor> listaProveedores = new ProveedorJDBCDAO().listAll();

        request.setAttribute("categorias", listaCategorias);
        request.setAttribute("proveedores", listaProveedores);
        request.setAttribute("tipoForm", "nuevo");

        request.getRequestDispatcher("WEB-INF/productos/form.jsp").forward(request, response);

    }

    private void formActualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long idProd = Long.parseLong(request.getParameter("idProd"));
        ProductoJDBCDAO daoProductoJDBCDAO = new ProductoJDBCDAO();

        Producto producto = daoProductoJDBCDAO.findById(idProd);

        if (producto != null) {

            List<Proveedor> listaProveedores = new ProveedorJDBCDAO().listAll();
            List<Categoria> listaCategorias = new CategoriaJDBCDAO().listAll();

            request.setAttribute("proveedores", listaProveedores);
            request.setAttribute("categorias", listaCategorias);
            request.setAttribute("producto", producto);
            request.setAttribute("tipoForm", "actualizar");

            request.getRequestDispatcher("WEB-INF/productos/form.jsp").forward(request, response);

        }

    }

    private void insertarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long proveedorId = Long.parseLong(request.getParameter("proveedorId"));
        long categoriaId = Long.parseLong(request.getParameter("categoriaId"));
        String descripcion = request.getParameter("descripcion");
        double precioInit = Double.parseDouble(request.getParameter("precioUnit"));
        int existencia = Integer.parseInt(request.getParameter("existencia"));

        Proveedor prov = new ProveedorJDBCDAO().findById(proveedorId);
        Categoria cat = new CategoriaJDBCDAO().findById(categoriaId);

        String mensaje = new ProductoJDBCDAO().insert(new Producto(prov, cat, descripcion, precioInit, existencia));

        request.getSession().setAttribute("operacionProducto", mensaje);

        response.sendRedirect("/sistema_comercializadora/productos");

    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long productoId = Long.parseLong(request.getParameter("idProd"));
        long proveedorId = Long.parseLong(request.getParameter("proveedorId"));
        long categoriaId = Long.parseLong(request.getParameter("categoriaId"));
        String descripcion = request.getParameter("descripcion");
        double precioInit = Double.parseDouble(request.getParameter("precioUnit"));
        int existencia = Integer.parseInt(request.getParameter("existencia"));

        Proveedor prov = new ProveedorJDBCDAO().findById(proveedorId);
        Categoria cat = new CategoriaJDBCDAO().findById(categoriaId);

        String mensaje = new ProductoJDBCDAO().update(new Producto(productoId, prov, cat, descripcion, precioInit, existencia));

        request.getSession().setAttribute("operacionProducto", mensaje);

        response.sendRedirect("/sistema_comercializadora/productos");
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long productoId = Long.parseLong(request.getParameter("idProd"));

        String mensaje = new ProductoJDBCDAO().delete(new Producto(productoId));

        request.getSession().setAttribute("operacionProducto", mensaje);

        response.sendRedirect("/sistema_comercializadora/productos");
    }
}
