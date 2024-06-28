package com.health.finder.model;

import java.sql.SQLException;

/**
 *
 * @author eleani
 * @author herve
 * Clase diseñada para identificar errores relacionados con las restricciones de comprobación
 * en la base de datos.
 */
public class CheckConstraintError {
    /**
     * Constructor privado para evitar instanciar por fuera de la clase. Los métodos
     * de la clase son estáticos.
     */
    private CheckConstraintError() {

    }

    /**
     ** Verifica si el estado SQL es una excepción HY000 el cual es un identificador genérico para
     * errores del servidor. También verifica que el codigo no sea 3819 el cual es un error
     * de código en la comprobación de la base de datos.
     * @param error representa el error que ha ocurrido en la base de datos
     * @return true si el error es una violación de una restricción de comprobación
     */
    public static boolean isCheckConstraintViolation(SQLException error) {
        return error.getSQLState().equals("HY000") && error.getErrorCode() == 3819;
    }

}
