package com.health.finder.controller;

import com.health.finder.model.*;
import com.health.finder.model.query.*;
import com.health.finder.view.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

/**
 * Esta clase es el controlador del programa
 * @author herve
 * @author eleani
 */
public class Controller implements ActionListener {

     private User user;
    private UserQueries userQueries;
    private LanguageQueries languageQueries;
    private ReviewQueries reviewQueries;
    private SpecialtyQueries specialtyQueries;
    private HealthCenterQueries healthCenterQueries;
    private Home homePanel;
    private Login loginPanel;
    private AdminHome adminHomePanel;
    private AdminManager adminManagerPanel;
    private HealthCentersManager healthCentersManagerPanel;
    private ReviewPage reviewPage;
    private ClientRegister clientRegisterForm;
    private Search searchPanel;
    private static final String SELECCIONE_OPCION = "Seleccione";
    private String coverImagePath = "";
    private List<HealthCenter> searchResults;
    private static final String MESSAGE_ERROR = "Hubo un error:";
/**
 * Constructor de la clase Controller que inicializa todos los componentes necesarios para controlar la aplicación en el patrón MVC.
 *
 * @param user                Instancia del modelo User.
 * @param userQueries         Instancia de UserQueries para realizar consultas relacionadas con usuarios en la base de datos.
 * @param languageQueries     Instancia de LanguageQueries para realizar consultas relacionadas con idiomas en la base de datos.
 * @param reviewQueries       Instancia de ReviewQueries para realizar consultas relacionadas con reseñas en la base de datos.
 * @param specialtyQueries    Instancia de SpecialtyQueries para realizar consultas relacionadas con especialidades en la base de datos.
 * @param healthCenterQueries Instancia de HealthCenterQueries para realizar consultas relacionadas con centros de salud en la base de datos.
 * @param homePanel           Instancia de la vista Home para la interfaz de usuario.
 * @param loginPanel          Instancia de la vista Login para la interfaz de usuario.
 * @param adminHomePanel      Instancia de la vista AdminHome para la interfaz de usuario.
 * @param adminManagerPanel   Instancia de la vista AdminManager para la interfaz de usuario.
 * @param healthCentersManagerPanel Instancia de la vista HealthCentersManager para la interfaz de usuario.
 * @param reviewPage          Instancia de la vista ReviewPage para la interfaz de usuario.
 * @param clientRegisterForm Instancia de la vista ClientRegister para la interfaz de usuario.
 * @param searchPanel         Instancia de la vista Search para la interfaz de usuario.
 */
    public Controller(User user,
                      UserQueries userQueries,
                      LanguageQueries languageQueries,
                      ReviewQueries reviewQueries,
                      SpecialtyQueries specialtyQueries,
                      HealthCenterQueries healthCenterQueries,
                      Home homePanel,
                      Login loginPanel,
                      AdminHome adminHomePanel,
                      AdminManager adminManagerPanel,
                      HealthCentersManager healthCentersManagerPanel,
                      ReviewPage reviewPage,
                      ClientRegister clientRegisterForm,
                      Search searchPanel) {
        this.user = user;
        this.userQueries = userQueries;
        this.languageQueries = languageQueries;
        this.reviewQueries = reviewQueries;
        this.specialtyQueries = specialtyQueries;
        this.healthCenterQueries = healthCenterQueries;
        this.homePanel = homePanel;
        this.loginPanel = loginPanel;
        this.adminHomePanel = adminHomePanel;
        this.adminManagerPanel = adminManagerPanel;
        this.healthCentersManagerPanel = healthCentersManagerPanel;
        this.reviewPage = reviewPage;
        this.clientRegisterForm = clientRegisterForm;
        this.searchPanel = searchPanel;

        loginPanel.loginButton.addActionListener(this);
        loginPanel.passwordTxtField.addActionListener(this);
        loginPanel.guessAccessButton.addActionListener(this);
        loginPanel.registerUserButton.addActionListener(this);

        clientRegisterForm.cancelButton.addActionListener(this);
        clientRegisterForm.registerButton.addActionListener(this);
        clientRegisterForm.avatarComboBox.addActionListener(this);

        homePanel.searchButton.addActionListener(this);
        homePanel.specialtyRButton.addActionListener(this);
        homePanel.comboBox_specialty.addActionListener(this);
        homePanel.nameRButton.addActionListener(this);
        homePanel.registerButton.addActionListener(this);
        homePanel.loginButton.addActionListener(this);

        adminHomePanel.adminManagerButton.addActionListener(this);
        adminHomePanel.healthCenterManager.addActionListener(this);
        adminHomePanel.homeButton.addActionListener(this);


        adminManagerPanel.deleteDataButton.addActionListener(this);
        adminManagerPanel.addNewDataButton.addActionListener(this);
        adminManagerPanel.saveDataButton.addActionListener(this);
        adminManagerPanel.backButton.addActionListener(this);
        adminManagerPanel.refreshTable.addActionListener(this);
        adminManagerPanel.cleanSelectionButton.addActionListener(this);
        adminManagerPanel.modifyDataButton.addActionListener(this);


loginPanel.loginButton.addActionListener(this);
loginPanel.passwordTxtField.addActionListener(this);
loginPanel.guessAccessButton.addActionListener(this);
loginPanel.registerUserButton.addActionListener(this);


clientRegisterForm.cancelButton.addActionListener(this);
clientRegisterForm.registerButton.addActionListener(this);
clientRegisterForm.avatarComboBox.addActionListener(this);


homePanel.searchButton.addActionListener(this);
homePanel.specialtyRButton.addActionListener(this);
homePanel.comboBox_specialty.addActionListener(this);
homePanel.nameRButton.addActionListener(this);
homePanel.registerButton.addActionListener(this);
homePanel.loginButton.addActionListener(this);


adminHomePanel.adminManagerButton.addActionListener(this);
adminHomePanel.healthCenterManager.addActionListener(this);
adminHomePanel.homeButton.addActionListener(this);


adminManagerPanel.deleteDataButton.addActionListener(this);
adminManagerPanel.addNewDataButton.addActionListener(this);
adminManagerPanel.saveDataButton.addActionListener(this);
adminManagerPanel.backButton.addActionListener(this);
adminManagerPanel.refreshTable.addActionListener(this);
adminManagerPanel.cleanSelectionButton.addActionListener(this);
adminManagerPanel.modifyDataButton.addActionListener(this);
adminManagerPanel.avatarComboBox.addActionListener(this);


        healthCentersManagerPanel.deleteDataButton.addActionListener(this);
        healthCentersManagerPanel.cleanSelection.addActionListener(this);
        healthCentersManagerPanel.modifyDataButton.addActionListener(this);
        healthCentersManagerPanel.imageSelector.addActionListener(this);
        healthCentersManagerPanel.specialtySelectorComboBox.addActionListener(this);
        healthCentersManagerPanel.addSpecialtyButton.addActionListener(this);
        healthCentersManagerPanel.addLanguageButton.addActionListener(this);
        healthCentersManagerPanel.backButton.addActionListener(this);
        healthCentersManagerPanel.deleteSpecialtyButton.addActionListener(this);
        healthCentersManagerPanel.deleteLanguageButton.addActionListener(this);
        healthCentersManagerPanel.insertnewDataButton.addActionListener(this);
        healthCentersManagerPanel.addNewDataButton.addActionListener(this);
        healthCentersManagerPanel.refreshTable.addActionListener(this);

        searchPanel.exitButton.addActionListener(this);
        searchPanel.clearFiltersButton.addActionListener(this);
        searchPanel.writeReviewButton.addActionListener(this);
        searchPanel.clearFiltersButton.addActionListener(this);
        searchPanel.backButton.addActionListener(this);
        searchPanel.updateReviewsButton.addActionListener(this);
        searchPanel.deleteReviewButton.addActionListener(this);

        searchPanel.fiveStarsRatingJRButton.addActionListener(this);
        searchPanel.fourtarsRatingJRButton.addActionListener(this);
        searchPanel.threeStarsRatingJRButton.addActionListener(this);
        searchPanel.twoStarsRatingJRButton.addActionListener(this);
        searchPanel.onestarRatingJRButton.addActionListener(this);
        searchPanel.languageSelectorComboBox.addActionListener(this);

         searchPanel.applyFiltersButton.addActionListener(this);

        reviewPage.cancelButton.addActionListener(this);
        reviewPage.sendReviewButton.addActionListener(this);
        reviewPage.saveModifyReviewButton.addActionListener(this);

        showSpecialtiesInHomePanel();
        showHealthCentersNamesInHomePanel();
        showSpecialtiesByName();
        showLanguagesByName();
        fillHealthCentersTable();
        showAdminUsersList();
        showLanguagesByNameSelector();
    }

    /**
    * Realiza las acciones correspondientes cuando se activa un evento de accion.
    * @param actionEvent El evento de acción que ha ocurrido.
    */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //loginProcess
        if (actionEvent.getSource() == loginPanel.loginButton) {
            loginProcess();
        }

        if (actionEvent.getSource() == loginPanel.guessAccessButton) {
            JOptionPane.showMessageDialog(null, "Entrando como invitado");
            loginPanel.dispose();
            homePanel.setVisible(true);
            searchPanel.writeReviewButton.setVisible(false);
            startHomePanel();
        }

        if (actionEvent.getSource() == loginPanel.registerUserButton) {
            clientRegisterForm.setVisible(true);
            loginPanel.dispose();
        }

        //registerForm
        if (actionEvent.getSource() == clientRegisterForm.registerButton) {
            insertNewClientData();
        }
        if (actionEvent.getSource() == clientRegisterForm.cancelButton) {
            clearRegisterFormPanel();
            loginPanel.setVisible(true);
            clientRegisterForm.dispose();
        }

        if (actionEvent.getSource() == clientRegisterForm.avatarComboBox) {
            String avatarId = (String) clientRegisterForm.avatarComboBox.getSelectedItem();
            Image avatarImage = getAvatarImage(avatarId);

            int width = clientRegisterForm.avatarLabel.getWidth();
            int height = clientRegisterForm.avatarLabel.getHeight();
            Image scaledImg = getScaleImage(avatarImage, width, height).getImage();
            ImageIcon scaledAvatarIcon = new ImageIcon(scaledImg);
            clientRegisterForm.avatarLabel.setIcon(scaledAvatarIcon);
        }

        //HomePanel
        if (actionEvent.getSource() == homePanel.registerButton) {
            homePanel.dispose();
            clientRegisterForm.setVisible(true);
        }

        if (actionEvent.getSource() == homePanel.loginButton) {
            homePanel.dispose();
            loginPanel.setVisible(true);
        }


        if (actionEvent.getSource() == homePanel.nameRButton) {
            //change by specialty
            homePanel.comboBox_specialty.setSelectedItem(SELECCIONE_OPCION);

            setVisibleOfSearchItemsByName(true);
            homePanel.comboBox_specialty.setVisible(false);
            homePanel.searchButton.setVisible(true);

        }

        if (actionEvent.getSource() == homePanel.specialtyRButton) {
            homePanel.healthCenternamesTxtField.setText("");
            homePanel.namesComboBox.setSelectedItem(SELECCIONE_OPCION);

            setVisibleOfSearchItemsByName(false);
            homePanel.comboBox_specialty.setVisible(true);
            homePanel.searchButton.setVisible(true);
        }

        if (actionEvent.getSource() == homePanel.searchButton) {
            searchResults = validateSearchCriteria();
            if (!searchResults.isEmpty()) { // Verifica si la lista de resultados no esta vacia
                //load data in tables
                loadfilteredHealthCenterDataInTable(searchResults);
                homePanel.dispose();
                searchPanel.languageSelectorComboBox.setSelectedItem(SELECCIONE_OPCION);
                searchPanel.deleteReviewButton.setVisible(false);
                searchPanel.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "No hay resultados para esa búsqueda");
            }
        }

        //search panel
        if(actionEvent.getSource() == searchPanel.writeReviewButton){
            //open review page
            reviewPage.saveModifyReviewButton.setVisible(false);
            reviewPage.setVisible(true);
        }

        if(actionEvent.getSource() == searchPanel.applyFiltersButton){
            if (searchResults != null) { // Verifica si searchResults no es nulo antes de usarlo
                handleFilterAction(searchResults);
            } else {
                JOptionPane.showMessageDialog(null, "Primero realiza una búsqueda antes de aplicar filtros.");
            }

        }

        if(actionEvent.getSource() == searchPanel.updateReviewsButton){
            int selectedRow = searchPanel.filteredHealthCenterTable.getSelectedRow();
            if (selectedRow != -1) {
                int healthCenterId = (int) searchPanel.filteredHealthCenterTable.getValueAt(selectedRow, 0);
                showReviewsFromSelectedHealthCenter(healthCenterId);
                updateGlobalRatingInfo(healthCenterId);
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un centro medico");
            }
        }

        if(actionEvent.getSource() == searchPanel.deleteReviewButton) {
            int selectedRow = searchPanel.reviewsTable.getSelectedRow();
            if (selectedRow != -1) {
                //delete and clear form
               deleteLoggedInReview(selectedRow);
               clearReviewPanel();
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un centro medico");
            }
        }


        if(actionEvent.getSource() == reviewPage.saveModifyReviewButton){
            int selectedRow = searchPanel.reviewsTable.getSelectedRow();
            if (selectedRow != -1) {
                editLoggedInReview(selectedRow);
                reviewPage.setVisible(true);
            }
        }

        //review page
        if(actionEvent.getSource() == reviewPage.sendReviewButton){
            int selectedRow = searchPanel.filteredHealthCenterTable.getSelectedRow();
            sendUserReview(selectedRow);
        }
        if(actionEvent.getSource() == searchPanel.backButton){
            searchPanel.dispose();
            clearSearchPanel();
            homePanel.setVisible(true);
        }

        if(actionEvent.getSource() == reviewPage.cancelButton){
            reviewPage.dispose();
        }


        //adminHomePanel
        if (actionEvent.getSource() == adminHomePanel.adminManagerButton) {
            //admin manager is selected
            //show admin manager panel
            adminHomePanel.setVisible(false);
            
            adminManagerPanel.setVisible(true);
            adminManagerPanel.formPanel.setVisible(false);
            adminManagerPanel.tablePanel.setVisible(true);
            adminManagerPanel.saveDataButton.setVisible(false);

        }

        if (actionEvent.getSource() == adminHomePanel.healthCenterManager) {
            //health center manager is selected
            adminHomePanel.setVisible(false);
            //show health center manager panel

            healthCentersManagerPanel.tablePanel.setVisible(true);
            healthCentersManagerPanel.formPanel.setVisible(false);

            healthCentersManagerPanel.setVisible(true);
        }

        if(actionEvent.getSource() == adminHomePanel.homeButton){
            adminHomePanel.dispose();
            homePanel.setVisible(true);
        }

        //adminManager
        if (actionEvent.getSource() == adminManagerPanel.saveDataButton) {
            insertNewAdmin();


        }
        if (actionEvent.getSource() == adminManagerPanel.modifyDataButton){
            updateAdminData();
        }
        if (actionEvent.getSource() == adminManagerPanel.deleteDataButton) {
            deleteAdminData();
        }
        if(actionEvent.getSource() == adminManagerPanel.backButton){
            adminManagerPanel.dispose();
            adminHomePanel.setVisible(true);
        }
        if(actionEvent.getSource() == adminManagerPanel.cleanSelectionButton){
            adminManagerPanel.adminTable.clearSelection();
            adminManagerPanel.formPanel.setVisible(false);
        }
        if(actionEvent.getSource() == adminManagerPanel.refreshTable){
            showAdminUsersList();
        }
        if(actionEvent.getSource() == adminManagerPanel.addNewDataButton){
            //hide table and clear form
            adminManagerPanel.tablePanel.setVisible(false);
            adminManagerPanel.formPanel.setVisible(true);
            adminManagerPanel.modifyDataButton.setVisible(false);
            adminManagerPanel.saveDataButton.setVisible(true);
            cleanAdminForm();
        }

        if(actionEvent.getSource() == adminManagerPanel.avatarComboBox){
            String avatarId = (String) adminManagerPanel.avatarComboBox.getSelectedItem();
            Image avatarImage = getAvatarImage(avatarId);

            int width = adminManagerPanel.avatarLabel.getWidth();
            int height = adminManagerPanel.avatarLabel.getHeight();
            Image scaledImg = getScaleImage(avatarImage, width, height).getImage();
            ImageIcon scaledAvatarIcon = new ImageIcon(scaledImg);
            adminManagerPanel.avatarLabel.setIcon(scaledAvatarIcon);

        }

        //healthCentersManager
        if (actionEvent.getSource() == healthCentersManagerPanel.imageSelector) {
            getCoverImageSelectedPath();
            Image coverImage = getCoverImage(coverImagePath);
            if (coverImage != null) {
                setCoverImageToLabel(coverImage);
            }

        }
        if (actionEvent.getSource() == healthCentersManagerPanel.addSpecialtyButton) {
            addSelectedSpecialtyToCurrentHealthCenter();
        }
        if (actionEvent.getSource() == healthCentersManagerPanel.addLanguageButton) {
            addSelectedLanguageToCurrentHealthCenter();
        }

        if(actionEvent.getSource() == healthCentersManagerPanel.deleteLanguageButton)
            deleteSelectedLanguageFromCurrentHealthCenter();

        if(actionEvent.getSource() == healthCentersManagerPanel.deleteSpecialtyButton)
            deleteSelectedSpecialtyFromCurrentHealthCenter();

        if (actionEvent.getSource() == healthCentersManagerPanel.cleanSelection) {
            healthCentersManagerPanel.healthCentersTable.clearSelection();
            healthCentersManagerPanel.formPanel.setVisible(false);

        }
        if(actionEvent.getSource() == healthCentersManagerPanel.addNewDataButton){
            //hide table and specialty and languages selector and clear form
            healthCentersManagerPanel.tablePanel.setVisible(false);
            healthCentersManagerPanel.formPanel.setVisible(true);
            healthCentersManagerPanel.attributesPanel.setVisible(false);
            clearHealthCenterForm();
            healthCentersManagerPanel.modifyDataButton.setVisible(false);
            healthCentersManagerPanel.insertnewDataButton.setVisible(true);

        }

        if(actionEvent.getSource() == healthCentersManagerPanel.insertnewDataButton){
            insertNewHealthCenterData();
        }
        if (actionEvent.getSource() == healthCentersManagerPanel.deleteDataButton) {
            deleteHealthCenterData();
            clearHealthCenterForm();
        }

        if (actionEvent.getSource() == healthCentersManagerPanel.modifyDataButton) {
            //hide table with health centers and open health center form
            healthCentersManagerPanel.tablePanel.setVisible(false);
            healthCentersManagerPanel.formPanel.setVisible(true);
            modifyHealthCenterData();

        }
        
        if(actionEvent.getSource() == healthCentersManagerPanel.backButton){
            //show previous panel and hide current
            adminHomePanel.setVisible(true);
            healthCentersManagerPanel.dispose();
        }

        if (actionEvent.getSource() == healthCentersManagerPanel.refreshTable) {
            fillHealthCentersTable();
        }
    }
/**
 * Valida los criterios de busqueda especificados por el usuario.
 * @return Una lista de centros de salud que coinciden con los criterios de búsqueda.
 */
    private List<HealthCenter> validateSearchCriteria() {

        String searchText = homePanel.healthCenternamesTxtField.getText();
        String selectedCenter = (String) homePanel.namesComboBox.getSelectedItem();
        String name = (!searchText.isEmpty()) ? searchText : (!Objects.equals(selectedCenter, SELECCIONE_OPCION)) ? selectedCenter : "";
        String selectedSpecialty = (String) homePanel.comboBox_specialty.getSelectedItem();

        if (!name.isEmpty()) {
            return  healthCenterQueries.getHealthCentersByName(name);

        } else if (!Objects.equals(selectedSpecialty, SELECCIONE_OPCION)) {
            return healthCenterQueries.getHealthCentersBySpecialty(selectedSpecialty);

        } else {
            JOptionPane.showMessageDialog(null, "Escriba o SELECCIONE_OPCION un centro médico o una especialidad");
        }
        return Collections.emptyList();
    }
/**
 * Muestra los datos del centro de salud en el panel de busqueda.
 * @param healthCenter El centro de salud cuyos datos se mostraran.
 */
    private void showHealthCenterDataInSearchPanel(HealthCenter healthCenter){

        searchPanel.nameTxtField.setText(healthCenter.getName());
        searchPanel.aboutTextArea.setText(healthCenter.getAbout());
        searchPanel.telephoneTxtField.setText(healthCenter.getTelephone());
        searchPanel.addressTxtField.setText(healthCenter.getAddress());
        String imageName = healthCenter.getCoverImage();
        String imagePath = getCoverImagePath(imageName);
        Image coverImage = getCoverImage(imagePath);

        int width = searchPanel.coverImageLabel.getWidth();
        int height = searchPanel.coverImageLabel.getHeight();

        Image scaledCoverImage = getScaleImage(Objects.requireNonNull(coverImage), width, height).getImage();
        ImageIcon scaledCoverImageIcon = new ImageIcon(scaledCoverImage);
        searchPanel.coverImageLabel.setIcon(scaledCoverImageIcon);

        int healthCenterId = healthCenter.getHealthCenterId();
        //load specialties and languages
        showLanguagesByFilteredHealthCenter(healthCenterId);
        showSpecialtiesByFilteredHealthCenter(healthCenterId);

    }

    /**
    * Inicia el panel de inicio de sesion, haciendolo visible.
    */
    public void startLoginPanel() {
        loginPanel.setVisible(true);
    }

    /**
     * Funcion que realiza el proceso de inicio de sesion
     */
    private void loginProcess() {

        String username = loginPanel.usernameTxtField.getText();
        String password = new String(loginPanel.passwordTxtField.getPassword());
        String encodePassword = PasswordEncoder.getEncodePassword(password);

        if (!username.isEmpty() || !password.isEmpty()) {
            user.setUsername(username);
            user.setPassword(encodePassword);

            if (userQueries.loginUser(user)) {
                //get avatarImage
                String avatarId = user.getAvatarId();
                Image img = getAvatarImage(avatarId);

                if (user.getIsAdmin()) {
                    adminHomePanel.setVisible(true);
                    setUserAdminData(img);
                } else {
                    homePanel.setVisible(true);
                }
                startHomePanel();

                //some labels must set visible false because theres a user
                homePanel.loginButton.setVisible(false);
                homePanel.registerButton.setVisible(false);

                homePanel.usernameLabel.setVisible(true);
                homePanel.avatarLabel.setVisible(true);

                setUserData(img);
                loginPanel.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Llene todos los campos");
        }
    }

    /**
     * Obtiene la imagen de avatar del usuario utilizando el ID de avatar proporcionado.
     *
     * @param avatarId El ID del avatar del usuario.
     * @return La imagen de avatar del usuario.
     */
    private Image getAvatarImage(String avatarId) {
        String avatarPath = getAvatarPath(avatarId);
        ImageIcon avatarIcon = new ImageIcon(avatarPath);
        return avatarIcon.getImage();
    }
    /**
     * Inserta los datos del nuevo cliente en la base de datos después de validarlos.
     */
    private void insertNewClientData() {
        try {
            // Obtener los datos del formulario de registro del cliente
            String username = clientRegisterForm.usernameTextField.getText();
            String email = clientRegisterForm.userEmailTextField.getText();
            String firstname = clientRegisterForm.firstnameTextField.getText();
            String lastname = clientRegisterForm.userLastNameTextField.getText();
            String password = clientRegisterForm.userPasswordTextField.getText();
            String encodePassword = PasswordEncoder.getEncodePassword(password);
            String avatarId = (String) clientRegisterForm.avatarComboBox.getSelectedItem();
            Boolean isAdmin = false;

            // Crear un usuario para validar los datos.
            User userClient = createUser(username, email, firstname, lastname, password, avatarId, isAdmin);

            // Validar los datos del usuario.
            if (validateUserData(userClient)) {
                // Cambiar la contraseña por la contraseña codificada de ocho caracteres
                userClient.setPassword(encodePassword);
                if (userQueries.registerUser(userClient)) {
                    JOptionPane.showMessageDialog(null, "Te registraste correctamente!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrarse, intentar de nuevo!");
                }
            }
        } catch (UsernameTakenError e) {
            JOptionPane.showMessageDialog(null, "Hubo un error: " + e);
        } catch (InvalidDataError e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
    }
    /**
     * Crea un objeto de tipo User con los datos proporcionados.
     *
     * @param username       El nombre de usuario del nuevo usuario.
     * @param email          El correo electrónico del nuevo usuario.
     * @param firstname      El nombre (primer nombre) del nuevo usuario.
     * @param lastname       El apellido (apellido paterno) del nuevo usuario.
     * @param encodePassword La contraseña codificada del nuevo usuario.
     * @param avatarId       El ID del avatar del nuevo usuario.
     * @param isAdmin        Un booleano que indica si el nuevo usuario es un administrador o no.
     * @return Un objeto User con los datos proporcionados.
     */
    private User createUser(String username, String email, String firstname, String lastname, String encodePassword, String avatarId, Boolean isAdmin) {
        User userCreated = new User();
        userCreated.setUsername(capitalizeFirstLetter(username));
        userCreated.setEmail(email.toLowerCase());
        userCreated.setFirstName(capitalizeFirstLetter(firstname));
        userCreated.setLastName(capitalizeFirstLetter(lastname));
        userCreated.setPassword(encodePassword);
        userCreated.setAvatarId(avatarId);
        userCreated.setIsAdmin(isAdmin);
        return userCreated;
    }
    /**
     * Capitaliza la primera letra de una cadena y convierte el resto de la cadena en minúsculas.
     *
     * @param input La cadena de entrada.
     * @return La cadena con la primera letra capitalizada y el resto de la cadena en minúsculas.
     */
    private String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
    /**
     * Valida los datos de un usuario.
     *
     * @param user El usuario cuyos datos se van a validar.
     * @return true si los datos son validos, de lo contrario false.
     * @throws UsernameTakenError Si el nombre de usuario ya esta en uso.
     * @throws InvalidDataError Si los datos del usuario son invalidos.
     */
    private boolean validateUserData(User user) throws UsernameTakenError, InvalidDataError {

        if (user.getUsername().isEmpty() || user.getEmail().isEmpty() || user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getPassword().isEmpty() || user.getAvatarId().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Completa los campos");
            return false;
        }
        if (user.getUsername().length() < 8) {
            JOptionPane.showMessageDialog(null, "El nombre de usuario debe tener 8 caracteres minimo");
            return false;
        }

        if (user.getPassword().length() < 8) {
            JOptionPane.showMessageDialog(null, "El password debe tener 8 caracteres minimo");
            return false;
        }

        if (!userQueries.checkEmail(user.getEmail())) {
            JOptionPane.showMessageDialog(null, "El correo ingresado no es valido");
            return false;
        }

        if (SELECCIONE_OPCION.equals(user.getAvatarId())) {
            JOptionPane.showMessageDialog(null, "Elija un avatar");
            return false;
        }

        return true;
    }


    /**
    * Inicia el panel de inicio.
    * Oculta los elementos relacionados con la búsqueda por nombre y la especialidad.
    */
    private void startHomePanel() {
        //items by name are hide
        setVisibleOfSearchItemsByName(false);

        homePanel.comboBox_specialty.setVisible(false);
        homePanel.searchButton.setVisible(false);
    }
    /**
    * Establece los datos del usuario en el panel de inicio.
    *
    * @param avatarImage La imagen de avatar del usuario.
    */
    private void setUserData(Image avatarImage) {
        //set positions to labels
        Point usernameLabelPos = new Point(1100, 58);

        homePanel.usernameLabel.setText(user.getUsername());
        homePanel.usernameLabel.setLocation(usernameLabelPos);

        Image scaledImg = avatarImage.getScaledInstance(homePanel.avatarLabel.getWidth(), homePanel.avatarLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledAvatarIcon = new ImageIcon(scaledImg);

        Point avatarLabelPos  = new Point(1200, 17);
        homePanel.avatarLabel.setIcon(scaledAvatarIcon);
        homePanel.avatarLabel.setLocation(avatarLabelPos);
        searchPanel.writeReviewButton.setVisible(true);

    }
    /**
    * Muestra las especialidades en el panel de inicio.
    */
    private void showSpecialtiesInHomePanel() {
        List<String> specialties = specialtyQueries.getSpecialtiesList();
        for (String actual : specialties) {
            homePanel.comboBox_specialty.addItem(actual);
        }
    }
    /**
    * Muestra los nombres de los centros de salud en el panel de inicio.
    */
    private void showHealthCentersNamesInHomePanel() {
        List<String> healthCentersNames = healthCenterQueries.getHealthCentersName();
        for (String actual : healthCentersNames) {
            homePanel.namesComboBox.addItem(actual);
        }
    }
    /**
    * Escala una imagen al tamaño especificado.
    *
    * @param img     La imagen que se va a escalar.
    * @param width   El ancho deseado de la imagen escalada.
    * @param height  La altura deseada de la imagen escalada.
    * @return        Un ImageIcon que representa la imagen escalada.
    */
    private ImageIcon getScaleImage(Image img, int width, int height) {
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    /**
     * obtiene el path de la imagen de perfil
     * @param avatarId nombre imagen
     * @return path de la imagen
     */
    private String getAvatarPath(String avatarId) {
        return getResourcePath("avatar", avatarId);
    }

    /**
     *  regresa el path de la imagen de centros medicos
     * @param imageName nombre de la imagen
     * @return direccion de la imagen
     */
    private String getCoverImagePath(String imageName) {
        return getResourcePath("coverimage", imageName);
    }

    /**
     *  decicde el tipo de recurso y elige la direccion base
     * @param resourceType tipo de recurso
     * @param resourceName nombre del recurso
     * @return
     */
    private String getResourcePath(String resourceType, String resourceName) {
        String basePath;

        switch (resourceType.toLowerCase()) {
            case "avatar":
                basePath = "health-finder/src/main/resources/avatar/";
                resourceName = Objects.requireNonNullElse(resourceName, "avatar_0") + ".png";
                break;
            case "coverimage":
                basePath = "health-finder/src/main/resources/healthcenter/";
                break;
            default:
                throw new IllegalArgumentException("Invalid resource type: " + resourceType);
        }

        return basePath + resourceName;
    }

    /**
    * Establece la visibilidad de los elementos de busqueda por nombre en el panel principal.
    *
    * @param value El valor booleano que indica si los elementos deben estar visibles o no.
    */
    private void setVisibleOfSearchItemsByName(Boolean value) {
        homePanel.healthCenternamesTxtField.setVisible(value);
        homePanel.namesComboBox.setVisible(value);
    }


    /**
    * Limpia los campos del formulario de administrador en el panel de gestion de administradores.
    */
    private void cleanAdminForm() {
        adminManagerPanel.adminPasswordTextField.setText("");
        adminManagerPanel.usernameTxtfield.setText("");
        adminManagerPanel.adminNameTextField.setText("");
        adminManagerPanel.adminEmailTextField.setText("");
        adminManagerPanel.adminLastNameTextField.setText("");
        adminManagerPanel.avatarComboBox.setSelectedIndex(0);
        adminManagerPanel.userIdLabel.setText("");
    }
    /**
    * Crea un modelo de tabla filtrada para los centros medicos.
    *
    * @return El modelo de tabla creado.
    */
    private DefaultTableModel createFilteredHealthCenterTableModel(){
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.setColumnIdentifiers(new Object[]{
                "Id Centro Médico", "Nombre "
        });
        // add ListSelectionListener to admin table to show admin data in form
        searchPanel.filteredHealthCenterTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    int selectedRow =  searchPanel.filteredHealthCenterTable.getSelectedRow();
                    if (selectedRow != -1) {
                     //get health center id
                        int healthCenterId = (int) searchPanel.filteredHealthCenterTable.getValueAt(selectedRow, 0);
                        HealthCenter healthCenterSelected = healthCenterQueries.getHealthCenterById(healthCenterId);
                        showHealthCenterDataInSearchPanel(healthCenterSelected);

                        //get review list
                        List<Review> reviews = reviewQueries.getReviewsByHealthCenterId(healthCenterId);
                        //load reviews in table
                        loadReviewInTable(reviews);
                        //get Reviews Global number
                        updateGlobalRatingInfo(healthCenterId);

                    }
                }
            }
        });
        return tableModel;
    }
    /**
    * Actualiza la informacion global de calificaciones en el panel de busqueda.
    *
    * @param healthCenterId El ID del centro medico del que se actualizara la informacion.
    */
    private void updateGlobalRatingInfo(int healthCenterId) {
        // Obtener el número global de reseñas
        int reviewCount = reviewQueries.getRatingInfoByHealthCenterId(healthCenterId).getReviewCount();
        // Calcular la calificación promedio
        double averageRating = reviewQueries.calculateAverageRating(healthCenterId);

        // Formatear la calificación promedio a dos decimales
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String formattedAverageRating = decimalFormat.format(averageRating);

        // Establecer el promedio en el panel
        searchPanel.globalRating.setText(formattedAverageRating + " estrellas");
        searchPanel.globalReviewsLabel.setText("(" + reviewCount + " reseñas)");
    }
    /**
    * Muestra las reviews del centro medico seleccionado en la tabla de review
    *
    * @param healthCenterId El ID del centro medico del que se mostraran las review
    */
    private void showReviewsFromSelectedHealthCenter( int healthCenterId){
        List<Review> reviews = reviewQueries.getReviewsByHealthCenterId(healthCenterId);
        loadReviewInTable(reviews);
    }
    /**
    * Carga los datos de los centros medicos filtrados en la tabla de centros medicos filtrados.
    *
    * @param filteredHealthCenters Lista de centros medicos filtrados que se mostraran en la tabla.
    */
    private void loadfilteredHealthCenterDataInTable(List<HealthCenter> filteredHealthCenters) {
        DefaultTableModel tableModel = createFilteredHealthCenterTableModel();
        searchPanel.filteredHealthCenterTable.setModel(tableModel);
        tableModel.setRowCount(0);

        for (HealthCenter healthCenterFiltered : filteredHealthCenters) {
            Object[] rowData = new Object[]{
                    healthCenterFiltered.getHealthCenterId(),
                    healthCenterFiltered.getName()
            };
            tableModel.addRow(rowData);
        }
        tableModel.fireTableDataChanged();
    }

    /**
    * Crea un modelo de tabla para mostrar datos de administradores.
    *
    * @return El modelo de tabla creado para mostrar datos de administradores.
    */
    private DefaultTableModel createAdminTableModel() {
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.setColumnIdentifiers(new Object[]{
                "Id Usuario", "Nombre de Usuario", "Nombre", "Apellido", "Correo", "AvatarId"
        });

        // add ListSelectionListener to admin table to show admin data in form
        adminManagerPanel.adminTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    int selectedRow = adminManagerPanel.adminTable.getSelectedRow();
                    if (selectedRow != -1) {
                        adminManagerPanel.formPanel.setVisible(true);
                        showAdminDataInForm(selectedRow);
                    } else {
                        adminManagerPanel.formPanel.setVisible(false);
                       cleanAdminForm();
                    }
                }
            }
        });
        return tableModel;
    }
    /**
    * Carga los datos de los administradores en la tabla de administradores.
    *
    * @param adminUsers La lista de usuarios administradores cuyos datos se cargaran en la tabla.
    */
    private void loadAdminDataInTable(List<User> adminUsers) {
        DefaultTableModel tableModel = createAdminTableModel();
        adminManagerPanel.adminTable.setModel(tableModel);
        tableModel.setRowCount(0);

        for (User actual : adminUsers) {
            tableModel.addRow(new Object[]{
                    actual.getUserId(),
                    actual.getUsername(),
                    actual.getFirstName(),
                    actual.getLastName(),
                    actual.getEmail(),
                    actual.getAvatarId()
            });
        }
    }
    /**
    * Muestra la lista de usuarios administradores en la interfaz de usuario.
    * Si no hay administradores en la base de datos, muestra un mensaje de advertencia.
    */
    private void showAdminUsersList() {
        List<User> userAdminList = userQueries.getAdminsData();
        if (userAdminList != null) {
            loadAdminDataInTable(userAdminList);
        } else {
            JOptionPane.showMessageDialog(null, "No hay administradores en la base de datos");
        }
    }
    /**
    * Muestra los datos del administrador seleccionado en el formulario de administracion.
    *
    * @param selectedRow El índice de la fila seleccionada en la tabla de administradores.
    */
    private void showAdminDataInForm(int selectedRow) {
        String userId = adminManagerPanel.adminTable.getValueAt(selectedRow, 0).toString();
        String username = adminManagerPanel.adminTable.getValueAt(selectedRow, 1).toString();
        String firstname = adminManagerPanel.adminTable.getValueAt(selectedRow, 2).toString();
        String lastname = adminManagerPanel.adminTable.getValueAt(selectedRow, 3).toString();
        String email = adminManagerPanel.adminTable.getValueAt(selectedRow, 4).toString();
        String avatarId = adminManagerPanel.adminTable.getValueAt(selectedRow, 5).toString();

        adminManagerPanel.adminNameTextField.setText(firstname);
        adminManagerPanel.adminLastNameTextField.setText(lastname);
        adminManagerPanel.adminEmailTextField.setText(email);
        adminManagerPanel.usernameTxtfield.setText(username);
        adminManagerPanel.avatarComboBox.setSelectedItem(avatarId);
        adminManagerPanel.userIdLabel.setText(userId);

    }
    /**
    * Establece los datos del usuario administrador en el panel de inicio de administrador.
    *
    * @param avatarImage La imagen del avatar del usuario administrador.
    */
    private void setUserAdminData(Image avatarImage) {

        adminHomePanel.adminNameJLabel.setText(user.getFirstName() + " " + user.getLastName());
        adminHomePanel.usernameTxtfield.setText(user.getUsername());
        //image
        int width = adminHomePanel.avatarLabel.getWidth();
        int height = adminHomePanel.avatarLabel.getHeight();

        Image scaledImg = getScaleImage(avatarImage, width, height).getImage();
        ImageIcon scaledAvatarIcon = new ImageIcon(scaledImg);
        adminHomePanel.avatarLabel.setIcon(scaledAvatarIcon);
    }
    /**
    * Inserta un nuevo administrador en la base de datos.
    *
    * @throws UsernameTakenError Si el nombre de usuario ya esta en uso.
    * @throws InvalidDataError   Si los datos del administrador son invalidos.
    */
    private void insertNewAdmin() {
        try {

            String username = adminManagerPanel.usernameTxtfield.getText();
            String email = adminManagerPanel.adminEmailTextField.getText();
            String firstname = adminManagerPanel.adminNameTextField.getText();
            String lastname = adminManagerPanel.adminLastNameTextField.getText();
            String password = adminManagerPanel.adminPasswordTextField.getText();
            String encodePassword = PasswordEncoder.getEncodePassword(password);
            String avatarId= (String) adminManagerPanel.avatarComboBox.getSelectedItem();
            Boolean isAdmin = true;

            User userAdmin = createUser(username,email, firstname,lastname,encodePassword,avatarId,isAdmin);

             if(validateUserData(userAdmin)){
                 userAdmin.setPassword(encodePassword);
                 if (userQueries.registerUser(userAdmin)) {
                     JOptionPane.showMessageDialog(null, "Administrador registrado!");
                     //clean form, hide form panel and show admin table
                     adminManagerPanel.tablePanel.setVisible(true);
                     adminManagerPanel.formPanel.setVisible(false);
                     cleanAdminForm();
                 } else {
                     JOptionPane.showMessageDialog(null, "Error al registrar administrador");
                 }
             }
        } catch (UsernameTakenError | InvalidDataError e) {
            JOptionPane.showMessageDialog(null, "Hubo un error :"+e);
        }
    }
    /**
    * Elimina los datos de un administrador de la base de datos.
    */
    private void deleteAdminData() {
        int userId = Integer.parseInt(adminManagerPanel.userIdLabel.getText());
        if (userQueries.deleteUserById(userId)) {
            JOptionPane.showMessageDialog(null, "Administrador Eliminado!");
            adminManagerPanel.formPanel.setVisible(false);
            adminManagerPanel.tablePanel.setVisible(true);
            cleanAdminForm();
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar!");
        }
    }
    /**
    * Actualiza los datos de un administrador en la base de datos.
    */
    private void updateAdminData() {
        try {
            int userId = Integer.parseInt(adminManagerPanel.userIdLabel.getText());
            String username= adminManagerPanel.usernameTxtfield.getText();
            String email = adminManagerPanel.adminEmailTextField.getText();
            String firstname = adminManagerPanel.adminNameTextField.getText();
            String lastname = adminManagerPanel.adminLastNameTextField.getText();
            String password = adminManagerPanel.adminPasswordTextField.getText();
            String encodePassword = PasswordEncoder.getEncodePassword(password);
            String avatarId= (String) adminManagerPanel.avatarComboBox.getSelectedItem();
            Boolean isAdmin = true;

            User userAdmin = createUser(username, email,firstname, lastname,password, avatarId, isAdmin);
          if(validateUserData(userAdmin)){
              userAdmin.setUserId(userId);
              userAdmin.setPassword(encodePassword);
              if (userQueries.modifyUser(userAdmin)) {
                  JOptionPane.showMessageDialog(null, "Administrador modificado!");
                  cleanAdminForm(); //clean form
                  adminManagerPanel.formPanel.setVisible(false);
              } else {
                  JOptionPane.showMessageDialog(null, "Error al modificar");
              }
          }
        } catch (UsernameTakenError | InvalidDataError e) {
            JOptionPane.showMessageDialog(null, MESSAGE_ERROR+e);
        }
    }

    /**
    * Muestra los datos del centro de salud seleccionado en el formulario de gestion del centro de salud.
    *
    * @param selectedRow El índice de la fila seleccionada en la tabla de centros de salud.
    */
    private void showHealthCenterDataInForm(int selectedRow) {
        //get health center data from table
        int healthCenterId = (int) healthCentersManagerPanel.healthCentersTable.getValueAt(selectedRow, 0);
        String name = healthCentersManagerPanel.healthCentersTable.getValueAt(selectedRow, 1).toString();
        String address = healthCentersManagerPanel.healthCentersTable.getValueAt(selectedRow, 2).toString();
        String phone = healthCentersManagerPanel.healthCentersTable.getValueAt(selectedRow, 3).toString();
        String about = healthCentersManagerPanel.healthCentersTable.getValueAt(selectedRow, 4).toString();
        String imageName = healthCentersManagerPanel.healthCentersTable.getValueAt(selectedRow, 6).toString();
        //set text in form
        healthCentersManagerPanel.healthCenterNameTextField.setText(name);
        healthCentersManagerPanel.healthCenterAddressTextArea.setText(address);
        healthCentersManagerPanel.healthCenterPhoneTextField.setText(phone);
        healthCentersManagerPanel.healthCenterDescriptionTextArea.setText(about);
        healthCentersManagerPanel.healthCenterIdLabel.setText(String.valueOf(healthCenterId));

        String imagePath = getCoverImagePath(imageName);
        Image coverImage = getCoverImage(imagePath);

        int width = healthCentersManagerPanel.coverImageLabel.getWidth();
        int height = healthCentersManagerPanel.coverImageLabel.getHeight();

        Image scaledCoverImage = getScaleImage(Objects.requireNonNull(coverImage), width, height).getImage();
        ImageIcon scaledCoverImageIcon = new ImageIcon(scaledCoverImage);
        healthCentersManagerPanel.coverImageLabel.setIcon(scaledCoverImageIcon);

        //show specialties and languages
        showSpecialtiesByHealthCenter(healthCenterId);
        showLanguagesByHealthCenter(healthCenterId);

    }
    /**
    * Crea un modelo de tabla para mostrar los datos de los centros de salud en el panel de gestion de centros de salud.
    *
    * @return El modelo de tabla creado.
    */
    private DefaultTableModel createHealthCenterTableModel() {
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.setColumnIdentifiers(new Object[]{
                "Id", "Nombre", "Dirección", "Teléfono", "Acerca de", "Fecha de Registro", "Foto"
        });

        healthCentersManagerPanel.healthCentersTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    int selectedRow = healthCentersManagerPanel.healthCentersTable.getSelectedRow();

                    if (selectedRow != -1) {
                        //show panel to edit health center
                        healthCentersManagerPanel.formPanel.setVisible(true);
                        healthCentersManagerPanel.attributesPanel.setVisible(true);
                        healthCentersManagerPanel.modifyDataButton.setVisible(true);

                        healthCentersManagerPanel.insertnewDataButton.setVisible(false);
                        showHealthCenterDataInForm(selectedRow);
                    } else {
                        healthCentersManagerPanel.formPanel.setVisible(false);
                        clearHealthCenterForm();
                    }
                }
            }
        });


        return tableModel;
    }
    /**
    * Carga los datos de los centros de salud en la tabla de gestion de centros de salud.
    *
    * @param healthCenters La lista de centros de salud a cargar en la tabla.
    */
    private void loadHealthCentersDataInTable(List<HealthCenter> healthCenters) {
        DefaultTableModel tableModel = createHealthCenterTableModel();
        healthCentersManagerPanel.healthCentersTable.setModel(tableModel);
        tableModel.setRowCount(0);

        for (HealthCenter actual : healthCenters) {
            tableModel.addRow(new Object[]{
                    actual.getHealthCenterId(),
                    actual.getName(),
                    actual.getAddress(),
                    actual.getTelephone(),
                    actual.getAbout(),
                    actual.getUploadDate(),
                    actual.getCoverImage(),
            });
        }
    }
    /**
    * Abre un dialogo de seleccion de archivo para obtener la ruta de la imagen de portada seleccionada.
    */
    private void getCoverImageSelectedPath() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de imagen", "png", "jpg");
        fileChooser.setFileFilter(filter);
        int window = fileChooser.showOpenDialog(null);
        if (window == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            coverImagePath = file.getPath();

        }
    }
    /**
    * Obtiene la imagen de portada correspondiente a la ruta de la imagen especificada.
    * @param coverImagePath La ruta de la imagen de portada.
    * @return La imagen de portada.
    */
    private Image getCoverImage(String coverImagePath) {
        if (!coverImagePath.isEmpty()) {
            ImageIcon coverImageIcon = new ImageIcon(coverImagePath);
            return coverImageIcon.getImage();
        }else{
           return null;
        }
    }

    /**
    * Establece la imagen de portada en el JLabel correspondiente.
    * @param coverImage La imagen de portada a establecer.
    */
    private void setCoverImageToLabel(Image coverImage) {
        int width = healthCentersManagerPanel.coverImageLabel.getWidth();
        int height = healthCentersManagerPanel.coverImageLabel.getHeight();
        Image scaledCoverImage = coverImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledCoverImageIcon = new ImageIcon(scaledCoverImage);
        healthCentersManagerPanel.coverImageLabel.setIcon(scaledCoverImageIcon);
    }
    /**
    * Muestra los idiomas asociados con un centro de salud especifico en el cuadro combinado correspondiente.
    *
    * @param healthCenterId El ID del centro de salud para el cual se mostraran los idiomas.
    */
        private void showLanguagesByHealthCenter(int healthCenterId) {
        showLanguagesByHealthCenterPanel(healthCentersManagerPanel.languagesByHealthCenterIdCombobox, healthCenterId);
    }
    /**
    * Muestra los idiomas asociados con un centro de salud específico en el cuadro combinado correspondiente.
    *
    * @param healthCenterId El ID del centro de salud para el cual se mostraran los idiomas.
    */
    private void showLanguagesByFilteredHealthCenter(int healthCenterId) {
        showLanguagesByHealthCenterPanel(searchPanel.languagesComboBox, healthCenterId);
    }
    /**
    * Muestra las especialidades asociadas con un centro de salud específico en el cuadro combinado correspondiente.
    *
    * @param healthCenterId El ID del centro de salud para el cual se mostraran las especialidades.
    */
    private void showSpecialtiesByHealthCenter(int healthCenterId){
        showSpecialtiesByHealthCenterPanel(healthCentersManagerPanel.specialtiesByHealthCenterIdComboBox, healthCenterId);
    }

    /**
    * Muestra las especialidades asociadas con un centro de salud filtrado en el cuadro combinado correspondiente.
    *
    * @param healthCenterId El ID del centro de salud filtrado para el cual se mostraran las especialidades.
    */
    private void showSpecialtiesByFilteredHealthCenter(int healthCenterId) {
        showSpecialtiesByHealthCenterPanel(searchPanel.specialtiesComboBox, healthCenterId);
    }
    /**
    * Muestra las especialidades asociadas con un centro de salud en el JComboBox proporcionado.
    *
    * @param comboBox      El JComboBox en el que se mostraran las especialidades.
    * @param healthCenterId El ID del centro de salud del cual se obtendran las especialidades.
    */
    private void showSpecialtiesByHealthCenterPanel(JComboBox<String> comboBox, int healthCenterId){
        List<String> specialtiesByHealthCenterId = specialtyQueries.getSpecialtiesByHealthCenterId(healthCenterId);
        fillComboBox(comboBox, specialtiesByHealthCenterId);
    }

    /**
    * Muestra los idiomas asociados con un centro de salud en el JComboBox proporcionado.
    *
    * @param comboBox      El JComboBox en el que se mostraran los idiomas.
    * @param healthCenterId El ID del centro de salud del cual se obtendran los idiomas.
    */
    private void showLanguagesByHealthCenterPanel(JComboBox<String> comboBox, int healthCenterId) {
        List<String> languagesByHealthCenterId = languageQueries.getLanguagesByHealthCenterId(healthCenterId);
        fillComboBox(comboBox, languagesByHealthCenterId);
    }
    /**
    * Muestra las especialidades médicas disponibles en un JComboBox.
    * Si no hay especialidades médicas registradas en la base de datos, se muestra un mensaje específico.
    */
    private void showSpecialtiesByName() {
        List<String> specialties = specialtyQueries.getSpecialtiesList();
        fillComboBox(healthCentersManagerPanel.specialtySelectorComboBox,
                specialties );

    }
    /**
    * Muestra los idiomas disponibles en un JComboBox.
    */
    private void showLanguagesByName() {
        showLanguagesByNamePanel(healthCentersManagerPanel.languageSelectorCombobox);
    }
    /**
    * Muestra los idiomas disponibles en un JComboBox en el panel de búsqueda.
    */
    private void showLanguagesByNameSelector() {
        showLanguagesByNamePanel(searchPanel.languageSelectorComboBox);
    }
    /**
    * Muestra los idiomas disponibles en un JComboBox especifico.
    *
    * @param comboBox El JComboBox donde se mostraran los idiomas.
    */
    private void showLanguagesByNamePanel(JComboBox<String> comboBox) {
        List<String> languages = languageQueries.getLanguagesName();
        fillComboBox(comboBox, languages);
    }
    /**
    * Llena la tabla de centros medicos con los datos obtenidos de la base de datos.
    * Si no hay centros medicos registrados, muestra un mensaje de advertencia.
    */
    private void fillHealthCentersTable() {
        List<HealthCenter> healthCenters = healthCenterQueries.getHealthCentersList();
        if (healthCenters != null) {
            loadHealthCentersDataInTable(healthCenters);
        } else {
            JOptionPane.showMessageDialog(null, "No hay centros médicos registrados en la base de datos");
        }

    }
    /**
    * Llena un JComboBox con los elementos proporcionados en la lista.
    * Si la lista esta vacia o es nula, muestra un mensaje de advertencia.
    *
    * @param comboBox     El JComboBox que se va a llenar.
    * @param items        La lista de elementos para llenar el JComboBox.
    * @param <T>          El tipo de elementos en la lista y en el JComboBox.
    */
    private <T> void fillComboBox(JComboBox<T> comboBox, List<T> items) {
        comboBox.removeAllItems();
        if (items != null && !items.isEmpty()) {
            for (T item : items) {
                comboBox.addItem(item);
            }
        }
    }
/**
 * Inserta los datos de un nuevo centro médico en la base de datos.
 * Obtiene los datos del formulario de entrada en la interfaz de usuario.
 * Muestra un mensaje de éxito o error dependiendo del resultado de la operación.
 */
    private void insertNewHealthCenterData() {
        try {
            String name = healthCentersManagerPanel.healthCenterNameTextField.getText();
            String about = healthCentersManagerPanel.healthCenterDescriptionTextArea.getText();
            String telephone = healthCentersManagerPanel.healthCenterPhoneTextField.getText();
            String address = healthCentersManagerPanel.healthCenterAddressTextArea.getText();
            String coverImage = getImageName(coverImagePath);
            HealthCenter newHealthCenter = getHealthCenterData(name,
                    about,
                    telephone,
                    address,
                    coverImage);
            if (healthCenterQueries.addHealthCenter(newHealthCenter)) {
                JOptionPane.showMessageDialog(null, "Centro médico registrado correctamente");

                clearHealthCenterForm();
                healthCentersManagerPanel.tablePanel.setVisible(true);
                healthCentersManagerPanel.attributesPanel.setVisible(true);
                healthCentersManagerPanel.formPanel.setVisible(false);
                healthCentersManagerPanel.insertnewDataButton.setVisible(false);

            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar centro médico");
            }
        } catch (HealthCenterNameExistError | InvalidDataError e) {
            JOptionPane.showMessageDialog(null, MESSAGE_ERROR+e);
        }


    }

    /**
     * modifica los datos de un centro medico
     */
    private void modifyHealthCenterData() {
        try {
            String name = healthCentersManagerPanel.healthCenterNameTextField.getText();
            String about = healthCentersManagerPanel.healthCenterDescriptionTextArea.getText();
            String telephone = healthCentersManagerPanel.healthCenterPhoneTextField.getText();
            String address = healthCentersManagerPanel.healthCenterAddressTextArea.getText();
            String coverImage = getImageName(coverImagePath);
            HealthCenter updateHealthCenter = getHealthCenterData(name,
                    about,
                    telephone,
                    address,
                    coverImage);
            if (healthCenterQueries.updateHealthCenter(updateHealthCenter)) {
                JOptionPane.showMessageDialog(null, "Centro médico modificado correctamente");

                clearHealthCenterForm();
                healthCentersManagerPanel.tablePanel.setVisible(true);
                healthCentersManagerPanel.attributesPanel.setVisible(true);
                healthCentersManagerPanel.formPanel.setVisible(false);
                healthCentersManagerPanel.insertnewDataButton.setVisible(false);

            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar entro médico");
            }
        } catch (InvalidDataError e) {
            JOptionPane.showMessageDialog(null, MESSAGE_ERROR+e);
        }


    }
    /**
    * Elimina los datos de un centro medico de la base de datos.
    * Obtiene el identificador del centro medico seleccionado en la interfaz de usuario.
    * Muestra un mensaje de éxito o error dependiendo del resultado de la operación.
    */
    private void deleteHealthCenterData() {
        int healthCenterId = Integer.parseInt(healthCentersManagerPanel.healthCenterIdLabel.getText());
        if (healthCenterQueries.deleteHealthCenterById(healthCenterId)) {
            JOptionPane.showMessageDialog(null, "Centro Médico Eliminado!");
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar!");
        }

    }
    /**
    * Crea y devuelve un objeto HealthCenter con los datos proporcionados.
    * Verifica que los campos requeridos no estén vacíos y que el número de teléfono tenga 10 digitos.
    * Si algún campo esta vacio o el numero de telefono no tiene 10 digitos, lanza una excepcion InvalidDataError.
    * @param name el nombre del centro medico.
    * @param about la descripción del centro medico.
    * @param telephone el número de telefono del centro medico.
    * @param address la direccion del centro medico.
    * @param coverImage el nombre de la imagen de portada del centro medico.
    * @return un objeto HealthCenter con los datos proporcionados.
    * @throws InvalidDataError si algun campo esta vacío o el numero de telefono no tiene 10 digitos.
    */
    private HealthCenter getHealthCenterData(String name,
                                             String about,
                                             String telephone,
                                             String address,
                                             String coverImage
    ) throws InvalidDataError {
        HealthCenter newHealthCenter = new HealthCenter();
        if (!name.isEmpty()
                && !about.isEmpty()
                && !telephone.isEmpty()
                && !address.isEmpty()
                && !coverImage.isEmpty()) {

            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            newHealthCenter.setUploadDate(sqlDate);
            newHealthCenter.setName(name);
            newHealthCenter.setAbout(about);
            newHealthCenter.setAddress(address);
            newHealthCenter.setTelephone(telephone);
            newHealthCenter.setCoverImage(coverImage);


            int minimumDigits = 10;
            if ((telephone.length() != minimumDigits)) {
                throw new InvalidDataError("El teléfono debe tener 10 dígitos");
            }

        } else {
            throw new InvalidDataError("Complete los campos y elija una imagen");
        }
        return newHealthCenter;
    }

    /**
    * Limpia todos los campos del formulario de centro médico y elimina cualquier imagen de portada actualmente mostrada.
    */
    private void clearHealthCenterForm() {

        healthCentersManagerPanel.healthCenterNameTextField.setText("");
        healthCentersManagerPanel.healthCenterDescriptionTextArea.setText("");
        healthCentersManagerPanel.healthCenterPhoneTextField.setText("");
        healthCentersManagerPanel.healthCenterAddressTextArea.setText("");
        healthCentersManagerPanel.healthCenterIdLabel.setText("");
        healthCentersManagerPanel.coverImageLabel.setIcon(null);

    }
    /**
    * Toma la ruta de la imagen de portada seleccionada y devuelve solo el nombre del archivo.
    * @param selectedCoverImage La ruta de la imagen de portada seleccionada.
    * @return El nombre del archivo de la imagen de portada.
    */
    private String getImageName(String selectedCoverImage) {
        int lastSlash = selectedCoverImage.lastIndexOf("\\");
        return selectedCoverImage.substring(lastSlash + 1);
    }
    /**
    * Agrega la especialidad seleccionada al centro médico actual.
    */
    private void addSelectedSpecialtyToCurrentHealthCenter() {
        try {
            String selectedSpecialty = (String) healthCentersManagerPanel.specialtySelectorComboBox.getSelectedItem();
            int healthCenterId = Integer.parseInt(healthCentersManagerPanel.healthCenterIdLabel.getText());

            if (!(Objects.equals(selectedSpecialty, SELECCIONE_OPCION))) {
                if (specialtyQueries.addSpecialtyToHealthCenter(healthCenterId, selectedSpecialty)) {
                    JOptionPane.showMessageDialog(null, "Especialidad agregada a centro médico!");
                    showSpecialtiesByHealthCenter(healthCenterId); //refresh  items
                } else {
                    JOptionPane.showMessageDialog(null, "Error al agregar");
                }
                healthCentersManagerPanel.specialtySelectorComboBox.setSelectedItem(SELECCIONE_OPCION);
            }
        } catch (SpecialtyExistError e) {
            JOptionPane.showMessageDialog(null, "Esa especialidad ya esta agregada!");
        }
    }
    /**
    * Agrega el idioma seleccionado al centro médico actual.
    */
    private void addSelectedLanguageToCurrentHealthCenter() {
        try {
            String selectedLanguage = (String) healthCentersManagerPanel.languageSelectorCombobox.getSelectedItem();
            int healthCenterId = Integer.parseInt(healthCentersManagerPanel.healthCenterIdLabel.getText());

            if (!(Objects.equals(selectedLanguage, SELECCIONE_OPCION))) {
                if (languageQueries.addLanguageToCurrentHealthCenter(healthCenterId, selectedLanguage)) {
                    showLanguagesByHealthCenter(healthCenterId);
                    JOptionPane.showMessageDialog(null, "Idioma agregado a centro médico!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al agregar");
                }
                healthCentersManagerPanel.languageSelectorCombobox.setSelectedItem(SELECCIONE_OPCION);
            }
        } catch (LanguageExistError e) {
            JOptionPane.showMessageDialog(null, "Ese idioma ya esta agregado!");

        }
    }
    /**
    * Elimina el idioma seleccionado del centro medico actual.
    */
    private void deleteSelectedLanguageFromCurrentHealthCenter(){

        String selectedLanguage = (String) healthCentersManagerPanel.languagesByHealthCenterIdCombobox.getSelectedItem();
        int healthCenterId = Integer.parseInt(healthCentersManagerPanel.healthCenterIdLabel.getText());

        if (!(Objects.equals(selectedLanguage, null))) {
            if (languageQueries.deleteLanguageFromHealthCenter(healthCenterId, selectedLanguage)) {
                    showLanguagesByHealthCenter(healthCenterId);
                    JOptionPane.showMessageDialog(null, "Idioma eliminado!");
            } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
            }
        }else{
            JOptionPane.showMessageDialog(null, "SELECCIONE_OPCION un idioma");
        }

    }
    /**
    * Elimina la especialidad seleccionada del centro medico actual.
    */
    private void deleteSelectedSpecialtyFromCurrentHealthCenter(){

        String selectedSpecialty = (String) healthCentersManagerPanel.specialtiesByHealthCenterIdComboBox.getSelectedItem();
        int healthCenterId = Integer.parseInt(healthCentersManagerPanel.healthCenterIdLabel.getText());

        if (!(Objects.equals(selectedSpecialty, null))) {
            if (specialtyQueries.deleteSpecialtyFromHealthCenter(healthCenterId, selectedSpecialty)) {
                JOptionPane.showMessageDialog(null, "Especialidad eliminada!");
                showSpecialtiesByHealthCenter(healthCenterId); //refresh JCombobox items
            } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
            }
        }else{
            JOptionPane.showMessageDialog(null, "SELECCIONE_OPCION una especialidad");
        }

    }
    /**
    * Crea un modelo de tabla para mostrar las reseñas.
    */
    private DefaultTableModel createReviewTableModel() {
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.setColumnIdentifiers(new Object[]{
                "Review Id","Id Usuario", "Descripción","Puntuación","Fecha de Publicación",
        });

        searchPanel.reviewsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    int selectedRow = searchPanel.reviewsTable.getSelectedRow();

                    if (selectedRow != -1) {
                        //get username and avatar by userId
                        int userId = (int) searchPanel.reviewsTable.getValueAt(selectedRow, 1);
                        User reviewUser = userQueries.getUserDetailsById(userId);
                        showUsersReviewInForm(selectedRow, reviewUser);
                        showEditReviewOptions(userId);
                    } else {
                        clearHealthCenterForm();
                    }
                }
            }
        });


        return tableModel;
    }
/**
 * Determina que opciones de edicion de reseñas mostrar en funcion del usuario que esta conectado y el usuario que realizó la reseña seleccionada.
 * @param userId El ID del usuario que realizo la reseña seleccionada.
 */
    private void showEditReviewOptions(int userId) {

        if (user != null) {
            int loggedInUserId = user.getUserId();
            //show buttons
            searchPanel.deleteReviewButton.setVisible(loggedInUserId == userId);
        }
    }
    /**
    * Muestra los detalles de la review de un usuario seleccionada en el formulario de busqueda.
    *
    * @param selectedRow El índice de la fila seleccionada en la tabla de reseñas.
    * @param user        El objeto User que representa al usuario que realizó la reseña.
    */
    private void showUsersReviewInForm(int selectedRow, User user){

        String description = (String) searchPanel.reviewsTable.getValueAt(selectedRow, 2);
        int rating = (int) searchPanel.reviewsTable.getValueAt(selectedRow, 3);
        java.util.Date publishDate = (Date) searchPanel.reviewsTable.getValueAt(selectedRow, 4);

        searchPanel.publishDateLabel.setText(String.valueOf(publishDate));
        searchPanel.ratingLabel.setText((rating) + " estrellas");
        searchPanel.usernameLabel.setText(user.getUsername());
        searchPanel.fullnameLabel.setText(user.getFirstName() + " "+ user.getLastName());
        searchPanel.descriptionTextArea.setText(description);

        String avatarId = user.getAvatarId();
        Image img = getAvatarImage(avatarId);

        Image scaledImg = img.getScaledInstance(searchPanel.avatarLabel.getWidth(), searchPanel.avatarLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledAvatarIcon = new ImageIcon(scaledImg);
        searchPanel.avatarLabel.setIcon(scaledAvatarIcon);

    }
    /**
    * Carga las revies proporcionadas en una tabla en el panel de busqueda.
    *
    * @param reviews La lista de reseñas que se cargaran en la tabla.
    */
    private void loadReviewInTable(List<Review> reviews) {
        DefaultTableModel tableModel = createReviewTableModel();
        searchPanel.reviewsTable.setModel(tableModel);
        tableModel.setRowCount(0);

        for (Review actual : reviews) {
            tableModel.addRow(new Object[]{
                    actual.getReviewId(),
                    actual.getUserId(),
                    actual.getDescription(),
                    actual.getRating(),
                    actual.getPublishDate(),
            });
        }
    }
    /**
    * Crea una nueva review utilizando los datos proporcionados en la pagina de revisión.
    *
    * @param selectedRow El índice de la fila seleccionada en la tabla de centros de salud.
    * @return La nueva reseña creada con los datos ingresados por el usuario.
    */
    private Review createReview(int selectedRow){

        int healthCenterId = (int) searchPanel.filteredHealthCenterTable.getValueAt(selectedRow, 0);
        int userId = user.getUserId();
        int rating = Integer.parseInt(reviewPage.ratingTextField.getText().trim());
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date publishDate = new java.sql.Date(utilDate.getTime());
        String description = reviewPage.descriptionTextArea.getText();

        Review userReview = new Review();
        userReview.setRating(rating);
        userReview.setUserId(userId);
        userReview.setPublishDate(publishDate);
        userReview.setDescription(description);
        userReview.setHealthCenterId(healthCenterId);

        return userReview;
    }
    /**
    * Valida los datos de una reseña antes de su registro.
    *
    * @param userReview La review a validar.
    * @return True si los datos de la reseña son validos, False si no lo son.
    */
    private boolean validateReviewData(Review userReview){

        if(userReview.getRating() < 1 || userReview.getRating() > 5 ){
            JOptionPane.showMessageDialog(null, "Ingrese una puntuación entre 1 y 5");
            return false;
        }

        return true;
    }
    /**
    * Publica la review de un usuario sobre un centro médico seleccionado.
    *
    * @param selectedRow La fila seleccionada en la tabla de centros médicos.
    */
    private void sendUserReview(int selectedRow){

        Review userReview = createReview(selectedRow);
        try{
            if(validateReviewData(userReview)){
                if(reviewQueries.publishReview(userReview)){
                    //clean window and close
                     reviewPage.ratingTextField.setText("");
                     reviewPage.descriptionTextArea.setText("");
                     reviewPage.dispose();

                    JOptionPane.showMessageDialog(null, "Se publicó la reseña!");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al registrar administrador");
                }
            }

        }catch (RatingCheckError e){
            JOptionPane.showMessageDialog(null, "Hubo un error :"+e);
        }
    }
    /**
    * Limpia todos los campos del panel de búsqueda y del panel de review en el panel de búsqueda.
    */
    private void clearSearchPanel(){
        //reset form
        searchPanel.nameTxtField.setText("");
        searchPanel.aboutTextArea.setText("");
        searchPanel.telephoneTxtField.setText("");
        searchPanel.addressTxtField.setText("");
        searchPanel.coverImageLabel.setIcon(null);

        //reset form review
        clearReviewPanel();
    }

    /**
     * limpia panel de reviews
     */
    private void clearReviewPanel(){
        //reset form review
        searchPanel.avatarLabel.setIcon(null);
        searchPanel.usernameLabel.setText("");
        searchPanel.fullnameLabel.setText("");
        searchPanel.descriptionTextArea.setText("");
        searchPanel.ratingLabel.setText("");
        searchPanel.publishDateLabel.setText("");
    }
    /**
    * Limpia todos los campos del formulario de reviews en el panel de bsqueda.
    */
    private void clearRegisterFormPanel(){
        clientRegisterForm.usernameTextField.setText("");
        clientRegisterForm.firstnameTextField.setText("");
        clientRegisterForm.userEmailTextField.setText("");
        clientRegisterForm.userPasswordTextField.setText("");
        clientRegisterForm.userLastNameTextField.setText("");
        clientRegisterForm.avatarLabel.setIcon(null);
        clientRegisterForm.avatarComboBox.setSelectedItem(SELECCIONE_OPCION);
    }
    /**
    * Elimina la reseña del usuario actualmente conectado.
    *
    * @param selectedRow indice de la fila seleccionada en la tabla de reseñas.
    */
    private void deleteLoggedInReview(int selectedRow) {

        int reviewId = (int) searchPanel.reviewsTable.getValueAt(selectedRow, 0);

        if(reviewQueries.deleteReviewByReviewId(reviewId)){

            JOptionPane.showMessageDialog(null, "Reseña eliminada!");
        }else{
            JOptionPane.showMessageDialog(null, "Hubo un error!");
        }

    }
    /**
    * Edita la reseña del usuario actualmente conectado.
    *
    * @param selectedRow indice de la fila seleccionada en la tabla de reseñas.
    */
    private void editLoggedInReview(int selectedRow){
        try {
            Review review1 = createReview(selectedRow);
            if(reviewQueries.updateReview(review1)){
                JOptionPane.showMessageDialog(null, "Reseña modificada!");
            }else{
                JOptionPane.showMessageDialog(null, "Hubo un error!");
            }
        } catch (RatingCheckError e) {
           JOptionPane.showMessageDialog(null, "Hubo un error: "+e);
        }
    }
    /**
     * Obtiene la puntuacion seleccionada por el usuario a partir de un grupo de botones de opcion.
    *
    * @param ratingGroup El grupo de botones de opcion que contiene las opciones de puntuacion.
    * @return La puntuacion seleccionada como un valor double. Retorna -1 si no se selecciona ningun boton.
    */
    private double getSelectedRating(ButtonGroup ratingGroup) {
        for (Enumeration<AbstractButton> buttons = ratingGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                String buttonText = button.getText();
                // Encuentra la posicion del espacio en blanco antes de "estrellas"
                int spaceIndex = buttonText.indexOf(" ");
                // Obtiene la subcadena antes del espacio en blanco y la convierte en un numero
                String ratingString = buttonText.substring(0, spaceIndex);
                return Double.parseDouble(ratingString);
            }
        }
        return -1; // Valor predeterminado si no se selecciona ningun botun (manejar este caso segun sea necesario)
    }

    /**
     * filtrar centros medicos
     * @param healthCenters centros medicos
     */

   private void handleFilterAction(List<HealthCenter> healthCenters) {
       double selectedRating = getSelectedRating(searchPanel.buttonGroup);
       String selectedLanguages = (String) searchPanel.languageSelectorComboBox.getSelectedItem();

       // Verifica si se ha seleccionado al menos un criterio de filtro
       if (selectedRating == -1 && (selectedLanguages == null || selectedLanguages.equals(SELECCIONE_OPCION))) {
           JOptionPane.showMessageDialog(null, "Por favor, selecciona al menos un criterio de filtro.");
           return;
       }

       List<HealthCenter> filteredHealthCenters;

       if (selectedRating != -1) {
           // Filtrar por rating
           filteredHealthCenters = reviewQueries.filterHealthCentersByRating(healthCenters, selectedRating);
       } else {
           // Si no hay rating seleccionado o el idioma seleccionado es "Seleccione", no se filtra por idioma
           filteredHealthCenters = languageQueries.filterHealthCentersByLanguage(healthCenters, selectedLanguages);
       }

       if (!filteredHealthCenters.isEmpty()) {
           loadfilteredHealthCenterDataInTable(filteredHealthCenters);
       } else {
           JOptionPane.showMessageDialog(null, "No hay centros de salud que cumplan con los filtros seleccionados.");
       }
   }




}
