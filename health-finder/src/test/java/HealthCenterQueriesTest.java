import com.health.finder.model.HealthCenter;
import com.health.finder.model.HealthCenterNameExistError;
import com.health.finder.model.query.HealthCenterQueries;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HealthCenterQueriesTest {
    private HealthCenter healthCenter;
    private HealthCenterQueries healthCenterQueries;

    @BeforeEach
    void setUp() {
        healthCenter = new HealthCenter();
        healthCenterQueries = new HealthCenterQueries();
    }


   @Test
   @Order(2)
   @DisplayName("Insertar centro médico que ya existe")
   void insertHealthCenterTestFailed(){
        healthCenter.setName("Centro de salud mérida");
        healthCenter.setAbout("Su actividad es la primera barrera ante las enfermedades, además de que se trabaja día a día en materia de salud mental y ante cualquier eventual complicación" +
                "\n" +
                "Tiene especialidades como radiología y psiquiatría que se complementan con el servicio que brinda las 24 horas del día durante toda la semana, los 365 días del año. ");
        healthCenter.setAddress("Mérida, Calle 50 x 65 y 67");
        healthCenter.setTelephone("9992328123");
        String imagePath="SSY_3814.jpg";
        healthCenter.setCoverImage(imagePath);
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        healthCenter.setUploadDate(sqlDate);

        assertThrows(HealthCenterNameExistError.class, () -> {
           healthCenterQueries.addHealthCenter(healthCenter);
       }, "Se esperaba que la inserción del centro de salud fallara debido a un nombre duplicado");

    }



    @Test
    @Order(1)
    @DisplayName("Insertar nuevo centro medico de prueba")
    void insertNewHealthCenterTestSuccessful() throws HealthCenterNameExistError {
        String name = "Clinica de prueba";
        healthCenter.setName(name);
        healthCenter.setAbout("Esta clinica cuenta con diferentes servicios medicos");
        healthCenter.setAddress("Mérida, Calle 23 x 43 y 41 colonia las Américas");
        healthCenter.setTelephone("9992328123");
        String imagePath = "fake_hc.jpg";
        healthCenter.setCoverImage(imagePath);
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        healthCenter.setUploadDate(sqlDate);

        assertTrue(healthCenterQueries.addHealthCenter(healthCenter));

    }
    @Test
    @Order(3)
    @DisplayName("Eliminar centro medico que ya existe")
    void deleteHealthCenterByNameTestSuccessful() {
        String name = "Clinica de prueba";
        healthCenter.setName(name);
        assertTrue(healthCenterQueries.deleteHealthCenterByName(name));

    }


    @Test
    @Order(4)
    @DisplayName("Recuperar lista de centros medicos con cierta especialidad")
    void getHealthCenterBySpecialtyTestSuccessful(){
        String specialty = "Cardiology";
        List<HealthCenter> healthCenterList = healthCenterQueries.getHealthCentersBySpecialty(specialty);

        assertNotNull(healthCenterList);
    }



}

