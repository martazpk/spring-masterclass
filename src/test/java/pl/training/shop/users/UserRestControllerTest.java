package pl.training.shop.users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRestController.class)
@ExtendWith(SpringExtension.class)
class UserRestControllerTest {

    private static final Long USER_ID = 1L;
    private static final String USER_NAME = "Peter";
    private static final String USER_SURNAME = "Parker";
    private static final String USER_EMAIL = "peter@training.pl";

    private final User user = new User();
    private final UserTransferObject userDto = new UserTransferObject();
    @MockBean
    private UserService userService;
    @MockBean
    private UserMapper userMapper;
    @Autowired
    private MockMvc chrome;

    @BeforeEach
    void setUp() {
        user.setId(USER_ID);
        user.setName(USER_NAME);
        user.setSurname(USER_SURNAME);
        user.setEmail(USER_EMAIL);

        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setEmailAddress(user.getEmail());

        when(userService.getById(USER_ID)).thenReturn(user);
        when(userMapper.toTransferObject(user)).thenReturn(userDto);
    }

    @Test
    void shouldReturnUserById() throws Exception {
        chrome.perform(get("/api/users/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(userDto.getName())))
                .andExpect(jsonPath("$.surname", is(userDto.getSurname())))
                .andExpect(jsonPath("$.emailAddress", is(userDto.getEmailAddress())));
    }
}