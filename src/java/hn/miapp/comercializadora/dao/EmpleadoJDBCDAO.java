package hn.miapp.comercializadora.dao;

import java.sql.PreparedStatement;
import hn.miapp.comercializadora.conexiones.BaseDatosMS;
import hn.miapp.comercializadora.modelos.Empleado;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;
import java.util.LinkedList;


public class EmpleadoJDBCDAO implements IEmpleadoDao {

    @Override
    public List<Empleado> listAll() {
        Empleado empleado = null;
        List<Empleado> listaEmpleados = new LinkedList<>();

        BaseDatosMS baseDatosMS = new BaseDatosMS();

        try {
            String sql = "SELECT * FROM empleados";
            PreparedStatement ps = baseDatosMS.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                long id = rs.getInt("empleadoid");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                Date fechaNac = rs.getDate("fecha_nac");
                int reportaA = rs.getInt("reporta_a");
                int extencion = rs.getInt("extension");

                empleado = new Empleado(id, nombre, apellido, fechaNac, reportaA, extencion);

                if (reportaA > 0) {
                    Empleado jefe = findById(reportaA);
                    empleado.setJefe(jefe.getNombreCompleto());
                }

                listaEmpleados.add(empleado);

            }

            baseDatosMS.desconectarBD();

        } catch (SQLException e) {
            System.out.println("Error en listALL de empleados: " + e.getMessage());
            baseDatosMS.desconectarBD();
        }

        return listaEmpleados;
    }

    @Override
    public String insert(Empleado emp) {
        String mensaje = "";
        BaseDatosMS base = new BaseDatosMS();
        
        try {
            String sql = "INSERT INTO empleados (nombre, apellido, fecha_nac, reporta_a, extension) VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement ps = base.getConnection().prepareStatement(sql);
            ps.setString(1, emp.getNombre());
            ps.setString(2, emp.getApellido());
            ps.setDate(3, (java.sql.Date) emp.getFechaNac());
            ps.setInt(4, emp.getReportaA());
            ps.setInt(5, emp.getExtencion());
            
            ps.executeUpdate();
            mensaje = "Empleado creado correctamente";
            
            base.desconectarBD();
            
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al crear empleado " + e.getMessage();
            System.out.println("Error al crear empleado " + e.getMessage());
            base.desconectarBD();
        }
        
        return mensaje;
        
    }

    @Override
    public String update(Empleado emp) {
        String mensaje = "";
        BaseDatosMS base = new BaseDatosMS();
        
        try {
            String sql = "UPDATE empleados SET nombre=?, apellido=?, fecha_nac=?, reporta_a=?, extension=? WHERE empleadoid = ?";
            
            PreparedStatement ps = base.getConnection().prepareStatement(sql);
            
            ps.setString(1, emp.getNombre());
            ps.setString(2, emp.getApellido());
            ps.setDate(3, (java.sql.Date) emp.getFechaNac());
            ps.setInt(4, emp.getReportaA());
            ps.setInt(5, emp.getExtencion());
            ps.setLong(6, emp.getEmpleadoId());
            
            ps.executeUpdate();
            mensaje = "Empleado actualizado correctamente";
            
            base.desconectarBD();
            
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al actualizar empleado " + e.getMessage();
            base.desconectarBD();
        }
        
        return mensaje;
    }

    @Override
    public String delete(Empleado emp) {
        String mensaje = "";
        BaseDatosMS base = new BaseDatosMS();
        
        try {
            String sql = "DELETE FROM empleados WHERE empleadoid = ?";
            
            PreparedStatement ps = base.getConnection().prepareStatement(sql);
            ps.setLong(1, emp.getEmpleadoId());
            ps.executeUpdate();
            
            mensaje = "Empleado eliminado";
            
            base.desconectarBD();
            
        } catch (SQLException e) {
            mensaje = "Error al eliminar usuario: " + e.getMessage();
            e.printStackTrace();
            base.desconectarBD();
        }
        
        return mensaje;
    }

    @Override
    public Empleado findById(long idEmpleado) {
        Empleado empleado = null;
        BaseDatosMS baseDatosMS = new BaseDatosMS();

        try {
            String sql = "SELECT * FROM empleados WHERE empleadoid = ? ";
            PreparedStatement ps = baseDatosMS.getConnection().prepareStatement(sql);
            ps.setLong(1, idEmpleado);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                long id = rs.getInt("empleadoid");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                Date fechaNac = rs.getDate("fecha_nac");
                int reportaA = rs.getInt("reporta_a");
                int extencion = rs.getInt("extension");

                empleado = new Empleado(id, nombre, apellido, fechaNac, reportaA, extencion);

                if (reportaA > 0) {
                    Empleado jefe = findById(reportaA);
                    empleado.setJefe(jefe.getNombreCompleto());
                }

            }

            baseDatosMS.desconectarBD();

        } catch (SQLException e) {
            e.printStackTrace();
            baseDatosMS.desconectarBD();
        }

        return empleado;

    }

}
