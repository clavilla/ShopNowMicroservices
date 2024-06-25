package com.shopnow.product.model.dto;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {

    private UUID code;
    private String name;
    private String description;
    private double price;
    private String status;
    private String category;

}
