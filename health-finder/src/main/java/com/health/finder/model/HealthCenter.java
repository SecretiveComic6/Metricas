package com.health.finder.model;
import java.sql.Date;

/**
 *
 * @author eleani
 * @author herve
 * Clase diseñada para representar un centro de salud, proprociona métodos para obtener y establecer
 * los valores de estos atributos.
 */


public class HealthCenter {
    private int healthCenterId;
    private String name;
    private String about;
    private String address;
    private String telephone;
    private java.sql.Date uploadDate;
    private String coverImage;
    
    public HealthCenter() {
        // El constructor esta vácio para proporcionar una forma de crear una instancia de HealthCenter
        // sin inicializar los atributos de inmediato.
    }
    /**
     * Obtiene el ID del centro de salud.
     *
     * @return healthCenterId El ID del centro de salud.
     */
    public int getHealthCenterId() {
        return healthCenterId;
    }
    /**
     * Establece el ID del centro de salud.
     *
     * @param healthCenterId El ID del centro de salud.
     */
    public void setHealthCenterId(int healthCenterId) {
        this.healthCenterId = healthCenterId;
    }
    /**
     * Obtiene la descripción del centro de salud.
     *
     * @return about La descripción del centro de salud.
     */
    public String getAbout() {
        return about;
    }
    /**
     * Establece la descripción del centro de salud.
     *
     * @param about La descripción del centro de salud.
     */
    public void setAbout(String about) {
        this.about = about;
    }
    /**
     * Obtiene la dirección del centro de salud.
     *
     * @return address La dirección del centro de salud.
     */
    public String getAddress() {
        return address;
    }
    /**
     * Establece la dirección del centro de salud.
     *
     * @param address La dirección del centro de salud.
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Obtiene el teléfono del centro de salud.
     *
     * @return telephone El teléfono del centro de salud.
     */
    public String getTelephone() {
        return telephone;
    }
    /**
     * Establece el teléfono del centro de salud.
     *
     * @param telephone El teléfono del centro de salud.
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    /**
     * Obtiene la fecha de carga de la información del centro de salud.
     *
     * @return uploadDate La fecha de carga de la información del centro de salud.
     */
    public Date getUploadDate() {
        return uploadDate;
    }
    /**
     * Establece la fecha de carga de la información del centro de salud.
     *
     * @param uploadDate La fecha de carga de la información del centro de salud.
     */
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
    /**
     * Obtiene la imagen de portada del centro de salud.
     *
     * @return coverImage La imagen de portada del centro de salud.
     */
    public String getCoverImage() {
        return coverImage;
    }
    /**
     * Establece la imagen de portada del centro de salud.
     *
     * @param coverImage La imagen de portada del centro de salud.
     */
    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
    /**
     * Obtiene el nombre del centro de salud.
     *
     * @return name El nombre del centro de salud.
     */
    public String getName() {
        return name;
    }
    /**
     * Establece el nombre del centro de salud.
     *
     * @param name El nombre del centro de salud.
     */
    public void setName(String name) {
        this.name = name;
    }
}
