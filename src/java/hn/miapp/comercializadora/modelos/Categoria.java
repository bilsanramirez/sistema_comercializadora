package hn.miapp.comercializadora.modelos;

import java.util.List;

public class Categoria {
    
    private long categoriaId;
    private String nombreCat;
    private List<Producto> productos;

    public Categoria() {
    }
    
    public Categoria(long categoriaId){
        this.categoriaId = categoriaId;
    }

    public Categoria(long categoriaId, String nombreCat) {
        this.categoriaId = categoriaId;
        this.nombreCat = nombreCat;
    }

    public long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNombreCat() {
        return nombreCat;
    }

    public void setNombreCat(String nombreCat) {
        this.nombreCat = nombreCat;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
    
    
}
