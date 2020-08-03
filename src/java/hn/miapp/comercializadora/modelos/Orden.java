package hn.miapp.comercializadora.modelos;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Orden {
    private long ordenId;
    private Empleado empleado;
    private Cliente cliente;
    private Date fechaOrden;
    private double descuento;
    private double importe;
    private String importeRedondeado;
    private List<DetalleOrden> detalles = new ArrayList<>();
    private List<DetalleOrden> detallesOrden;

    public Orden() {
    }

    
    
    public Orden(long ordenId, Empleado empleado, Cliente cliente, Date fechaOrden, double descuento, double importe) {
        this.ordenId = ordenId;
        this.empleado = empleado;
        this.cliente = cliente;
        this.fechaOrden = fechaOrden;
        this.descuento = descuento;
        this.importe = importe;
    }
    
    public Orden(long ordenId, Empleado empleado, Cliente cliente, Date fechaOrden, double descuento, double importe, String importeRedondeado) {
        this.ordenId = ordenId;
        this.empleado = empleado;
        this.cliente = cliente;
        this.fechaOrden = fechaOrden;
        this.descuento = descuento;
        this.importe = importe;
        this.importeRedondeado = importeRedondeado;
    }
    
    public long getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(long ordenId) {
        this.ordenId = ordenId;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getImporteRedondeado() {
        return new DecimalFormat("#.##").format(importe);
    }

    public void setImporteRedondeado(String importeRedondeado) {
        this.importeRedondeado = importeRedondeado;
    }

    public List<DetalleOrden> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleOrden> detalles) {
        this.detalles = detalles;
    }

    public List<DetalleOrden> getDetallesOrden() {
        return detallesOrden;
    }

    public void setDetallesOrden(List<DetalleOrden> detallesOrden) {
        this.detallesOrden = detallesOrden;
    }
  
}
