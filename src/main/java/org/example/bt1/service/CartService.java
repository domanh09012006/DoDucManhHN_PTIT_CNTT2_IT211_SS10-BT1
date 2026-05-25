package org.example.bt1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bt1.dto.CartItemRequest;
import org.example.bt1.entity.CartItem;
import org.example.bt1.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final CartRepository cartRepository;

    public CartItem addToCart(CartItemRequest request) {

        log.info(
                "Nhận request thêm giỏ hàng userId={}, productId={}, quantity={}",
                request.getUserId(),
                request.getProductId(),
                request.getQuantity()
        );

        if (request.getProductId().trim().isEmpty()) {

            log.warn(
                    "ProductId rỗng từ user={}",
                    request.getUserId()
            );

            throw new RuntimeException("ProductId không hợp lệ");

        }

        if (request.getQuantity() <= 0) {

            log.warn(
                    "Quantity không hợp lệ: {}",
                    request.getQuantity()
            );

            throw new RuntimeException("Quantity phải lớn hơn 0");

        }

        CartItem existingItem =
                cartRepository.findByUserIdAndProductId(
                        request.getUserId(),
                        request.getProductId()
                ).orElse(null);

        if (existingItem != null) {

            existingItem.setQuantity(
                    existingItem.getQuantity() + request.getQuantity()
            );

            log.info(
                    "Cộng dồn sản phẩm productId={} cho user={}",
                    request.getProductId(),
                    request.getUserId()
            );

            return existingItem;

        }

        CartItem newItem = new CartItem(
                null,
                request.getUserId(),
                request.getProductId(),
                request.getQuantity()
        );

        cartRepository.save(newItem);

        log.info(
                "Tạo mới sản phẩm productId={} cho user={}",
                request.getProductId(),
                request.getUserId()
        );

        return newItem;

    }

    public List<CartItem> getCartByUserId(String userId) {

        log.info("Lấy giỏ hàng của user={}", userId);

        return cartRepository.findByUserId(userId);

    }

}