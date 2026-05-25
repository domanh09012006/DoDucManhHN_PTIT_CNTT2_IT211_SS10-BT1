package org.example.bt1.repository;

import org.example.bt1.entity.CartItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CartRepository {
    private final List<CartItem> cartItems = new ArrayList<>();
    private Long currentId = 1L;
    public List<CartItem> findByUserId(String userId) {
        return cartItems.stream()
                .filter(item -> item.getUserId().equals(userId))
                .toList();

    }
    public Optional<CartItem> findByUserIdAndProductId(
            String userId,
            String productId
    ) {
        return cartItems.stream()
                .filter(item ->
                        item.getUserId().equals(userId)
                                && item.getProductId().equals(productId))
                .findFirst();
    }
    public CartItem save(CartItem cartItem) {
        if (cartItem.getId() == null) {
            cartItem.setId(currentId++);
            cartItems.add(cartItem);
        }
        return cartItem;

    }

}