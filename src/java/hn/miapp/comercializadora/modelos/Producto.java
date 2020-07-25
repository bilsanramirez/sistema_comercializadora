package hn.miapp.comercializadora.modelos;

public class Producto {

    private long productoId;
    private Proveedor proveedorId;
    private Categoria categoriaId;
    private String descripcion;
    private double precioUnit;
    private int existencia;

    public Producto() {
        
    }

    public Producto(long productoId) {
        this.productoId = productoId;
    }
    
    public Producto(long productoId, Proveedor proveedorId, Categoria categoriaId, String descripcion, double precioUnit, int existencia) {
        this.productoId = productoId;
        this.proveedorId = proveedorId;
        this.categoriaId = categoriaId;
        this.descripcion = descripcion;
        this.precioUnit = precioUnit;
        this.existencia = existencia;
    }

    public Producto(Proveedor proveedorId, Categoria categoriaId, String descripcion, double precioUnit, int existencia) {
        this.proveedorId = proveedorId;
        this.categoriaId = categoriaId;
        this.descripcion = descripcion;
        this.precioUnit = precioUnit;
        this.existencia = existencia;
    }

    
    public long getProductoId() {
        return productoId;
    }

    public void setProductoId(long productoId) {
        this.productoId = productoId;
    }

    public Proveedor getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Proveedor proveedorId) {
        this.proveedorId = proveedorId;
    }

    public Categoria getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Categoria categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioUnit() {
        return precioUnit;
    }

    public void setPrecioUnit(double precioUnit) {
        this.precioUnit = precioUnit;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }
}
