package com.health.finder.model.query;
import com.health.finder.model.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import static com.health.finder.model.CheckConstraintError.isCheckConstraintViolation;
/**
 *
 * @author eleani
 * @author herve
 * Clase que proporciona métodos para realizar consultas
 * y operaciones en la base de datos relacionadas
 * con las revisiones de los centros de salud.
 */
public class ReviewQueries extends DataBaseConnection {
    private static final Logger LOGGER = Logger.getLogger(ReviewQueries.class.getName());
    private static final String SQL_EXCEPTION_MESSAGE = "SQLException: ";
    /**
     * Publica una revisión en la base de datos.
     *
     * @param review La revisión a publicar.
     * @return `true` si la revisión se publicó correctamente, `false` de lo contrario.
     * @throws RatingCheckError Si la puntuación de la revisión no está dentro del rango permitido.
     */
    public boolean publishReview(Review review) throws RatingCheckError {

        String sql = "INSERT INTO `reviews` (`description`,`rating`,`publishDate`, `userId`, `healthCenterId`) VALUES (?,?,?,?,?)";

        try(Connection databaseConnection = getConnection();
            PreparedStatement query = databaseConnection.prepareStatement(sql)){
            query.setString(1, review.getDescription());
            query.setInt(2, review.getRating());
            query.setDate(3, review.getPublishDate());
            query.setInt(4, review.getUserId());
            query.setInt(5, review.getHealthCenterId());
            query.execute();
            return true;
        }catch(SQLException e){
            if (isCheckConstraintViolation(e))
                throw new RatingCheckError("La puntuación es de 1 a 5!");
            return false;
        }

    }
    /**
     * Obtiene una lista de revisiones asociadas a un centro de salud.
     *
     * @param healthCenterId El identificador del centro de salud.
     * @return Una lista de revisiones asociadas al centro de salud especificado.
     */

    public List<Review> getReviewsByHealthCenterId(int healthCenterId) {
        ResultSet resultSet;
        String sql = "SELECT reviewId, description, rating, publishDate, userId, healthCenterId FROM reviews WHERE healthCenterId = ?";

        try(Connection databaseConnection = getConnection();
            PreparedStatement query = databaseConnection.prepareStatement(sql)){

            query.setInt(1, healthCenterId);
            resultSet = query.executeQuery();
            List<Review> reviews = new LinkedList<>();
            while (resultSet.next()) {
                Review review = new Review();
                review.setReviewId(resultSet.getInt("reviewId"));
                review.setDescription(resultSet.getString("description"));
                review.setRating(resultSet.getInt("rating"));
                review.setPublishDate(resultSet.getDate("publishDate"));
                review.setUserId(resultSet.getInt("userId"));
                review.setHealthCenterId(resultSet.getInt("healthCenterId"));
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE+e);
            return Collections.emptyList();
        }
    }
    /**
     * Elimina todas las revisiones asociadas a un usuario.
     *
     * @param username El nombre de usuario del usuario cuyas revisiones se eliminarán.
     * @return `true` si se eliminaron las revisiones correctamente, `false` de lo contrario.
     */
    public boolean deleteReviewsByUsername(String username) {
        String sql = "DELETE FROM reviews WHERE userId = (SELECT userId FROM users WHERE username = ?)";

        try (Connection databaseConnection = getConnection();
             PreparedStatement query = databaseConnection.prepareStatement(sql)) {

            query.setString(1, username);
            int rowsAffected = query.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                LOGGER.info("No se encontraron reseñas para el usuario especificado.");
                return false;
            }

        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE + e);
            return false;
        }
    }
    /**
     * Elimina una revisión por su ID.
     *
     * @param reviewId El ID de la revisión a eliminar.
     * @return `true` si se eliminó la revisión correctamente, `false` de lo contrario.
     */

    public boolean deleteReviewByReviewId(int reviewId) {

        String sql = "DELETE FROM reviews WHERE reviewId = ?";

        try (Connection databaseConnection = getConnection();
             PreparedStatement query = databaseConnection.prepareStatement(sql)) {
             query.setInt(1, reviewId);
             int rowsAffected = query.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                LOGGER.info("No se encontró una reseña con el ID especificado.");
                return false;
            }
        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE + e);
            return false;
        }
    }
    /**
     * Actualiza una revisión existente en la base de datos.
     *
     * @param review La revisión actualizada.
     * @return `true` si la revisión se actualizó correctamente, `false` de lo contrario.
     * @throws RatingCheckError Si la puntuación de la revisión no está dentro del rango permitido.
     */
    public boolean updateReview(Review review) throws RatingCheckError {
        String sql = "UPDATE reviews SET description = ?, rating = ?, publishDate = ?, healthCenterId = ? WHERE reviewId = ?";

        try (Connection databaseConnection = getConnection();
             PreparedStatement query = databaseConnection.prepareStatement(sql)) {

            query.setString(1, review.getDescription());
            query.setInt(2, review.getRating());
            query.setDate(3, review.getPublishDate());
            query.setInt(4, review.getHealthCenterId());
            query.setInt(5, review.getReviewId());
            int rowsAffected = query.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                LOGGER.info("No se encontró la reseña para actualizar.");
                return false;
            }
        } catch (SQLException e) {
            if (isCheckConstraintViolation(e)) {
                throw new RatingCheckError("La puntuación es de 1 a 5!");
            }
            LOGGER.info(SQL_EXCEPTION_MESSAGE + e);
            return false;
        }
    }
    /**
     * Obtiene la información de calificación (total de puntuaciones y recuento de revisiones) para un centro de salud.
     *
     * @param healthCenterId El identificador del centro de salud.
     * @return Un objeto RatingInfo que contiene la información de calificación.
     */
    public RatingInfo getRatingInfoByHealthCenterId(int healthCenterId) {
        String sql = "SELECT SUM(rating) AS totalRating, COUNT(*) AS reviewCount FROM reviews WHERE healthCenterId = ?";

        try (Connection databaseConnection = getConnection();
             PreparedStatement query = databaseConnection.prepareStatement(sql)) {

            query.setInt(1, healthCenterId);
            ResultSet resultSet = query.executeQuery();

            if (resultSet.next()) {
                int totalRating = resultSet.getInt("totalRating");
                int reviewCount = resultSet.getInt("reviewCount");
                return new RatingInfo(totalRating, reviewCount);
            }
        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE + e);
        }
        return new RatingInfo(0, 0);
    }
    /**
     * Calcula la calificación promedio para un centro de salud.
     *
     * @param healthCenterId El identificador del centro de salud.
     * @return La calificación promedio del centro de salud.
     */
    public double calculateAverageRating(int healthCenterId) {
        RatingInfo ratingInfo = getRatingInfoByHealthCenterId(healthCenterId);
        if (ratingInfo.getReviewCount() == 0) {
            return 0.0;
        }
        return (double) ratingInfo.getTotalRating() / ratingInfo.getReviewCount();
    }
    /**
     * Filtra los centros de salud según la calificación especificada.
     *
     * @param healthCenters La lista de centros de salud a filtrar.
     * @param rating        La calificación por la que se filtrarán los centros de salud.
     * @return Una lista de centros de salud que tienen la calificación especificada.
     */
    public List<HealthCenter> filterHealthCentersByRating(List<HealthCenter> healthCenters, double rating) {
        return healthCenters.stream()
                .filter(healthCenter -> {
                    double averageRating = calculateAverageRating(healthCenter.getHealthCenterId());
                    return  Math.floor(averageRating) == rating;
                })
                .toList();
    }

    public List<HealthCenter> filterHealthCenters(List<HealthCenter> healthCenters, double rating, String languages) {
        LanguageQueries languageQueries = new LanguageQueries();
        return healthCenters.stream()
                .filter(healthCenter -> {
                    double averageRating = calculateAverageRating(healthCenter.getHealthCenterId());
                    boolean ratingMatch = Math.floor(averageRating) == rating;

                    // Obtiene los idiomas del centro de salud actual
                    List<String> healthCenterLanguages = languageQueries.getLanguagesByHealthCenterId(healthCenter.getHealthCenterId());

                    // Verifica si el rating coincide y si al menos uno de los idiomas seleccionados está presente en los idiomas del centro de salud
                    boolean languageMatch = languages.isEmpty() || containsAnyLanguage(healthCenterLanguages, languages);

                    return ratingMatch && languageMatch;
                })
                .toList();
    }

    // Método auxiliar para verificar si al menos uno de los idiomas seleccionados está presente en la lista de idiomas del centro de salud
    private boolean containsAnyLanguage(List<String> healthCenterLanguages, String selectedLanguages) {
        String[] selectedLanguageArray = selectedLanguages.split(",");
        for (String selectedLanguage : selectedLanguageArray) {
            if (healthCenterLanguages.contains(selectedLanguage.trim())) {
                return true;
            }
        }
        return false;
    }



}






