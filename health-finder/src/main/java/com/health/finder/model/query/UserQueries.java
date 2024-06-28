package com.health.finder.model.query;

import com.health.finder.model.DataBaseConnection;
import com.health.finder.model.User;
import com.health.finder.model.UsernameTakenError;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.health.finder.model.DuplicateKeyError.isDuplicateKeyException;
/**
 *
 * @author eleani
 * @author herve
 * Clase que proporciona métodos para realizar consultas y
 * operaciones en la base de datos relacionadas con los usuarios del sistema.
 */
public class UserQueries extends DataBaseConnection {
    private static final Logger LOGGER = Logger.getLogger(UserQueries.class.getName());
    private static final String SQL_EXCEPTION_MESSAGE = "SQLException: ";

    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_FIRSTNAME = "firstname";
    private static final String COLUMN_LASTNAME = "lastname";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_ADMIN = "admin";
    private static final String COLUMN_AVATAR_ID = "avatarId";
    private static final String COLUMN_USER_ID = "userId";


    public boolean registerUser(User user) throws UsernameTakenError {
    /**
     * Registra un nuevo usuario en la base de datos.
     *
     * @param user El objeto User que representa al usuario a registrar.
     * @return `true` si el usuario se registró correctamente, `false` de lo contrario.
     * @throws UsernameTakenError Si el nombre de usuario ya está tomado.
     */
        String sql = "INSERT INTO `users` (`username`,`firstname`,`lastname`, `email`, `password`, `admin`,`avatarId` ) VALUES (?,?,?,?,?,?,?)";
        try( Connection databaseConnection = getConnection();
        PreparedStatement query = databaseConnection.prepareStatement(sql)){

            query.setString(1,user.getUsername().toLowerCase());
            query.setString(2,user.getFirstName());
            query.setString(3, user.getLastName());
            query.setString(4, user.getEmail().toLowerCase());
            query.setString(5, user.getPassword());
            query.setBoolean(6, user.getIsAdmin());
            query.setString(7, user.getAvatarId());
            query.execute();
            return true;
        }catch(SQLException e){
            if (isDuplicateKeyException(e))
                throw new UsernameTakenError("El nombre de usuario ya ha sido tomado!");
            return false;
        }

    }
    /**
     * Verifica las credenciales de inicio de sesión de un usuario.
     *
     * @param user El objeto User que contiene las credenciales de inicio de sesión.
     * @return `true` si las credenciales son válidas y el inicio de sesión es exitoso, `false` de lo contrario.
     */
    public boolean loginUser (User user){

        String sql = "SELECT userId, firstname, lastname, admin, email, avatarId, username, password FROM users WHERE username = ? AND password =?";

        try (Connection databaseConnection = getConnection();
             PreparedStatement query = databaseConnection.prepareStatement(sql)){

            query.setString(1,user.getUsername());
            query.setString(2, user.getPassword());
            ResultSet resultSet = query.executeQuery();

            if(resultSet.next()){
                user.setUserId(resultSet.getInt(COLUMN_USER_ID));
                user.setFirstName(resultSet.getString(COLUMN_FIRSTNAME));
                user.setLastName(resultSet.getString(COLUMN_LASTNAME));
                user.setIsAdmin(resultSet.getBoolean(COLUMN_ADMIN));
                user.setEmail(resultSet.getString(COLUMN_EMAIL));
                user.setAvatarId(resultSet.getString(COLUMN_AVATAR_ID));
                return true;
            }

            return false;
        }catch(SQLException e){
            LOGGER.info(SQL_EXCEPTION_MESSAGE+e);
            return false;
        }

    }

       /**
     * Elimina un usuario de la base de datos por su ID.
     *
     * @param userId El ID del usuario a eliminar.
     * @return `true` si el usuario se eliminó correctamente, `false` de lo contrario.
     */
    public boolean deleteUserById(int userId) {

        String sql = "delete from users where userId=?";
        try( Connection databaseConnection = getConnection();
             PreparedStatement query= databaseConnection.prepareStatement(sql)){

            query.setInt(1,userId);
            int resultado = query.executeUpdate();
            return resultado > 0;
        }catch(Exception e){
            LOGGER.info(SQL_EXCEPTION_MESSAGE+e);
            return false;
        }
    }
    /**
     * Elimina un usuario de la base de datos por su nombre de usuario.
     *
     * @param username El nombre de usuario del usuario a eliminar.
     * @return `true` si el usuario se eliminó correctamente, `false` de lo contrario.
     */
    public boolean deleteUserByUsername(String username) {

        String sql = "delete from users where username=?";
        try( Connection databaseConnection = getConnection();
             PreparedStatement query= databaseConnection.prepareStatement(sql)){
            query.setString(1, username);
            int resultado = query.executeUpdate();
            return resultado > 0;
        }catch(Exception e){
            LOGGER.info(SQL_EXCEPTION_MESSAGE+e);
            return false;
        }
    }
    /**
     * Obtiene los datos de los administradores del sistema.
     *
     * @return Una lista de usuarios que son administradores del sistema.
     */
    public List<User> getAdminsData(){
        String sql = "SELECT userId, username, firstname, lastname, email, avatarId, admin FROM users;";

        try(Connection databaseConnection = getConnection();
            PreparedStatement query= databaseConnection.prepareStatement(sql)){

            ResultSet resultSet = query.executeQuery(sql);
            List<User> adminUsers = new LinkedList<>();

            while (resultSet.next()) {
                User user = new User();
                boolean isAdmin = resultSet.getBoolean("admin");
                if (isAdmin) {
                    user.setUserId(resultSet.getInt("userId"));
                    user.setUsername(resultSet.getString("username"));
                    user.setFirstName(resultSet.getString("firstname"));
                    user.setLastName(resultSet.getString("lastname"));
                    user.setEmail(resultSet.getString("email"));
                    user.setAvatarId(resultSet.getString("avatarId"));
                    adminUsers.add(user);
                }
            }
            return adminUsers;
        }catch(SQLException e){
            LOGGER.info(SQL_EXCEPTION_MESSAGE+e);
            return Collections.emptyList();
        }
    }
    /**
     * Modifica los detalles de un usuario en la base de datos.
     *
     * @param user El objeto User que contiene los nuevos detalles del usuario.
     * @return `true` si la modificación fue exitosa, `false` de lo contrario.
     */
    public boolean modifyUser(User user) {
        String sql = "UPDATE `users` SET `username` = ?, `firstname` = ?, `lastname` = ?, `email` = ?, `password` = ?, `admin` = ?, `avatarId` = ? WHERE `userId` = ?";

        try(Connection databaseConnection = getConnection();
            PreparedStatement query = databaseConnection.prepareStatement(sql)){

            query.setString(1, user.getUsername().toLowerCase());
            query.setString(2, user.getFirstName());
            query.setString(3, user.getLastName());
            query.setString(4, user.getEmail().toLowerCase());
            query.setString(5, user.getPassword());
            query.setBoolean(6, user.getIsAdmin());
            query.setString(7, user.getAvatarId());
            query.setInt(8, user.getUserId());
            int rowsAffected = query.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE+e);
            return false;
        }
    }
    /**
     * Verifica si una cadena dada es una dirección de correo electrónico válida.
     *
     * @param email La dirección de correo electrónico a verificar.
     * @return `true` si la cadena es una dirección de correo electrónico válida, `false` de lo contrario.
     */
    public boolean checkEmail(String email){
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    /**
     * Obtiene los detalles de un usuario por su ID.
     *
     * @param userId El ID del usuario del que se desean obtener los detalles.
     * @return Un objeto User que contiene los detalles del usuario.
     */
    public User getUserDetailsById(int userId) {
        String sql = "SELECT username, avatarId, firstname, lastname FROM users WHERE userId = ?";
        User user = new User();

        try (Connection databaseConnection = getConnection();
             PreparedStatement query = databaseConnection.prepareStatement(sql)) {

            query.setInt(1, userId);
            ResultSet resultSet = query.executeQuery();

            if (resultSet.next()) {
                user.setUserId(userId);
                user.setUsername(resultSet.getString("username"));
                user.setAvatarId(resultSet.getString("avatarId"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
            }

        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE + e);
        }

        return user;
    }
}
