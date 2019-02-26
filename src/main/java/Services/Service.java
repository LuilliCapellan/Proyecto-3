package Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Service {
    private static Service service = null;
    private static String URL = "jdbc:h2:tcp://localhost/~/practica3";

    public Service() {
    }

    public static Service getService() {
        if (service == null) {
            service = new Service();
        }
        return service;
    }

    private void registrarDriver() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection _conn = null;
        try {
            _conn = DriverManager.getConnection(URL, "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return _conn;
    }

    public void testConnection() {
        try {
            getConnection().close();
            System.out.println("Logro conectarse");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
