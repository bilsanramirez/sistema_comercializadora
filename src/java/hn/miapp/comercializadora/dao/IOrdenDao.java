package hn.miapp.comercializadora.dao;

import hn.miapp.comercializadora.modelos.Orden;
import java.util.List;

public interface IOrdenDao {
    public Orden insert(Orden orden);
    public List<Orden> listAll();
    public Orden findById(long id);
}


