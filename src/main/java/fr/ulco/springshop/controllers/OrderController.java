package fr.ulco.springshop.controllers;

import fr.ulco.springshop.model.bo.OrderBO;
import fr.ulco.springshop.model.bo.OrderItemBO;
import fr.ulco.springshop.model.bo.UserBO;
import fr.ulco.springshop.model.dto.OrderDTO;
import fr.ulco.springshop.model.form.OrderForm;
import fr.ulco.springshop.service.core.OrderServiceInterface;
import fr.ulco.springshop.service.core.ProductServiceInterface;
import fr.ulco.springshop.service.core.UserServiceInterface;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Tag(name = "Orders", description = "The orders API")
@SecurityRequirement(name = "JWT")
public class OrderController {

    private final UserServiceInterface userService;
    private final OrderServiceInterface orderService;

    private final ProductServiceInterface productService;

    @GetMapping(Routes.GET_ORDERS)
    public ResponseEntity<List<OrderDTO>> getOrders(Authentication authentication) {

        Optional<UserBO> user = userService.findByEmail(authentication.getName());

        return user.map(userBO -> ResponseEntity.ok(

                orderService.findByUser(userBO.getId())
                        .stream().map(OrderDTO::new).toList()

        )).orElseGet(() -> ResponseEntity.status(401).build());

    }

    @PostMapping(value = Routes.POST_ORDER)
    public ResponseEntity<OrderDTO> postOrder(@RequestBody() OrderForm orderForm, Authentication authentication) {
        OrderBO orderBO = new OrderBO();
        orderBO.setCreatedAt(LocalDateTime.now());
        orderBO.setUser(userService.findByEmail(authentication.getName()).get());
        orderBO.setItems(new ArrayList<>());
        orderForm.getItems().forEach(item -> {

            String product = item.getProduct().replace(Routes.GET_PRODUCT_BY_ID.replace("{id}", ""), "");
            int productId = Integer.parseInt(product);

            OrderItemBO orderItemBO = new OrderItemBO();
            orderItemBO.setProduct(productService.findById(productId).get());
            orderItemBO.setQuantity(item.getQuantity());
            orderBO.getItems().add(orderItemBO);
        });

        return ResponseEntity.ok(new OrderDTO(orderService.save(orderBO)));
    }


}
