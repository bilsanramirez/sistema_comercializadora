package hn.miapp.comercializadora.modelos;


public class DetalleOrden {
    private long detalleId;
    private long ordenId;
    private long productoId;
    private int cantidad;

    public long getDetalleId() {
        return detalleId;
    }

    public void setDetalleId(long detalleId) {
        this.detalleId = detalleId;
    }

    public long getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(long ordenId) {
        this.ordenId = ordenId;
    }

    public long getProductoId() {
        return productoId;
    }

    public void setProductoId(long productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
