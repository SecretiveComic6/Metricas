package com.health.finder.model.query;

import com.health.finder.model.*;
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
 * Clase que proporciona metodos para realizar consultas y operaciones
 * en la base de datos relacionadas con los centros de salud.
 */
public class HealthCenterQueries extends DataBaseConnection {
    private static final Logger LOGGER = Logger.getLogger(HealthCenterQueries.class.getName());
    private static final String SQL_EXCEPTION_MESSAGE = "SQLException: ";
    private static final String HEALTH_CENTER_ID = "healthCenterId";
    private static final String NAME = "name";
    private static final String TELEPHONE = "telephone";
    private static final String ADDRESS ="address";
    private static final String UPLOAD_DATE = "uploadDate";
    private static final String COVER_IMAGE = "coverImage";
    private static final String ABOUT= "about";

    /**
     * Agrega un nuevo centro de salud a la base de datos.
     *
     * @param healthCenter El centro de salud que se va a agregar.
     * @return `true` si el centro de salud se agrego correctamente, `false` de lo contrario.
     * @throws HealthCenterNameExistError Si ya existe un centro de salud con el mismo nombre en la base de datos.
     */
    public boolean addHealthCenter(HealthCenter healthCenter) throws HealthCenterNameExistError {
        String sql = "INSERT INTO `health_centers` (`about`,`address`,`telephone`, `uploadDate`, `coverImage`, `name`) VALUES (?,?,?,?,?,?)";

        try (Connection databaseConnection = getConnection();
             PreparedStatement query = databaseConnection.prepareStatement(sql)){

            query.setString(1, healthCenter.getAbout());
            query.setString(2, healthCenter.getAddress());
            query.setString(3, healthCenter.getTelephone());
            query.setDate(4, healthCenter.getUploadDate());
            query.setString(5, healthCenter.getCoverImage());
            query.setString(6, healthCenter.getName());
            query.execute();
            return true;
        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE+e);
            if (isDuplicateKeyException(e))
                throw new HealthCenterNameExistError("Este centro médico ya existe!");
            return false;
        }

    }

    public boolean updateHealthCenter(HealthCenter healthCenter)  {
        String sql = "UPDATE `health_centers` SET `about`=?, `address`=?, `telephone`=?, `uploadDate`=?, `coverImage`=? WHERE `name`=?";

        try (Connection databaseConnection = getConnection();
             PreparedStatement query = databaseConnection.prepareStatement(sql)){

            query.setString(1, healthCenter.getAbout());
            query.setString(2, healthCenter.getAddress());
            query.setString(3, healthCenter.getTelephone());
            query.setDate(4, healthCenter.getUploadDate());
            query.setString(5, healthCenter.getCoverImage());
            query.setString(6, healthCenter.getName());

            int rowsAffected = query.executeUpdate();
            if (rowsAffected == 0) {
                LOGGER.info("NO SE ENCONTRÓ CENTRO MEDICO");
            }
            return true;
        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE + e);
            return false;
        }
    }

    /**
     * Obtiene una lista de centros de salud que ofrecen una especialidad específica.
     *
     * @param specialtyName El nombre de la especialidad.
     * @return Una lista de centros de salud que ofrecen la especialidad especificada.
     */
    public List<HealthCenter> getHealthCentersBySpecialty(String specialtyName) {
        String sql = "SELECT h.* FROM health_centers h " +
                "JOIN healthcenter_specialties hs ON h.healthCenterId = hs.healthCenterId " +
                "JOIN specialties s ON hs.specialtyId = s.specialtyId " +
                "WHERE s.specialty = ?;";
        List<HealthCenter> healthCenters = new LinkedList<>();

        try(Connection databaseConnection = getConnection();
            PreparedStatement query = databaseConnection.prepareStatement(sql)){

            query.setString(1, specialtyName);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                HealthCenter healthCenter = new HealthCenter();
                healthCenter.setHealthCenterId(resultSet.getInt(HEALTH_CENTER_ID));
                healthCenter.setName(resultSet.getString(NAME));
                healthCenter.setTelephone(resultSet.getString(TELEPHONE));
                healthCenter.setAddress(resultSet.getString(ADDRESS));
                healthCenter.setUploadDate(resultSet.getDate(UPLOAD_DATE));
                healthCenter.setCoverImage(resultSet.getString(COVER_IMAGE));
                healthCenter.setAbout(resultSet.getString(ABOUT));
                healthCenters.add(healthCenter);
            }
        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE+e);
        }
        return healthCenters;
    }
     /**
     * Obtiene una lista de centros de salud cuyo nombre coincide (parcialmente) con el nombre especificado.
     *
     * @param healthCenterName El nombre (o parte del nombre) del centro de salud.
     * @return Una lista de centros de salud cuyo nombre coincide con el especificado.
     */
    public List<HealthCenter> getHealthCentersByName(String healthCenterName) {
        String sql = "SELECT healthCenterId,name,telephone,address,uploadDate,coverImage, about FROM health_centers WHERE name LIKE ?;";
        List<HealthCenter> healthCenters = new LinkedList<>();

        try(Connection databaseConnection = getConnection();
             PreparedStatement  query = databaseConnection.prepareStatement(sql)){

            query.setString(1, "%" + healthCenterName + "%");
            ResultSet resultSet = query.executeQuery();

            while (resultSet.next()) {
                HealthCenter healthCenter = new HealthCenter();
                healthCenter.setHealthCenterId(resultSet.getInt(HEALTH_CENTER_ID));
                healthCenter.setName(resultSet.getString(NAME));
                healthCenter.setTelephone(resultSet.getString(TELEPHONE));
                healthCenter.setAddress(resultSet.getString(ADDRESS));
                healthCenter.setUploadDate(resultSet.getDate(UPLOAD_DATE));
                healthCenter.setCoverImage(resultSet.getString(COVER_IMAGE));
                healthCenter.setAbout(resultSet.getString(ABOUT));
                healthCenters.add(healthCenter);
            }
        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE+e);
        }
        return healthCenters;
    }
    /**
     * Obtiene un centro de salud por su identificador único.
     *
     * @param selectedHealthCenterId El identificador único del centro de salud.
     * @return El centro de salud correspondiente al identificador especificado.
     */
    public HealthCenter getHealthCenterById(int selectedHealthCenterId){
        String sql = "SELECT healthCenterId,name,telephone,address,uploadDate,coverImage, about FROM health_centers WHERE healthCenterId=?;";
        HealthCenter healthCenter = new HealthCenter();

        try(Connection databaseConnection = getConnection();
            PreparedStatement  query = databaseConnection.prepareStatement(sql)){

            query.setInt(1, selectedHealthCenterId);
            ResultSet resultSet = query.executeQuery();

            while (resultSet.next()) {
                healthCenter.setHealthCenterId(resultSet.getInt(HEALTH_CENTER_ID));
                healthCenter.setName(resultSet.getString(NAME));
                healthCenter.setTelephone(resultSet.getString(TELEPHONE));
                healthCenter.setAddress(resultSet.getString(ADDRESS));
                healthCenter.setUploadDate(resultSet.getDate(UPLOAD_DATE));
                healthCenter.setCoverImage(resultSet.getString(COVER_IMAGE));
                healthCenter.setAbout(resultSet.getString(ABOUT));
            }
        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE+e);
        }
        return healthCenter;
    }
    /**
     * Obtiene una lista de todos los centros de salud almacenados en la base de datos.
     *
     * @return Una lista de todos los centros de salud.
     */
    public List<HealthCenter> getHealthCentersList() {
        String sql = "SELECT healthCenterId, name, telephone, address, uploadDate, coverImage, about FROM health_centers ORDER BY name ASC;";

        try(Connection databaseConnection = getConnection();
            PreparedStatement  query = databaseConnection.prepareStatement(sql)){
            ResultSet resultSet = query.executeQuery();

            List<HealthCenter> healthCenters = new LinkedList<>();

            while (resultSet.next()) {
                HealthCenter healthCenter = new HealthCenter();
                healthCenter.setHealthCenterId(resultSet.getInt(HEALTH_CENTER_ID));
                healthCenter.setName(resultSet.getString(NAME));
                healthCenter.setTelephone(resultSet.getString(TELEPHONE));
                healthCenter.setAddress(resultSet.getString(ADDRESS));
                healthCenter.setUploadDate(resultSet.getDate(UPLOAD_DATE));
                healthCenter.setCoverImage(resultSet.getString(COVER_IMAGE));
                healthCenter.setAbout(resultSet.getString(ABOUT));
                healthCenters.add(healthCenter);
            }
            return healthCenters;
        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE+e);
            return Collections.emptyList();
        }
    }

    /**
     * Obtiene una lista de los nombres de todos los centros de salud almacenados en la base de datos.
     *
     * @return Una lista de los nombres de todos los centros de salud.
     */
    public List<String> getHealthCentersName() {
        String sql = "SELECT name FROM health_centers ORDER BY name ASC;";

        try(Connection databaseConnection = getConnection();
            PreparedStatement  query = databaseConnection.prepareStatement(sql)){

            ResultSet resultSet = query.executeQuery(sql);
            List<String> healthCentersName = new LinkedList<>();
            while (resultSet.next()) {
                String name= resultSet.getString("name");
                healthCentersName.add(name);
            }
            return healthCentersName;
        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE+e);
            return Collections.emptyList();

        }

    }
    /**
     * Elimina un centro de salud de la base de datos por su identificador único.
     *
     * @param healthCenterId El identificador único del centro de salud que se va a eliminar.
     * @return `true` si el centro de salud se eliminó correctamente, `false` de lo contrario.
     */
    public boolean deleteHealthCenterById(int healthCenterId) {
        String sql = "DELETE FROM `health_centers` WHERE `healthCenterId` = ?";

        try(Connection databaseConnection = getConnection();
            PreparedStatement  query = databaseConnection.prepareStatement(sql)){

            query.setInt(1, healthCenterId);
            int rowsAffected = query.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE+e);
            return false;
        }
    }
    /**
     * Elimina un centro de salud de la base de datos por su nombre.
     *
     * @param name El nombre del centro de salud que se va a eliminar.
     * @return `true` si el centro de salud se eliminó correctamente, `false` de lo contrario.
     */
    public boolean deleteHealthCenterByName(String name) {
        String sql = "DELETE FROM `health_centers` WHERE `name` = ?";

        try(Connection databaseConnection = getConnection();
            PreparedStatement  query = databaseConnection.prepareStatement(sql)){

            query.setString(1, name);
            int rowsAffected = query.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE+e);
            return false;
        }
    }



}