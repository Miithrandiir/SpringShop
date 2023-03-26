package fr.ulco.springshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ulco.springshop.controllers.AuthenticationController;
import fr.ulco.springshop.controllers.Routes;
import fr.ulco.springshop.model.form.RegisterForm;
import fr.ulco.springshop.service.core.UserServiceInterface;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = StatutisApplication.class)
@AutoConfigureMockMvc
public class AuthentificationControllerTest {

    @Autowired
    private MockMvc mvc;

    @InjectMocks
    private AuthenticationController controller;

    @Autowired
    private UserServiceInterface userService;

    @Test
    public void registerUser() throws Exception {
        RegisterForm registerForm = new RegisterForm("test@localhost", "123", "123", "Firstname", "Lastname");

        final var request = MockMvcRequestBuilders.post(Routes.REGISTER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(registerForm));
        mvc.perform(request)
                .andExpect(status().isOk());

        Assert.assertNotNull(userService.findByEmail(registerForm.getEmail()));

        // Already exists
        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }
}
