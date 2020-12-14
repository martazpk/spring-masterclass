package pl.training.shop.users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.training.shop.ShopApplication;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ShopApplication.class
)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class UsersIntegrationTest {

    private static final Long USER_ID = 1L;
    private static final String USER_NAME = "Peter";
    private static final String USER_SURNAME = "Parker";
    private static final String USER_EMAIL = "peter@training.pl";

    private final User user = new User();
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private MockMvc chrome;

    @BeforeEach
    void setUp() {
        user.setName(USER_NAME);
        user.setSurname(USER_SURNAME);
        user.setEmail(USER_EMAIL);
    }

    @Test
    void shouldReturnUserById() throws Exception {
        entityManager.persist(user);
        chrome.perform(get("/api/users/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(USER_NAME)))
                .andExpect(jsonPath("$.surname", is(USER_SURNAME)))
                .andExpect(jsonPath("$.emailAddress", is(USER_EMAIL)));
    }
}