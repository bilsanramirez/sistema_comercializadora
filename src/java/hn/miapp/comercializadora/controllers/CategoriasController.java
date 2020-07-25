package hn.miapp.comercializadora.controllers;

import hn.miapp.comercializadora.dao.CategoriaJDBCDAO;
import hn.miapp.comercializadora.dao.ProductoJDBCDAO;
import hn.miapp.comercializadora.modelos.Categoria;
import hn.miapp.comercializadora.modelos.Producto;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CategoriasController", urlPatterns = {"/categorias"})
public class CategoriasController extends HttpServlet {

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
                case "productos":
                    formVerProductos(request, response);
            }

        } else {
            CategoriaJDBCDAO daoCategoria = new CategoriaJDBCDAO();
            List<Categoria> listaCategorias = daoCategoria.listAll();

            request.setAttribute("categorias", listaCategorias);

            request.getRequestDispatcher("WEB-INF/categorias/index.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("accion") != null) {
            String accion = request.getParameter("accion");

            switch (accion) {
                case "crear":
                    insertarCategoria(request, response);
                    break;
                case "borrar":
                    borrarCategoria(request, response);
                    break;
                case "actualizar":
                    actualizarCategoria(request, response);
                    break;
            }
        }
    }

    private void formNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("tipoForm", "crear");
        request.getRequestDispatcher("WEB-INF/categorias/form.jsp").forward(request, response);

    }

    private void insertarCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long claveCat = Long.parseLong(request.getParameter("categoriaId"));
        String nombreCat = request.getParameter("nombreCat");

        CategoriaJDBCDAO categoriaJDBCDAO = new CategoriaJDBCDAO();
        Categoria cat = new Categoria(claveCat, nombreCat);

        String mensaje = categoriaJDBCDAO.insert(cat);

        request.getSession().setAttribute("operacionCategoria", mensaje);

        response.sendRedirect("/sistema_comercializadora/categorias");

    }

    private void formEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long idCat = Long.parseLong(request.getParameter("idCat"));

        CategoriaJDBCDAO categoriaJDBCDAO = new CategoriaJDBCDAO();

        Categoria categoria = categoriaJDBCDAO.findById(idCat);

        if (categoria != null) {
            request.setAttribute("tipoForm", "actualizar");
            request.setAttribute("categoria", categoria);
            request.getRequestDispatcher("WEB-INF/categorias/form.jsp").forward(request, response);
        }

    }

    private void actualizarCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Categoria categoria = new Categoria();
        CategoriaJDBCDAO categoriaJDBCDAO = new CategoriaJDBCDAO();
        String mensaje = "";

        long idCat = Long.parseLong(request.getParameter("categoriaId"));
        String nombreCat = request.getParameter("nombreCat");

        categoria.setCategoriaId(idCat);
        categoria.setNombreCat(nombreCat);

        mensaje = categoriaJDBCDAO.update(categoria);

        request.getSession().setAttribute("operacionCategoria", mensaje);

        response.sendRedirect("/sistema_comercializadora/categorias");

    }

    private void borrarCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String mensaje = null;
        CategoriaJDBCDAO categoriaJDBCDAO = new CategoriaJDBCDAO();

        long idCat = Long.parseLong(request.getParameter("idCat"));

        mensaje = categoriaJDBCDAO.delete(new Categoria(idCat));

        request.getSession().setAttribute("operacionCategoria", mensaje);

        response.sendRedirect("/sistema_comercializadora/categorias");

    }

    private void formVerProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoriaJDBCDAO daoCategoria = new CategoriaJDBCDAO();
        long idCat = Long.parseLong(request.getParameter("idCat"));
        ProductoJDBCDAO daoProducto = new ProductoJDBCDAO();
        
        Categoria cat = daoCategoria.findById(idCat);

        List<Producto> listaProductosCategorias = daoProducto.getProductosByCategoria(cat);
        
        cat.setProductos(listaProductosCategorias);

        request.setAttribute("categoria", cat);
        request.getRequestDispatcher("WEB-INF/categorias/show.jsp").forward(request, response);
    }
}
