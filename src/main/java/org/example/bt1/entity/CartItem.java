package org.example.bt1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    private Long id;

    private String userId;

    private String productId;

    private Integer quantity;

}