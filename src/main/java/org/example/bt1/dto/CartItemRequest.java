package org.example.bt1.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartItemRequest {

    @NotBlank(message = "UserId không được để trống")
    private String userId;

    @NotBlank(message = "ProductId không được để trống")
    private String productId;

    @NotNull(message = "Quantity không được null")
    @Min(value = 1, message = "Quantity phải lớn hơn 0")
    private Integer quantity;

}