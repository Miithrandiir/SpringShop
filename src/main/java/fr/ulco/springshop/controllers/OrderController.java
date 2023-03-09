package fr.ulco.springshop.controllers;

import fr.ulco.springshop.model.bo.UserBO;
import fr.ulco.springshop.model.dto.OrderDTO;
import fr.ulco.springshop.service.OrderService;
import fr.ulco.springshop.service.UserService;
import fr.ulco.springshop.service.core.OrderServiceInterface;
import fr.ulco.springshop.service.core.UserServiceInterface;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Tag(name = "Orders", description = "The orders API")
@SecurityRequirement(name = "JWT")
public class OrderController {

    private final UserServiceInterface userService;
    private final OrderServiceInterface orderService;

    @GetMapping(Routes.GET_ORDERS)
    public ResponseEntity<List<OrderDTO>> getOrders(Authentication authentication) {

        Optional<UserBO> user = userService.findByEmail(authentication.getName());

        return user.map(userBO -> ResponseEntity.ok(

                orderService.findByUser(userBO.getId())
                        .stream().map(OrderDTO::new).toList()

        )).orElseGet(() -> ResponseEntity.status(401).build());

    }

}
