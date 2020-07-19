package hn.miapp.comercializadora.dao;

import hn.miapp.comercializadora.modelos.Empleado;
import java.util.List;

public interface IEmpleadoDao {
    
    public List<Empleado> listAll();
    public String insert(Empleado emp);
    public String update(Empleado emp);
    public String delete(Empleado emp);
    public Empleado findById(long idEmpleado);
    
}
