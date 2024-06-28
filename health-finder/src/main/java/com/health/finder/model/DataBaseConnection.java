package com.health.finder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;
/**
 *
 * @author eleani
 * @author herve
 * Clase para gestionar la conexión a la base de datos, estableciendo constantes nombradas para realizar
 la conexión en el método Connection, para mejor legibilidad.
 */
public class DataBaseConnection {
    private static final String DATABASE = "health_finder";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE;
    private static final String USER ="root";
    private static final String PASSWORD = "";
    private Connection connection = null;
    private static final Logger LOGGER = Logger.getLogger(DataBaseConnection.class.getName());
    /**
     * Establece y devuelve una conexión a la base de datos.
     * 
     * @return una conexión a la base de datos, o null si ocurre un error.
     */
    public Connection getConnection(){
        try {
            connection =  DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception error) {
           LOGGER.info("Ocurrió un error: "+error);
        }
        return connection;
    }
}