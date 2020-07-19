package hn.miapp.comercializadora.dao;

import hn.miapp.comercializadora.modelos.Categoria;
import java.util.List;

public interface ICategoriaDao {
    public List<Categoria> listAll();
    public String insert(Categoria cat);
    public Categoria findById(long id);
    public String update(Categoria cat);
    public String delete(Categoria cat);
}
