package fr.ulco.springshop;

import fr.ulco.springshop.controllers.OrderController;
import fr.ulco.springshop.controllers.Routes;
import fr.ulco.springshop.helper.AuthenticationHelper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    private MockMvc mvc;


    @InjectMocks
    private OrderController controller;

    @Autowired
    private AuthenticationHelper authenticationHelper;

    @Test
    public void testGetOrders() throws Exception {
        //Without authentication
        mvc.perform(MockMvcRequestBuilders.get(Routes.GET_ORDERS))
                .andExpect(status().isUnauthorized());

        //With Authentification
        mvc.perform(MockMvcRequestBuilders.get(Routes.GET_ORDERS)
                        .with(authenticationHelper.jwtUser()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].user", equalTo(AuthenticationHelper.defaultUsername)))
                .andExpect(jsonPath("$[0].items", hasSize(1)))
                .andExpect(jsonPath("$[0].items[0].product", endsWith("/1")))
                .andExpect(jsonPath("$[0].items[0].quantity", equalTo(10)))
        ;
    }
}
