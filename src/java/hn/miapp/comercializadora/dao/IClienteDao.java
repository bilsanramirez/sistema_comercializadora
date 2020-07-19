package hn.miapp.comercializadora.dao;

import hn.miapp.comercializadora.modelos.Cliente;
import java.util.List;

public interface IClienteDao {
    
    public List<Cliente> listAll();
    public Cliente findById(Cliente cli);
    public String insert(Cliente cli);
    public String update(Cliente cli);
    public String delete(Cliente cli);
    
}
