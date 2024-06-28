package com.health.finder.model.query;

import com.health.finder.model.DataBaseConnection;
import com.health.finder.model.SpecialtyExistError;

import java.sql.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import static com.health.finder.model.DuplicateKeyError.isDuplicateKeyException;
/**
 *
 * @author eleani
 * @author herve
 * Clase que proporciona métodos para realizar consultas y operaciones
 * en la base de datos relacionadas con las especialidades médicas de los centros de salud.
 */
public class SpecialtyQueries extends DataBaseConnection {
    private static final Logger LOGGER = Logger.getLogger(SpecialtyQueries.class.getName());
    private static final String SQL_EXCEPTION_MESSAGE = "SQLException: ";
    /**
     * Agrega una especialidad a un centro de salud en la base de datos.
     *
     * @param healthCenterId El ID del centro de salud al que se agregará la especialidad.
     * @param specialtyName  El nombre de la especialidad a agregar.
     * @return `true` si la especialidad se agregó correctamente, `false` de lo contrario.
     * @throws SpecialtyExistError Si la especialidad ya existe para el centro de salud especificado.
     */
    public boolean addSpecialtyToHealthCenter(int healthCenterId, String specialtyName) throws SpecialtyExistError {
        String sql = "INSERT INTO `healthcenter_specialties` (`healthCenter_specialtiesId`, `healthCenterId`, `specialtyId`) VALUES (?, ?, (SELECT specialtyId FROM specialties WHERE specialty = ?));";

        try (Connection databaseConnection = getConnection();
             PreparedStatement query = databaseConnection.prepareStatement(sql)) {
            int specialtyId = getSpecialtyIdByName(specialtyName);
            String id = healthCenterId + "_" + specialtyId;
            query.setString(1, id);
            query.setInt(2, healthCenterId);
            query.setString(3, specialtyName);
            query.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE + e);
            if (isDuplicateKeyException(e)) {
                throw new SpecialtyExistError("El centro médico ya tiene esta especialidad!");
            }
            return false;
        }
    }

    public boolean deleteSpecialtyFromHealthCenter(int healthCenterId, String specialtyName) {
        int specialtyId = getSpecialtyIdByName(specialtyName);
        String sql = "DELETE FROM healthcenter_specialties WHERE healthCenterId = ? AND specialtyId = ?;";
        try (Connection databaseConnection = getConnection();
             PreparedStatement query = databaseConnection.prepareStatement(sql)) {

            query.setInt(1, healthCenterId);
            query.setInt(2, specialtyId);
            int rowsAffected = query.executeUpdate();

            if (rowsAffected == 0) {
                LOGGER.info("Especialidad no existe");
            }
            return true;
        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE + e);
            return false;
        }
    }
   /**
     * Obtiene una lista de nombres de todas las especialidades médicas disponibles.
     *
     * @return Una lista de nombres de especialidades médicas ordenados alfabéticamente.
     */
    public List<String> getSpecialtiesList() {
        String sql = "SELECT specialty FROM specialties ORDER BY specialty ASC;";
        try(Connection databaseConnection = getConnection();
            PreparedStatement query = databaseConnection.prepareStatement(sql)){

            ResultSet resultSet = query.executeQuery(sql);

            List<String> specialtiesName = new LinkedList<>();
            while (resultSet.next()) {
                String specialty = resultSet.getString("specialty");
                specialtiesName.add(specialty);
            }
            return specialtiesName;
        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE+e);
            return Collections.emptyList();

        }

    }
    /**
     * Obtiene el ID de una especialidad médica por su nombre.
     *
     * @param specialtyName El nombre de la especialidad médica.
     * @return El ID de la especialidad médica, o -1 si no se encuentra.
     */
    public int getSpecialtyIdByName(String specialtyName){
        String sql = "SELECT specialtyId FROM specialties where specialty=?;";
        int specialtyId=-1;

        try (Connection databaseConnection = getConnection();
             PreparedStatement query = databaseConnection.prepareStatement(sql)) {

            query.setString(1, specialtyName);
            ResultSet resultSet = query.executeQuery();

            if(resultSet.next()) {
                specialtyId = resultSet.getInt("specialtyId");
            }

        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE+e);

        }
        return specialtyId;
    }
    /**
     * Obtiene una lista de nombres de especialidades médicas asociadas a un centro de salud.
     *
     * @param healthCenterId El ID del centro de salud.
     * @return Una lista de nombres de especialidades médicas asociadas al centro de salud especificado.
     */
    public List<String> getSpecialtiesByHealthCenterId(int healthCenterId){
        String sql = "SELECT s.specialty FROM healthcenter_specialties hs JOIN specialties s ON hs.specialtyId = s.specialtyId WHERE hs.healthCenterId = ?;";

        try(Connection databaseConnection = getConnection();
            PreparedStatement query= databaseConnection.prepareStatement(sql)) {
            query.setInt(1, healthCenterId);
            ResultSet resultSet = query.executeQuery();
            List<String> specialtiesName = new LinkedList<>();
            while (resultSet.next()) {
                String specialty = resultSet.getString("specialty");
                specialtiesName.add(specialty);
            }
            return specialtiesName;
        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE+e);
            return Collections.emptyList();

        }

    }


}
