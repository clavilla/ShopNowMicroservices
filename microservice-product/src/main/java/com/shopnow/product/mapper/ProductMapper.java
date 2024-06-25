package com.shopnow.product.mapper;

import com.shopnow.product.model.dto.ProductRequestDto;
import com.shopnow.product.model.dto.ProductResponseDto;
import com.shopnow.product.model.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    private final ModelMapper modelMapper;

    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Product convertToEntity(ProductRequestDto productRequestDto) {
        return modelMapper.map(productRequestDto, Product.class);
    }

    public ProductResponseDto convertToDto(Product product) {
        return modelMapper.map(product, ProductResponseDto.class);
    }

    public List<ProductResponseDto> convertToListDto(List<Product> productList) {
        return productList.stream().map(this::convertToDto).toList();
    }

    public Page<ProductResponseDto> convertToPagetDto(Page<Product> productPage) {
        return productPage.map(this::convertToDto);
    }
}
