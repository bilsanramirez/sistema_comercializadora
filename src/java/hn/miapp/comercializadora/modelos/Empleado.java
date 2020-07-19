package hn.miapp.comercializadora.modelos;

import java.util.Date;


public class Empleado {
    
    private long empleadoId;
    private String nombre;
    private String apellido;
    private Date fechaNac;
    private int reportaA;
    private int extencion;
    private String jefe;

    public Empleado() {
    }

    public Empleado(long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Empleado(long empleadoId,String nombre, String apellido, Date fechaNac, int reportaA, int extencion) {
        this.empleadoId = empleadoId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.reportaA = reportaA;
        this.extencion = extencion;
    }

    public Empleado(String nombre, String apellido, Date fechaNac, int reportaA, int extencion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.reportaA = reportaA;
        this.extencion = extencion;
    }
    
    

    public long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public int getReportaA() {
        return reportaA;
    }

    public void setReportaA(int reportaA) {
        this.reportaA = reportaA;
    }

    public int getExtencion() {
        return extencion;
    }

    public void setExtencion(int extencion) {
        this.extencion = extencion;
    }

    public String getJefe() {
        return jefe;
    }

    public void setJefe(String jefe) {
        this.jefe = jefe;
    }
    
    public String getNombreCompleto(){
    
        return nombre + " " + apellido;
    
    }
    
}
