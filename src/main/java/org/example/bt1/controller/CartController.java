package org.example.bt1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bt1.dto.CartItemRequest;
import org.example.bt1.dto.UpdateQuantityRequest;
import org.example.bt1.entity.CartItem;
import org.example.bt1.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public CartItem addToCart(
            @Valid @RequestBody CartItemRequest request
    ) {

        log.info(
                "API addToCart được gọi với userId={}",
                request.getUserId()
        );

        return cartService.addToCart(request);

    }

    @GetMapping("/{userId}")
    public List<CartItem> getCart(
            @PathVariable String userId
    ) {

        log.info("API getCart được gọi với userId={}", userId);

        return cartService.getCartByUserId(userId);

    }

    @GetMapping("/all")
    public List<CartItem> getAll() {

        log.info("API getAll CartItem được gọi");

        return cartService.getAll();

    }

    @GetMapping("/item/{id}")
    public CartItem getById(@PathVariable Long id) {

        log.info("API getById CartItem được gọi id={}", id);

        return cartService.getById(id);

    }

    @PutMapping("/item/{id}")
    public CartItem updateQuantity(
            @PathVariable Long id,
            @Valid @RequestBody UpdateQuantityRequest request
    ) {

        log.info("API updateQuantity được gọi id={} quantity={}", id, request.getQuantity());

        return cartService.updateQuantity(id, request.getQuantity());

    }

    @DeleteMapping("/item/{id}")
    public void deleteById(@PathVariable Long id) {

        log.info("API deleteById được gọi id={}", id);

        cartService.deleteById(id);

    }

}