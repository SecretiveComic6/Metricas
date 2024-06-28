import com.health.finder.model.query.SpecialtyQueries;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpecialtyQueriesTest {

    private SpecialtyQueries specialtyQueries;

    @BeforeEach
    void setUp() {
        specialtyQueries = new SpecialtyQueries();
    }


    @Test
    @DisplayName("Obtener id de una especialidad por su nombre")
    void getSpecialtyIdByNameTest(){
        String specialtyName = "Family Medicine";
        int expectedId = 7;
        int specialtyId = specialtyQueries.getSpecialtyIdByName(specialtyName);
        assertEquals(expectedId, specialtyId);
    }

    @Test
    @DisplayName("Recuperar lista de especialidades")
    void getSpecialtiesListTest(){
        List<String> specialties = specialtyQueries.getSpecialtiesList();

        assertNotNull(specialties);
    }

    @Test
    @DisplayName("Recuperar id de especialidad debe devolver -1 cuando no existe la especialidad")
    void getSpecialtyIdByNameTestFailed() {
        String specialty = "Geriatría";

        int specialtyId = specialtyQueries.getSpecialtyIdByName(specialty);

        assertEquals(-1, specialtyId, "Se esperaba que el ID de la especialidad fuera -1 porque no existe la especialidad");
    }

    @Test
    @DisplayName("Recuperar lista de especialidades de un centro medico pero debe ser lista vacia")
    void getSpecialtiesByHealthCenterIdTestFailed(){
        int healthCenterId = 21;

        List<String> specialtiesList = specialtyQueries.getSpecialtiesByHealthCenterId(healthCenterId);
        assertEquals(Collections.emptyList(), specialtiesList, "Se esperaba la lista vacia porque el centro medico no existe");

    }


}

