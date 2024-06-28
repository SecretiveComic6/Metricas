package com.health.finder.model;

import java.sql.Date;
/**
 *
 * @author eleani
 * @author herve
 * Clase que representa una revisión realizada por un usuario sobre un centro de salud.
 */
public class Review {
    private int reviewId;
    private String description;
    private int rating;
    private java.sql.Date publishDate;
    private int userId;
    private int healthCenterId;
    /**
     *
     * Crea una nueva instancia de Review sin establecer valores para sus atributos.
     */
    public Review() {
    }
    /**
     * Constructor que inicializa una nueva instancia de Review con valores específicos.
     *
     * @param reviewId      El identificador de la revisión.
     * @param description   La descripción de la revisión.
     * @param rating        La calificación de la revisión.
     * @param publishDate   La fecha de publicación de la revisión.
     * @param userId        El identificador del usuario que realizó la revisión.
     * @param healthCenterId El identificador del centro de salud asociado a la revisión.
     */
    public Review(int reviewId, String description, int rating, Date publishDate, int userId, int healthCenterId) {
        this.reviewId = reviewId;
        this.description = description;
        this.rating = rating;
        this.publishDate = publishDate;
        this.userId = userId;
        this.healthCenterId = healthCenterId;
    }
    /**
     * Obtiene el identificador de la revisión.
     *
     * @return El identificador de la revisión.
     */
    public int getReviewId() {
        return reviewId;
    }
    /**
     * Establece el identificador de la revisión.
     *
     * @param reviewId El identificador de la revisión.
     */
    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }
   /**
     * Obtiene la descripción de la revisión.
     *
     * @return La descripción de la revisión.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Establece la descripción de la revisión.
     *
     * @param description La descripción de la revisión.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Obtiene la calificación de la revisión.
     *
     * @return La calificación de la revisión.
     */
    public int getRating() {
        return rating;
    }
    /**
     * Establece la calificación de la revisión.
     *
     * @param rating La calificación de la revisión.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }
    /**
     * Obtiene la fecha de publicación de la revisión.
     *
     * @return La fecha de publicación de la revisión.
     */
    public Date getPublishDate() {
        return publishDate;
    }
    /**
     * Establece la fecha de publicación de la revisión.
     *
     * @param publishDate La fecha de publicación de la revisión.
     */
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
    /**
     * Obtiene el identificador del usuario que realizó la revisión.
     *
     * @return El identificador del usuario que realizó la revisión.
     */
    public int getUserId() {
        return userId;
    }
    /**
     * Establece el identificador del usuario que realizó la revisión.
     *
     * @param userId El identificador del usuario que realizó la revisión.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**
     * Obtiene el identificador del centro de salud asociado a la revisión.
     *
     * @return El identificador del centro de salud asociado a la revisión.
     */
    public int getHealthCenterId() {
        return healthCenterId;
    }
    /**
     * Establece el identificador del centro de salud asociado a la revisión.
     *
     * @param healthCenterId El identificador del centro de salud asociado a la revisión.
     */
    public void setHealthCenterId(int healthCenterId) {
        this.healthCenterId = healthCenterId;
    }

}
