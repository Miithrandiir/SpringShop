package fr.ulco.springshop;

import fr.ulco.springshop.controllers.CategoryController;
import fr.ulco.springshop.controllers.Routes;
import fr.ulco.springshop.helper.AuthenticationHelper;
import fr.ulco.springshop.model.conveter.CategoryConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = StatutisApplication.class)
@AutoConfigureMockMvc
public class CategoryControllerTest {
    @Autowired
    private MockMvc mvc;

    @InjectMocks
    private CategoryController controller;

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private AuthenticationHelper authenticationHelper;

    @Test
    public void testGetCategories() throws Exception {

        final var request = MockMvcRequestBuilders.get(Routes.GET_CATEGORIES);
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));

    }

    @Test
    public void testPostCategoriesWithoutAccess() throws Exception {
        final var request = MockMvcRequestBuilders.post(Routes.POST_CATEGORIES);
        mvc.perform(request)
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testPostCategoriesWithAccess() throws Exception {

        String token = authenticationHelper.generateToken("john@doe.tld");

        mvc.perform(MockMvcRequestBuilders.post(Routes.POST_CATEGORIES)
                .header("Authorization", "Bearer " + token)
                .contentType("multipart/form-data")
                .param("name","test"))
                .andExpect(status().isOk());
    }

}
