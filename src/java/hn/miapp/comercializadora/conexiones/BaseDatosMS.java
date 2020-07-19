package hn.miapp.comercializadora.conexiones;

import java.sql.*;

public class BaseDatosMS {

    Connection conn = null;

    public BaseDatosMS() {

        String urlDatabase = "jdbc:mysql://localhost:3306/pedidos";
        String usuario = "root";
        String password = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(urlDatabase, usuario, password);
        } catch (SQLException e) {
            System.out.println("Excepcion: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Excepcion no encontro driver: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void desconectarBD() {

        System.out.println("Cerrar conexion a base de datos");

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("No se realizo la desconexion: " + ex.getMessage());
            }
        }
    }
}
