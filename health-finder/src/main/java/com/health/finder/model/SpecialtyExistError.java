package com.health.finder.model;
/**
 *
 * @author eleani
 * @author herve
 * Clase que representa una excepción lanzada cuando se intenta agregar una especialidad que ya existe.
 */
public class SpecialtyExistError extends Throwable {
    /**
     * Constructor que crea una nueva instancia de SpecialtyExistError con un mensaje de error específico.
     *
     * @param message El mensaje de error que describe el detalle de la excepción.
     */
    public SpecialtyExistError(String message){
        super(message);
    }
}
