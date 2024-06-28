package com.health.finder.model;
/**
 *
 * @author eleani
 * @author herve
 * Clase que representa una especialidad médica en el contexto de un centro de salud.
 */
public class Specialty {
    private int specialtyId;
    private String specialtyName;
    /**
     * Crea una nueva instancia de Specialty sin establecer valores para sus atributos.
     */
    public Specialty() {

    }

    /**
     * Obtiene el identificador único de la especialidad.
     *
     * @return El identificador único de la especialidad.
     */
    public int getSpecialtyId() {
        return specialtyId;
    }
    /**
     * Establece el identificador único de la especialidad.
     *
     * @param specialtyId El identificador único de la especialidad.
     */
    public void setSpecialtyId(int specialtyId) {
        this.specialtyId = specialtyId;
    }
    /**
     * Obtiene el nombre de la especialidad.
     *
     * @return El nombre de la especialidad.
     */
    public String getSpecialtyName() {
        return specialtyName;
    }
    /**
     * Establece el nombre de la especialidad.
     *
     * @param specialtyName El nombre de la especialidad.
     */
    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }
}
