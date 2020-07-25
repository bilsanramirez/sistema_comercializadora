package hn.miapp.comercializadora.dao;

import hn.miapp.comercializadora.modelos.Categoria;
import hn.miapp.comercializadora.modelos.Producto;
import java.util.List;

public interface IProductoDao {
    public List<Producto> listAll();
    public Producto findById(long idProd);
    public String insert(Producto prod);
    public String update(Producto prod);
    public String delete(Producto prod);
    public List<Producto> getProductosByCategoria(Categoria cat);
}
