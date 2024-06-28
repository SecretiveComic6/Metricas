package com.health.finder.model;
/**
 *
 * @author eleani
 * @author herve
 * Clase que representa un usuario en el sistema de gestión de un centro de salud.
 */
public class User {
    private int userId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isAdmin;
    private String avatarId;
    /**
     * Crea una nueva instancia de User sin establecer valores para sus atributos.
     */
    public User(){

    }
     /**
     * Constructor que inicializa una nueva instancia de User con valores específicos.
     *
     * @param userId    El identificador único del usuario.
     * @param username  El nombre de usuario del usuario.
     * @param firstName El nombre del usuario.
     * @param lastName  El apellido del usuario.
     * @param email     El correo electrónico del usuario.
     * @param password  La contraseña del usuario.
     * @param isAdmin   Indica si el usuario es un administrador o no.
     * @param avatarId  El identificador del avatar del usuario.
     */
    public User(int userId,
                String username,
                String firstName,
                String lastName,
                String email,
                String password,
                boolean isAdmin,
                String avatarId) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.avatarId = avatarId;
    }
    /**
     * Obtiene el identificador único del usuario.
     *
     * @return El identificador único del usuario.
     */
    public int getUserId() {
        return userId;
    }
    /**
     * Establece el identificador único del usuario.
     *
     * @param userId El identificador único del usuario.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**
     * Obtiene el nombre de usuario del usuario.
     *
     * @return El nombre de usuario del usuario.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Establece el nombre de usuario del usuario.
     *
     * @param username El nombre de usuario del usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Establece el nombre del usuario.
     *
     * @param firstName El nombre del usuario.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Obtiene el apellido del usuario.
     *
     * @return El apellido del usuario.
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Establece el apellido del usuario.
     *
     * @param lastName El apellido del usuario.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return El correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Establece el correo electrónico del usuario.
     *
     * @param email El correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }
   /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Establece la contraseña del usuario.
     *
     * @param password La contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Obtiene el estado de administrador del usuario.
     *
     * @return `true` si el usuario es un administrador, `false` de lo contrario.
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }
    /**
     * Establece el estado de administrador del usuario.
     *
     * @param admin Indica si el usuario es un administrador o no.
     */
    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }
    /**
     * Obtiene el identificador del avatar del usuario.
     *
     * @return El identificador del avatar del usuario.
     */
    public String getAvatarId() {
        return avatarId;
    }
    /**
     * Establece el identificador del avatar del usuario.
     *
     * @param avatarId El identificador del avatar del usuario.
     */
    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

}
