package com.health.finder.model;
/**
 *
 * @author eleani
 * @author herve
 * Clase que representa un idioma.
 * Contiene un identificador único y el nombre del idioma.
 * Proporciona métodos para obtener y establecer estos valores.
 */
public class Language {
    private int languageId;
    private String languageName;
    /**
     * Constructor por defecto.
     * Inicializa una nueva instancia de la clase Language sin establecer valores.
     */
    public Language() {
         // El constructor esta vácio para proporcionar una forma de crear una instancia de Lenguage
        // sin inicializar los atributos de inmediato.
    }
    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }
}
