package fr.ulco.springshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ulco.springshop.controllers.CategoryController;
import fr.ulco.springshop.controllers.Routes;
import fr.ulco.springshop.model.bo.CategoryBO;
import fr.ulco.springshop.model.conveter.CategoryConverter;
import fr.ulco.springshop.model.dao.CategoryRepository;
import fr.ulco.springshop.model.dto.CategoryDTO;
import fr.ulco.springshop.model.entities.CategoryEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Test
    public void testGetCategories() throws Exception {

        final var request = MockMvcRequestBuilders.get(Routes.GET_CATEGORIES);
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));

    }

}
