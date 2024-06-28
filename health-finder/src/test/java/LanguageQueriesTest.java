
import com.health.finder.model.query.LanguageQueries;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LanguageQueriesTest {
    private LanguageQueries languageQueries;

    @BeforeEach
    void setUp() {
        languageQueries = new LanguageQueries();
    }

    @Test
    @DisplayName("Recuperar lista de idiomas")
    void getLanguagesNameTest(){
        List<String> languageList = languageQueries.getLanguagesName();
        assertNotNull(languageList);
    }

    @Test
    @DisplayName("Recuperar idioma por su id")
    void getLanguageIdByName(){
        String name =  "Italiano";
        int languageId = languageQueries.getLanguageIdByName(name);
        int expectedId= 4;
        assertEquals(languageId, expectedId);
    }

    @Test
    @DisplayName("Recuperar lista de los idiomas de un centro medico")
    void getLanguagesByHealthCenterId(){
        int healthCenterId = 6;
        List<String> languagesList = languageQueries.getLanguagesByHealthCenterId(healthCenterId);

        assertNotNull(languagesList);

    }




}