package com.health.finder.model;
/**
 *
 * @author eleani
 * @author herve
 * Clase que representa una excepción lanzada cuando el nombre de un centro de salud ya existe.
 */
public class HealthCenterNameExistError extends Throwable{
    /**
     * Constructor que crea una nueva instancia de HealthCenterNameExistError con un mensaje de error específico.
     *
     * @param message El mensaje de error que describe el detalle de la excepción.
     */
    public HealthCenterNameExistError(String message){
        super(message);
    }
}
