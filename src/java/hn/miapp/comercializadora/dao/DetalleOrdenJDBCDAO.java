package hn.miapp.comercializadora.dao;

import java.sql.PreparedStatement;
import hn.miapp.comercializadora.conexiones.BaseDatosMS;
import hn.miapp.comercializadora.modelos.DetalleOrden;
import hn.miapp.comercializadora.modelos.Orden;
import hn.miapp.comercializadora.modelos.Producto;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleOrdenJDBCDAO implements IDetalleOrdenDao{

    @Override
    public List<DetalleOrden> listAll(Orden orden) {
        Producto producto = null;
        DetalleOrden detalle = null;
                
        List<DetalleOrden> detalles = new ArrayList<>();
        BaseDatosMS conexion = new BaseDatosMS();
        ProductoJDBCDAO daoProducto = new ProductoJDBCDAO();
        
        try {
            String sql = "SELECT * FROM detalle_ordenes WHERE ordenid = ?";
            PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
            ps.setLong(1, orden.getOrdenId());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
            
                long detalleId = rs.getLong("detalleid");
                long productoId = rs.getLong("productoid");
                int cantidad = rs.getInt("cantidad");
                BigDecimal importe = rs.getBigDecimal("importe");
                
                producto = daoProducto.findById(productoId);
                
                detalle = new DetalleOrden(detalleId, orden, producto, cantidad, importe.doubleValue());
                 
                detalles.add(detalle);
            }
            
            conexion.desconectarBD();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
        
            if(conexion.getConnection() != null){
            
                conexion.desconectarBD();
                
            }
            
        }
        
        return detalles;
        
    }
    
}
