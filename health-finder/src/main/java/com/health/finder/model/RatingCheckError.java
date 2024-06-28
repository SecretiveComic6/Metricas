package com.health.finder.model;
/**
 *
 * @author eleani
 * @author herve
 * Clase que representa una excepción lanzada cuando se produce un error al verificar una calificación.
 */
public class RatingCheckError extends Throwable{
    /**
     * Constructor que crea una nueva instancia de RatingCheckError con un mensaje de error específico.
     *
     * @param message El mensaje de error que describe el detalle de la excepción.
     */
    public RatingCheckError(String message){
        super(message);
    }
}
