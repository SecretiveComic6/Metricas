package com.health.finder.model;
/**
 *
 * @author eleani
 * @author herve
 * Clase que representa una excepción lanzada cuando se intenta agregar un idioma que ya existe.
 */
public class LanguageExistError extends Throwable{
    /**
     * Constructor que crea una nueva instancia de LanguageExistError con un mensaje de error específico.
     *
     * @param message El mensaje de error que describe el detalle de la excepción.
     */
    public LanguageExistError(String message){
        super(message);
    }

}
