package com.health.finder.model;
/**
 *
 * @author eleani
 * @author herve
 * Clase que representa la información de calificación de un elemento.
 Contiene el total de calificaciones y la cantidad de revisiones.
 */
public class RatingInfo {
    private int totalRating;
    private int reviewCount;
    /**
     * Constructor que inicializa una nueva instancia de RatingInfo con valores específicos.
     *
     * @param totalRating El total de calificaciones del elemento.
     * @param reviewCount La cantidad de revisiones del elemento.
     */

    public RatingInfo(int totalRating, int reviewCount) {
        this.totalRating = totalRating;
        this.reviewCount = reviewCount;
    }
    /**
     * Obtiene el total de calificaciones del elemento.
     *
     * @return El total de calificaciones del elemento.
     */
    public int getTotalRating() {
        return totalRating;
    }
    /**
     * Obtiene la cantidad de revisiones del elemento.
     *
     * @return La cantidad de revisiones del elemento.
     */

    public int getReviewCount() {
        return reviewCount;
    }
      /**
     * Establece el total de calificaciones del elemento.
     *
     * @param totalRating El total de calificaciones del elemento.
     */

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }
    /**
     * Establece la cantidad de revisiones del elemento.
     *
     * @param reviewCount La cantidad de revisiones del elemento.
     */
    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }
}