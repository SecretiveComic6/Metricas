package com.health.finder.model;
/**
 *
 * @author eleani
 * @author herve
 * Clase que representa una excepción lanzada cuando se encuentran datos inválidos.
 */
public class InvalidDataError extends Throwable{
    /**
     * Constructor que crea una nueva instancia de InvalidDataError con un mensaje de error específico.
     *
     * @param message El mensaje de error que describe el detalle de la excepción.
     */
    public InvalidDataError(String message){
        super(message);
    }
}
