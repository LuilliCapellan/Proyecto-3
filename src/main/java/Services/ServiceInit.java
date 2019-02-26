package Services;

import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceInit {
    public static void iniciaDb() throws SQLException {
        Server.createTcpServer("-tcpPort", "2129", "-tcpAllowOthers").start();
    }

    public static void stopDb() throws SQLException {
        Server.shutdownTcpServer("tcp://localhost:2129", "", true, true);
    }

    public static void createTable() throws SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS USUARIO (\n" +
                "    NOMBRE_USUARIO VARCHAR(124) PRIMARY KEY NOT NULL,\n" +
                "    NOMBRE VARCHAR(100) NOT NULL,\n" +
                "    PASSWORD VARCHAR(100) NOT NULL,\n" +
                "    ADMINISTRADOR BOOL NOT NULL,\n" +
                "    AUTOR BOOL NOT NULL )\n" +
                "CREATE TABLEIF NOT EXISTS ARTICULO (\n" +
                "    CODIGO INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,\n" +
                "    TITULO VARCHAR(464) NOT NULL,\n" +
                "    CUERPO LONGTEXT NOT NULL,\n" +
                "    FECHA_PUBLICACION DATETIME NOT NULL,\n" +
                "    AUTOR VARCHAR(464) NOT NULL\n" +
                ")\n" +
                "CREATE TABLE IF NOT EXISTS ETIQUETA(\n" +
                "    CODIGO INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,\n" +
                "    NOMBRE VARCHAR (256) NOT NULL,\n" +
                "    ARTICULO INTEGER NOT NULL\n" +
                ")\n" +
                "CREATE TABLE IF NOT EXISTS COMENTARIO(\n" +
                "    CODIGO INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,\n" +
                "    COMENTARIO VARCHAR(4096) NOT NULL,\n" +
                "    ARTICULO INTEGER NOT NULL,\n" +
                "    AUTOR VARCHAR(456) NOT NULL\n" +
                ")\n" +
                "CREATE TABLE IF NOT EXISTS ARTICULO_ETIQUETA(\n" +
                "    ID_A_E INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,\n" +
                "    CODIGO_ARTICULO INTEGER NOT NULL,\n" +
                "    CODIGO_ETIQUETA INTEGER NOT NULL \n" +
                ")\n" +
                "\n" +
                "ALTER TABLE COMENTARIO ADD FOREIGN KEY (AUTOR) REFERENCES USUARIO (NOMBRE_USUARIO);\n" +
                "ALTER TABLE ARTICULO ADD FOREIGN KEY (AUTOR) REFERENCES USUARIO (NOMBRE_USUARIO);\n" +
                "ALTER TABLE COMENTARIO ADD FOREIGN KEY (ARTICULO) REFERENCES ARTICULO (CODIGO);\n" +
                "ALTER TABLE ETIQUETA ADD FOREIGN KEY (ARTICULO) REFERENCES ARTICULO (CODIGO);\n" +
                "MERGE INTO USUARIO KEY (NOMBRE_USUARIO) VALUES ('Lugi', 'Luis Capellan', 'Capellan123', TRUE, TRUE);";
        Connection _conn = Service.getConnection();
        Statement stm = _conn.createStatement();
        stm.execute(sql);
        _conn.close();
    }
}