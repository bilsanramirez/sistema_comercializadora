package hn.miapp.comercializadora.dao;

import com.mysql.jdbc.Statement;
import hn.miapp.comercializadora.conexiones.BaseDatosMS;
import hn.miapp.comercializadora.modelos.Cliente;
import hn.miapp.comercializadora.modelos.DetalleOrden;
import hn.miapp.comercializadora.modelos.Empleado;
import hn.miapp.comercializadora.modelos.Orden;
import hn.miapp.comercializadora.modelos.Producto;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdenJDBCDAO implements IOrdenDao {

    @Override
    public List<Orden> listAll() {

        Orden orden = null;
        List<Orden> listaOrdenes = new ArrayList<>();
        BaseDatosMS conexion = new BaseDatosMS();

        try {

            String sql = "SELECT * FROM ordenes";
            PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            EmpleadoJDBCDAO daoEmpleado = new EmpleadoJDBCDAO();
            ClienteJDBCDAO daoCliente = new ClienteJDBCDAO();

            while (rs.next()) {

                long ordenId = rs.getLong("ordenid");
                long empleadoId = rs.getLong("empleadoid");
                long clienteId = rs.getLong("clienteid");
                Date fechaOrden = rs.getDate("fechaorden");
                double descuento = rs.getDouble("descuento");
                double importe = rs.getDouble("importe");

                Empleado empleado = daoEmpleado.findById(empleadoId);
                Cliente cliente = daoCliente.findById(new Cliente(clienteId));

                if (empleado != null || cliente != null) {
                    orden = new Orden(ordenId, empleado, cliente, fechaOrden, descuento, importe);
                    listaOrdenes.add(orden);
                }
                
            }

            conexion.desconectarBD();

        } catch (SQLException e) {
            System.err.println("Error en el listAll de ordenes" + e.getMessage());
            e.printStackTrace();
            conexion.desconectarBD();
        }
        finally{
            try {
                conexion.desconectarBD();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
        return listaOrdenes;
    }

    @Override
    public Orden insert(Orden orden) {
        String mensaje = "";
        BaseDatosMS conexion = new BaseDatosMS();

        try {
            int idGenerado = 0;

            conexion.getConnection().setAutoCommit(false);

            String sql = "INSERT INTO ordenes (empleadoid, clienteid, fechaorden, descuento, importe) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = conexion.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, orden.getEmpleado().getEmpleadoId());
            ps.setLong(2, orden.getCliente().getClienteId());
            ps.setDate(3, (Date) orden.getFechaOrden());
            ps.setDouble(4, orden.getDescuento());
            ps.setBigDecimal(5, new BigDecimal(orden.getImporte()));

            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {

                idGenerado = generatedKeys.getInt(1);

            }

            PreparedStatement ps2;

            for (DetalleOrden ordenDetalle : orden.getDetalles()) {

                String sqlDetalles = "INSERT INTO detalle_ordenes (ordenid, productoid, cantidad, importe) VALUES (?, ?, ?, ?)";

                ps2 = conexion.getConnection().prepareStatement(sqlDetalles);
                ps2.setLong(1, idGenerado);
                ps2.setLong(2, ordenDetalle.getProducto().getProductoId());
                ps2.setDouble(3, ordenDetalle.getCantidad());
                ps2.setBigDecimal(4, new BigDecimal(ordenDetalle.getImporte()));

                ps2.executeUpdate();

            }

            mensaje = "El pedido se ha creado correctamente";
            conexion.getConnection().commit();

            orden.setOrdenId(idGenerado);

        } catch (SQLException ex) {

            if (conexion.getConnection() != null) {
                try {
                    System.err.print("La transaccion no pudo realizarse");
                    conexion.getConnection().rollback();
                } catch (SQLException e) {
                    System.err.println("No pudo hacerse el rollback de la transaccion");
                }
            }

            ex.printStackTrace();
            mensaje = "No fue posibles registrar productos: " + ex.getMessage();
            conexion.desconectarBD();

        } finally {

            if (conexion.getConnection() != null) {

                conexion.desconectarBD();

            }

        }

        return orden;

    }

    @Override
    public Orden findById(long id) {

        BaseDatosMS conexion = new BaseDatosMS();
        EmpleadoJDBCDAO daoEmpleado = new EmpleadoJDBCDAO();
        ClienteJDBCDAO daoCliente = new ClienteJDBCDAO();

        Orden orden = null;
        Empleado empleado = null;
        Cliente cliente = null;

        List<DetalleOrden> listaDetalles = new ArrayList<>();

        try {

            String sql = "SELECT * FROM ordenes WHERE ordenid = ?";
            PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                long ordenId = rs.getLong("ordenid");
                long empleadoId = rs.getLong("empleadoid");
                long clienteId = rs.getLong("clienteid");
                Date fechaOrden = rs.getDate("fechaorden");
                double descuento = rs.getDouble("descuento");
                BigDecimal importe = rs.getBigDecimal("importe");

                empleado = daoEmpleado.findById(empleadoId);
                cliente = daoCliente.findById(new Cliente(clienteId));

                orden = new Orden(ordenId, empleado, cliente, fechaOrden, descuento, importe.doubleValue());

            }
            
            conexion.desconectarBD();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error: " + ex.getMessage());
        }
        finally{
            if(conexion.getConnection() != null){
                conexion.desconectarBD();   
            }
        }

        return orden;

    }

}
