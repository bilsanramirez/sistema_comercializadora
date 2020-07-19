package hn.miapp.comercializadora.dao;

import java.sql.PreparedStatement;
import hn.miapp.comercializadora.conexiones.BaseDatosMS;
import hn.miapp.comercializadora.modelos.Proveedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProveedorJDBCDAO implements IProveedorDao {

    @Override
    public List<Proveedor> listAll() {
        BaseDatosMS base = new BaseDatosMS();
        Proveedor prov = null;
        List<Proveedor> listaProveedores = new LinkedList<>();

        try {
            String sql = "SELECT * FROM proveedores";

            PreparedStatement ps = base.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                prov = new Proveedor(rs.getLong("proveedorid"), rs.getString("nombreprov"), rs.getString("contacto"), rs.getString("celuprov"), rs.getString("fijoprov"));
                listaProveedores.add(prov);
            }

            base.desconectarBD();

        } catch (SQLException ex) {
            System.out.println("Error en el metodo listAll de ProveedorJDBCDAO: " + ex.getMessage());
            base.desconectarBD();
        }
        return listaProveedores;
    }

    @Override
    public String insert(Proveedor prov) {
        String mensaje = "";
        BaseDatosMS base = new BaseDatosMS();

        try {
            String sql = "INSERT INTO proveedores (nombreprov, contacto, celuprov, fijoprov) values (?, ?, ?, ?)";
            PreparedStatement ps = base.getConnection().prepareStatement(sql);
            ps.setString(1, prov.getNombreProv());
            ps.setString(2, prov.getContacto());
            ps.setString(3, prov.getCeluProv());
            ps.setString(4, prov.getFijoProv());

            ps.executeUpdate();

            mensaje = "Proveedor ingresado correctamente";

            base.desconectarBD();

        } catch (SQLException e) {
            e.printStackTrace();
            base.desconectarBD();
            mensaje = "Erro al insetar proveedor " + e.getMessage();
        }

        return mensaje;
    }

    @Override
    public String update(Proveedor prov) {

        String mensaje = "";
        BaseDatosMS base = new BaseDatosMS();

        try {

            String sql = "UPDATE proveedores SET nombreProv = ?, contacto = ?, celuProv = ?, fijoProv = ? WHERE proveedorid = ?";
            PreparedStatement ps = base.getConnection().prepareStatement(sql);
            ps.setString(1, prov.getNombreProv());
            ps.setString(2, prov.getContacto());
            ps.setString(3, prov.getCeluProv());
            ps.setString(4, prov.getFijoProv());
            ps.setLong(5, prov.getProveedorId());

            ps.executeUpdate();
            mensaje = "El proveedor fue actualizado correctamente";

            base.desconectarBD();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "No se pudo actualizar el proveedor" + e.getMessage();
            base.desconectarBD();
        }

        return mensaje;

    }

    @Override
    public String delete(Proveedor prov) {
        String mensaje = "";
        BaseDatosMS base = new BaseDatosMS();

        try {
            String sql = "DELETE FROM proveedores WHERE proveedorid = ?";
            PreparedStatement ps = base.getConnection().prepareStatement(sql);
            ps.setLong(1, prov.getProveedorId());

            ps.executeUpdate();
            mensaje = "El proveedor fue eliminado correctamente";

            base.desconectarBD();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "No se pudo eliminar el proveedor" + e.getMessage();
            base.desconectarBD();
        }

        return mensaje;
    }

    @Override
    public Proveedor findById(long idProveedor) {
        BaseDatosMS base = new BaseDatosMS();
        Proveedor prov = null;

        try {
            String sql = "SELECT * FROM proveedores WHERE proveedorid = ?";
          
            PreparedStatement ps = base.getConnection().prepareStatement(sql);
            ps.setLong(1, idProveedor);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                prov = new Proveedor(rs.getLong("proveedorid"), rs.getString("nombreprov"), rs.getString("contacto"), rs.getString("celuprov"), rs.getString("fijoprov"));
            }

            base.desconectarBD();

        } catch (SQLException ex) {
            System.out.println("Error en el metodo listAll de ProveedorJDBCDAO: " + ex.getMessage());
            base.desconectarBD();
        }
        return prov;
    }

}
