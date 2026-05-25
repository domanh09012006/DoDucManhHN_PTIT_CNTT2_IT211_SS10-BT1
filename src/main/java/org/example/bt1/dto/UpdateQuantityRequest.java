package org.example.bt1.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateQuantityRequest {

    @NotNull(message = "Quantity không được null")
    @Min(value = 1, message = "Quantity phải lớn hơn 0")
    private Integer quantity;

}

