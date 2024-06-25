package com.shopnow.product.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDto {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    @NotBlank(message = "La descripción no puede estar vacío")
    private String description;
    @NotBlank(message = "El precio no puede estar vacío")
    private double price;
    @NotBlank(message = "El status no puede estar vacío")
    private String status;
    @NotBlank(message = "La categoría no puede estar vacía")
    private String Category;

}
