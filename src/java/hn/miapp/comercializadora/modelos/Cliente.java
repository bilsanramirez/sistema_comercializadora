package hn.miapp.comercializadora.modelos;

public class Cliente {
    private long clienteId;
    private String cedulaRuc;
    private String nombreCia;
    private String nombreContacto;
    private String direccionCli;
    private String fax;
    private String email;
    private String celular;
    private String fijo;

    public Cliente() {
    }

    public Cliente(long clienteId) {
        this.clienteId = clienteId;
    }

    public Cliente(long clienteId, String cedulaRuc, String nombreCia, String nombreContacto, String direccionCli, String fax, String email, String celular, String fijo) {
        this.clienteId = clienteId;
        this.cedulaRuc = cedulaRuc;
        this.nombreCia = nombreCia;
        this.nombreContacto = nombreContacto;
        this.direccionCli = direccionCli;
        this.fax = fax;
        this.email = email;
        this.celular = celular;
        this.fijo = fijo;
    }

    public Cliente(String cedulaRuc, String nombreCia, String nombreContacto, String direccionCli, String fax, String email, String celular, String fijo) {
        this.cedulaRuc = cedulaRuc;
        this.nombreCia = nombreCia;
        this.nombreContacto = nombreContacto;
        this.direccionCli = direccionCli;
        this.fax = fax;
        this.email = email;
        this.celular = celular;
        this.fijo = fijo;
    }
    
    

    public String getFijo() {
        return fijo;
    }

    public void setFijo(String fijo) {
        this.fijo = fijo;
    }

    public long getClienteId() {
        return clienteId;
    }

    public void setClienteId(long clienteId) {
        this.clienteId = clienteId;
    }

    public String getCedulaRuc() {
        return cedulaRuc;
    }

    public void setCedulaRuc(String cedulaRuc) {
        this.cedulaRuc = cedulaRuc;
    }

    public String getNombreCia() {
        return nombreCia;
    }

    public void setNombreCia(String nombreCia) {
        this.nombreCia = nombreCia;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getDireccionCli() {
        return direccionCli;
    }

    public void setDireccionCli(String direccionCli) {
        this.direccionCli = direccionCli;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
