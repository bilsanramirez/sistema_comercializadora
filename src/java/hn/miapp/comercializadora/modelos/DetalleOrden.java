package hn.miapp.comercializadora.modelos;

import java.text.DecimalFormat;


public class DetalleOrden {
    
    private long detalleId;
    private Orden orden;
    private Producto producto;
    private double cantidad;
    private double importe;
    private String importeRedondeado;

    public DetalleOrden() {
    }

    public DetalleOrden(long detalleId, Producto producto, double cantidad, double importe) {
        this.detalleId = detalleId;
        this.producto = producto;
        this.cantidad = cantidad;
        this.importe = importe;
    }

    
    
    public DetalleOrden(long detalleId, Orden orden, Producto producto, double cantidad, double importe, String importeRedondeado) {
        this.detalleId = detalleId;
        this.orden = orden;
        this.producto = producto;
        this.cantidad = cantidad;
        this.importe = importe;
        this.importeRedondeado = importeRedondeado;
    }

    
    
    public DetalleOrden(long detalleId, Orden orden, Producto producto, double cantidad, double importe) {
        this.detalleId = detalleId;
        this.orden = orden;
        this.producto = producto;
        this.cantidad = cantidad;
        this.importe = importe;
    }

    public long getDetalleId() {
        return detalleId;
    }

    public void setDetalleId(long detalleId) {
        this.detalleId = detalleId;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
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
    
    
    
}
