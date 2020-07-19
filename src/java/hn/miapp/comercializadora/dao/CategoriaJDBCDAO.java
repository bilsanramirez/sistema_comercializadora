package hn.miapp.comercializadora.dao;

import java.sql.*;
import hn.miapp.comercializadora.conexiones.BaseDatosMS;
import hn.miapp.comercializadora.modelos.Categoria;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriaJDBCDAO implements ICategoriaDao {

    @Override
    public List<Categoria> listAll() {

        Categoria categoria;
        List<Categoria> lista = new LinkedList<>();

        try {

            BaseDatosMS conn = new BaseDatosMS();

            String sql = "select * from categorias";

            PreparedStatement ps = conn.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                categoria = new Categoria();
                categoria.setCategoriaId(rs.getInt("categoriaid"));
                categoria.setNombreCat(rs.getString("nombrecat"));

                lista.add(categoria);
            }
        } catch (SQLException e) {
            System.err.println("Error en el listAll de categorias" + e.getMessage());

        }
        return lista;
    }

    @Override
    public String insert(Categoria cat) {
        String mensaje = "";

        try {
            BaseDatosMS base = new BaseDatosMS();
            String sql = "insert into categorias (categoriaid, nombrecat) values (?, ?)";

            PreparedStatement ps = base.getConnection().prepareStatement(sql);
            ps.setLong(1, cat.getCategoriaId());
            ps.setString(2, cat.getNombreCat());
            ps.executeUpdate();
            mensaje = "La categoria se ha creado exitosamente";
            base.desconectarBD();
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "No fue posible crear la categoria";
        }

        return mensaje;
    }

    @Override
    public Categoria findById(long id) {
        Categoria categoria = null;

        try {
            BaseDatosMS conexion = new BaseDatosMS();
            String sql = "select * from categorias where categoriaid = ?";
            PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                categoria = new Categoria();
                categoria.setCategoriaId(rs.getInt("categoriaid"));
                categoria.setNombreCat(rs.getString("nombrecat"));
            }

            conexion.desconectarBD();

        } catch (SQLException ex) {
            System.out.println("Excepcion: " + ex.getMessage());
        }
        return categoria;
    }

    @Override
    public String update(Categoria cat) {

        String mensaje = "";

        try {
            BaseDatosMS base = new BaseDatosMS();
            String sql = "update categorias set nombrecat= ? where categoriaid = ?";
            PreparedStatement ps = base.getConnection().prepareStatement(sql);
            ps.setString(1, cat.getNombreCat());
            ps.setLong(2, cat.getCategoriaId());
            ps.executeUpdate();
            mensaje = "La categoria se ha actualizado exitosamente";
            base.desconectarBD();
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "No fue posible actualizar la categoria";
        }

        return mensaje;

    }

    @Override
    public String delete(Categoria cat) {
       String mensaje = "";
       
        try {
            BaseDatosMS conexion = new BaseDatosMS();
            
            String sql = "delete from categorias where categoriaid = ?";
  
            PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
            ps.setLong(1, cat.getCategoriaId());
            
            ps.executeUpdate();
            mensaje = "Se ha eliminado la categoria";
            conexion.desconectarBD();
            
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "No fue posible eliminar categoria " + e.getMessage();
        }
        
        return mensaje;
       
    }

}
