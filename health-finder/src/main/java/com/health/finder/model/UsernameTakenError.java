package com.health.finder.model;
/**
 * Clase que representa una excepción lanzada cuando se intenta registrar un usuario con un nombre de usuario que ya está en uso.
 * Esta excepción permite manejar errores específicos relacionados con la duplicación de nombres de usuario.
 */
public class UsernameTakenError extends Throwable {
    /**
     * Constructor que crea una nueva instancia de UsernameTakenError con un mensaje de error específico.
     *
     * @param message El mensaje de error que describe el detalle de la excepción.
     */
    public UsernameTakenError(String message){
        super(message);
    }

}
