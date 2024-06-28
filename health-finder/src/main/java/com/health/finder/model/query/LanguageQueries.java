package com.health.finder.model.query;

import com.health.finder.model.DataBaseConnection;
import com.health.finder.model.HealthCenter;
import com.health.finder.model.LanguageExistError;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import static com.health.finder.model.DuplicateKeyError.isDuplicateKeyException;
/**
 *
 * @author eleani
 * @author herve
 * Clase que proporciona métodos para realizar consultas
 * y operaciones en la base de datos relacionadas con los idiomas y
 * su asociación con los centros de salud.
 */
public class LanguageQueries extends DataBaseConnection {
    private static final Logger LOGGER = Logger.getLogger(LanguageQueries.class.getName());
    private static final String SQL_EXCEPTION_MESSAGE = "SQLException: ";
    /**
     * Obtiene una lista de nombres de todos los idiomas almacenados en la base de datos.
     *
     * @return Una lista de nombres de idiomas.
     */
    public List<String> getLanguagesName() {
        String sql = "SELECT language FROM languages ORDER BY language ASC;";

        try(Connection databaseConnection = getConnection();
            PreparedStatement query = databaseConnection.prepareStatement(sql)){

            ResultSet resultSet = query.executeQuery(sql);
            List<String> languages = new LinkedList<>();

            while (resultSet.next()) {
                String name= resultSet.getString("language");
                languages.add(name);
            }
            return languages;
        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE+e);
            return Collections.emptyList();

        }

    }
    /**
     * Obtiene el identificador de un idioma por su nombre.
     *
     * @param language El nombre del idioma.
     * @return El identificador del idioma o -1 si no se encuentra.
     */
    public int getLanguageIdByName(String language){
        String sql = "SELECT languageId FROM languages where language=?;";
        int languageId =-1;
        try(Connection databaseConnection = getConnection();
            PreparedStatement  query = databaseConnection.prepareStatement(sql)){
            query.setString(1, language);
            ResultSet resultSet = query.executeQuery();

            while (resultSet.next()) {
                languageId = resultSet.getInt("languageId");
            }

        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE+e);
        }
        return languageId;
    }
    /**
     * Agrega un idioma al centro de salud especificado.
     *
     * @param healthCenterId El identificador del centro de salud al que se agregará el idioma.
     * @param languageName   El nombre del idioma que se agregará.
     * @return `true` si se agregó el idioma correctamente, `false` de lo contrario.
     * @throws LanguageExistError Si el centro médico ya tiene este idioma asociado.
     */
    public boolean addLanguageToCurrentHealthCenter(int healthCenterId, String languageName) throws LanguageExistError {
        String sql = "INSERT INTO `healthcenter_speak` (`healthCenter_speakId`, `healthCenterId`, `languageId`) VALUES (?, ?, ?);";

        try (Connection databaseConnection = getConnection();
             PreparedStatement query = databaseConnection.prepareStatement(sql)){
            int languageId = getLanguageIdByName(languageName);
            String id = healthCenterId + "_" + languageId;

            query.setString(1, id);
            query.setInt(2, healthCenterId);
            query.setInt(3, languageId);
            query.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (isDuplicateKeyException(e)) {
                throw new LanguageExistError("El centro médico ya tiene este idioma!");
            }
            return false;
        }
    }

    /**
     * Obtiene una lista de nombres de idiomas asociados a un centro de salud.
     *
     * @param healthCenterId El identificador del centro de salud.
     * @return Una lista de nombres de idiomas asociados al centro de salud especificado.
     */
    public List<String> getLanguagesByHealthCenterId(int healthCenterId){
        String sql = "SELECT s.language FROM healthcenter_speak hs JOIN languages s ON hs.languageId = s.languageId WHERE hs.healthCenterId = ?;";

        try(Connection databaseConnection = getConnection();
             PreparedStatement query = databaseConnection.prepareStatement(sql)){

            query.setInt(1, healthCenterId);
            ResultSet resultSet = query.executeQuery();
            List<String>  languages = new LinkedList<>();
            while (resultSet.next()) {
                String language = resultSet.getString("language");
                languages.add(language);
            }
            return languages;
        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE+e);
            return Collections.emptyList();
        }
    }
    /**
     * Elimina un idioma del centro de salud especificado.
     *
     * @param healthCenterId El identificador del centro de salud del que se eliminará el idioma.
     * @param languageName   El nombre del idioma que se eliminará.
     * @return `true` si se eliminó el idioma correctamente, `false` de lo contrario.
     */
    public boolean deleteLanguageFromHealthCenter(int healthCenterId, String languageName){
        String sql = "DELETE FROM healthcenter_speak WHERE healthCenterId = ? AND languageId = ?;";


        try(Connection databaseConnection = getConnection();
            PreparedStatement query = databaseConnection.prepareStatement(sql)){
            query.setInt(1, healthCenterId);
            int languageId = getLanguageIdByName(languageName);
            query.setInt(2, languageId);
            int rowsAffected = query.executeUpdate();

            if (rowsAffected == 0) {
                LOGGER.info("No se encontró el idioma");
            }
            return true;
        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE+e);
            return false;
        }
    }
    /**
     * Filtra los centros de salud según el idioma especificado.
     *
     * @param healthCenters La lista de centros de salud a filtrar.
     * @param language      El idioma por el que se filtrarán los centros de salud.
     * @return Una lista de centros de salud que hablan el idioma especificado.
     */
    public List<HealthCenter> filterHealthCentersByLanguage(List<HealthCenter> healthCenters, String language) {
        return healthCenters.stream()
                .filter(healthCenter -> {
                    List<String> languages = getLanguagesByHealthCenterId(healthCenter.getHealthCenterId());
                    return languages.contains(language);
                })
                .toList();
    }


}
