package hn.miapp.comercializadora.dao;

import java.sql.PreparedStatement;
import hn.miapp.comercializadora.conexiones.BaseDatosMS;
import hn.miapp.comercializadora.modelos.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteJDBCDAO implements IClienteDao {

    @Override
    public List<Cliente> listAll() {
        Cliente cliente;
        List<Cliente> listaClientes = new ArrayList<>();
        BaseDatosMS conexion = new BaseDatosMS();

        try {
            String sql = "SELECT * FROM clientes";
            PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                long clienteId = rs.getLong("clienteid");
                String cedulaRuc = rs.getString("cedula_ruc");
                String nombreCia = rs.getString("nombrecia");
                String nombreContacto = rs.getString("nombrecontacto");
                String direccionCli = rs.getString("direccioncli");
                String fax = rs.getString("fax");
                String email = rs.getString("email");
                String celular = rs.getString("celular");
                String fijo = rs.getString("fijo");

                cliente = new Cliente(clienteId, cedulaRuc, nombreCia, nombreContacto, direccionCli, fax, email, celular, fijo);

                listaClientes.add(cliente);

            }

            conexion.desconectarBD();

        } catch (SQLException e) {
            System.out.println("Error en el metodo listAll() Clientes: " + e.getMessage());
            conexion.desconectarBD();
        }

        return listaClientes;

    }

    @Override
    public Cliente findById(Cliente cli) {

        BaseDatosMS conexion = new BaseDatosMS();
        Cliente cliente = null;

        try {
            String sql = "SELECT * FROM clientes WHERE clienteid = ?";
            PreparedStatement ps = conexion.getConnection().prepareCall(sql);
            ps.setLong(1, cli.getClienteId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                long clienteId = rs.getLong("clienteid");
                String cedulaRuc = rs.getString("cedula_ruc");
                String nombreCia = rs.getString("nombrecia");
                String nombreContacto = rs.getString("nombrecontacto");
                String direccionCli = rs.getString("direccioncli");
                String fax = rs.getString("fax");
                String email = rs.getString("email");
                String celular = rs.getString("celular");
                String fijo = rs.getString("fijo");
                
                cliente = new Cliente(clienteId, cedulaRuc, nombreCia, nombreContacto, direccionCli, fax, email, celular, fijo);
            }
            
            conexion.desconectarBD();

        } catch (SQLException e) {
            System.out.println("Error en el metodo findById de Clientes: " + e.getMessage());
            conexion.desconectarBD();
        }

        return cliente;
    }

    @Override
    public String insert(Cliente cli) {
        String mensaje = "";
        BaseDatosMS conexion = new BaseDatosMS();
        
        try {
            String sql = "INSERT INTO clientes (cedula_ruc, nombrecia, nombrecontacto, direccioncli, fax, email, celular, fijo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
            ps.setString(1, cli.getCedulaRuc());
            ps.setString(2, cli.getNombreCia());
            ps.setString(3, cli.getNombreContacto());
            ps.setString(4, cli.getDireccionCli());
            ps.setString(5, cli.getFax());
            ps.setString(6, cli.getEmail());
            ps.setString(7, cli.getCelular());
            ps.setString(8, cli.getFijo());
            
            ps.executeUpdate();
            
            mensaje = "Cliente insertado correctamente";
            
            conexion.desconectarBD();
            
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error en insertar cliente " + e.getMessage();
        }
        
        return mensaje;
        
    }

    @Override
    public String update(Cliente cli) {
        
        String mensaje = "";
        BaseDatosMS conecxion = new BaseDatosMS();
        
        try {
            String sql = "UPDATE clientes SET cedula_ruc = ?, nombrecia = ?, nombrecontacto = ?, direccioncli = ?, fax = ?, email = ?, celular = ?, fijo = ? WHERE clienteid = ?";
            PreparedStatement ps = conecxion.getConnection().prepareStatement(sql);
            ps.setString(1, cli.getCedulaRuc());
            ps.setString(2, cli.getNombreCia());
            ps.setString(3, cli.getNombreContacto());
            ps.setString(4, cli.getDireccionCli());
            ps.setString(5, cli.getFax());
            ps.setString(6, cli.getEmail());
            ps.setString(7, cli.getCelular());
            ps.setString(8, cli.getFijo());
            ps.setLong(9, cli.getClienteId());
            ps.executeUpdate();
            
            mensaje = "Cliente actualizado correctamente";
            
            conecxion.desconectarBD();
            
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error: en actulizar cliente: " + e.getMessage();
            conecxion.desconectarBD();
        }
        
        return mensaje;
    }

    @Override
    public String delete(Cliente cli) {
       String mensaje = "";
       BaseDatosMS conexion = new BaseDatosMS();
       
        try {
            String sql = "DELETE FROM clientes WHERE clienteid = ?";
            PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
            ps.setLong(1, cli.getClienteId());
            ps.executeUpdate();
            
            mensaje = "Cliente eliminado";
            
            conexion.desconectarBD();
       
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al eliminar cliente: " + e.getMessage();
            conexion.desconectarBD();
        }
        
        return mensaje;
    }

}
