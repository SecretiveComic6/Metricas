import com.health.finder.model.PasswordEncoder;
import com.health.finder.model.User;
import com.health.finder.model.UsernameTakenError;
import com.health.finder.model.query.UserQueries;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UserQueriesTest {
    private UserQueries userQueries;
    private User user;
    @BeforeEach
    void setUp() {
        userQueries = new UserQueries();
        user = new User();
    }
    @Test
    @Order(1)
    @DisplayName("Se inserta nuevo cliente en los registros")
    void registerUserTest() throws UsernameTakenError {
        user.setUsername("sofivergara");
        user.setFirstName("Sofia");
        user.setLastName("Vergara Sanchez");
        user.setIsAdmin(false);
        user.setAvatarId("avatar_3");
        user.setEmail("sofia@hotmail.com");
        String password = "sofia123455.";
        String encodePassword = PasswordEncoder.getEncodePassword(password);
        user.setPassword(encodePassword);

        assertTrue(userQueries.registerUser(user));


    }





    @Test
    @Order(4)
    @DisplayName("Se elimina un usuario registrado")
    void deleteUserByIdTest(){

        String username = "sofivergara";
        assertTrue(userQueries.deleteUserByUsername(username));
    }

}

