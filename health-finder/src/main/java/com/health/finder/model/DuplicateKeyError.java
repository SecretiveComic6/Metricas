package com.health.finder.model;

import java.sql.SQLException;
/**
 *
 * @author eleani
 * @author herve
 * Clase para identificar errores realcionados con claves duplicadas en la base de datos.
 */
public class DuplicateKeyError {
    /**
     * Constructor privado para evitar instanciar por fuera de la clase. Los métodos
     * de la clase son estáticos.
     */
    private DuplicateKeyError() {

    }
    /**
     * Verifica si una SQLException es una excepción de clave duplicada.
     *
     * @param error devuelve una instancia de SQLException que representa el error que ha ocurrido en la base de datos.
     * @return true si el error es una excepción de clave duplicada, false en caso contrario.
     */
    public static boolean isDuplicateKeyException(SQLException error){
        return(error.getSQLState().equals("23000") && error.getErrorCode() == 1062);
    }
}
