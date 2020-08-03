package hn.miapp.comercializadora.dao;

import hn.miapp.comercializadora.modelos.DetalleOrden;
import hn.miapp.comercializadora.modelos.Orden;
import java.util.List;

public interface IDetalleOrdenDao {
    List<DetalleOrden> listAll(Orden orden);
}
