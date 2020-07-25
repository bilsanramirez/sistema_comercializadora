package hn.miapp.comercializadora.dao;

import hn.miapp.comercializadora.conexiones.BaseDatosMS;
import hn.miapp.comercializadora.modelos.Categoria;
import hn.miapp.comercializadora.modelos.Producto;
import hn.miapp.comercializadora.modelos.Proveedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoJDBCDAO implements IProductoDao {

    @Override
    public List<Producto> listAll() {
        Producto producto;
        Categoria categoria;
        Proveedor proveedor;
        BaseDatosMS conexion = new BaseDatosMS();
        List<Producto> listaProductos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM productos";
            PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                long productoId = rs.getLong("productoid");
                long proveedorId = rs.getLong("proveedorid");
                long categoriaId = rs.getLong("categoriaid");
                String descripcion = rs.getString("descripcion");
                double precioUnit = rs.getDouble("preciounit");
                int existencia = rs.getInt("existencia");

                categoria = new CategoriaJDBCDAO().findById(categoriaId);
                proveedor = new ProveedorJDBCDAO().findById(proveedorId);

                producto = new Producto(productoId, proveedor, categoria, descripcion, precioUnit, existencia);

                listaProductos.add(producto);

            }

            conexion.desconectarBD();

        } catch (SQLException e) {
            System.out.println("Error en listAll de productos: " + e.getMessage());
            conexion.desconectarBD();
        }
        return listaProductos;
    }

    @Override
    public String insert(Producto prod) {

        String mensaje = "";
        BaseDatosMS conexion = new BaseDatosMS();

        try {
            String sql = "INSERT INTO productos (proveedorid, categoriaid, descripcion, preciounit, existencia) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
            ps.setLong(1, prod.getProveedorId().getProveedorId());
            ps.setLong(2, prod.getCategoriaId().getCategoriaId());
            ps.setString(3, prod.getDescripcion());
            ps.setDouble(4, prod.getPrecioUnit());
            ps.setInt(5, prod.getExistencia());

            ps.executeUpdate();

            mensaje = "Producto guardado correctamente";

            conexion.desconectarBD();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en insert productoJDBCDAO: " + e.getMessage());
            mensaje = "Error al guardar el producto: " + e.getMessage();
            conexion.desconectarBD();
        }

        return mensaje;
    }

    @Override
    public String update(Producto prod) {
        String mensaje = "";
        BaseDatosMS conexion = new BaseDatosMS();

        try {
            String sql = "UPDATE productos SET proveedorid = ?, categoriaid = ?, descripcion = ?, preciounit = ?, existencia = ? WHERE productoid = ?";
            PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
            ps.setLong(1, prod.getProveedorId().getProveedorId());
            ps.setLong(2, prod.getCategoriaId().getCategoriaId());
            ps.setString(3, prod.getDescripcion());
            ps.setDouble(4, prod.getPrecioUnit());
            ps.setInt(5, prod.getExistencia());
            ps.setLong(6, prod.getProductoId());

            ps.executeUpdate();

            mensaje = "Producto actualizado correctamente";

            conexion.desconectarBD();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en update productoJDBCDAO: " + e.getMessage());
            mensaje = "Error en actualizar el producto: " + e.getMessage();
            conexion.desconectarBD();
        }

        return mensaje;
    }

    @Override
    public String delete(Producto prod) {
        String mensaje = "";
        BaseDatosMS conexion = new BaseDatosMS();

        try {
            String sql = "DELETE FROM productos WHERE productoid = ?";
            PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
            ps.setLong(1, prod.getProductoId());

            ps.executeUpdate();

            mensaje = "Producto eliminado correctamente";

            conexion.desconectarBD();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en eliminar productoJDBCDAO: " + e.getMessage());
            mensaje = "Error en eliminar el producto: " + e.getMessage();
            conexion.desconectarBD();
        }

        return mensaje;
    }

    @Override
    public Producto findById(long idProd) {

        Producto producto = null;
        BaseDatosMS conexion = new BaseDatosMS();

        try {
            String sql = "SELECT * FROM productos WHERE productoid = ?";
            PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
            ps.setLong(1, idProd);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                long productoId = rs.getLong("productoid");
                long proveedorId = rs.getLong("proveedorid");
                long categoriaId = rs.getLong("categoriaid");
                String descripcion = rs.getString("descripcion");
                double precioUnit = rs.getDouble("preciounit");
                int existencia = rs.getInt("existencia");

                Categoria categoria = new CategoriaJDBCDAO().findById(categoriaId);
                Proveedor proveedor = new ProveedorJDBCDAO().findById(proveedorId);

                producto = new Producto(productoId, proveedor, categoria, descripcion, precioUnit, existencia);
            }

            conexion.desconectarBD();

        } catch (SQLException e) {
            System.out.println("Excepcion: " + e.getMessage());
            conexion.desconectarBD();
        }

        return producto;

    }

    @Override
    public List<Producto> getProductosByCategoria(Categoria cat) {
        Producto producto = null;
        List<Producto> listaProductos = new ArrayList<>();
        BaseDatosMS conexion = new BaseDatosMS();

        try {
            String sql = "SELECT * FROM clientes WHERE categoriaid = ?";
            PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
            ps.setLong(1, cat.getCategoriaId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                long productoId = rs.getLong("productoid");
                long proveedorId = rs.getLong("proveedorid");
                long categoriaId = rs.getLong("categoriaid");
                String descripcion = rs.getString("descripcion");
                double precioUnit = rs.getDouble("preciounit");
                int existencia = rs.getInt("existencia");

                Categoria categoria = new CategoriaJDBCDAO().findById(categoriaId);
                Proveedor proveedor = new ProveedorJDBCDAO().findById(proveedorId);

                producto = new Producto(productoId, proveedor, categoria, descripcion, precioUnit, existencia);
                
                listaProductos.add(producto);
            }

            conexion.desconectarBD();

        } catch (SQLException e) {
            System.out.println("Excepcion: " + e.getMessage());
            conexion.desconectarBD();
        }

        return listaProductos;
    }

}
