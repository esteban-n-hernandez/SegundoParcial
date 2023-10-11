package edu.usal.biblioteca.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {

    Connection con;

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String bbdd = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String usuario = "root";
    private static final String clave = "Estu#180893";

    public Connection getConnection() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(bbdd, usuario,clave);
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos." + e.getMessage());
        }
        return con;
    }

}


