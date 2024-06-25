package com.shopnow.product.service;

import com.shopnow.product.exception.ResourceNotFoundException;
import com.shopnow.product.mapper.ProductMapper;
import com.shopnow.product.model.dto.ProductRequestDto;
import com.shopnow.product.model.dto.ProductResponseDto;
import com.shopnow.product.model.entity.Product;
import com.shopnow.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @NonNull
    @Transactional(readOnly = true)
    public Page<ProductResponseDto> findAll(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        return productMapper.convertToPagetDto(productPage);
    }

    @NonNull
    @Transactional(readOnly = true)
    public ProductResponseDto findByUUID(@NonNull UUID code) {
        Product product = productRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with code: " + code));
        return productMapper.convertToDto(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> findByNameContainingIgnoreCase(String name) {
        List<Product> productList = productRepository.findByNameContainingIgnoreCase(name);
        return productMapper.convertToListDto(productList);
    }

    @Transactional
    public ProductResponseDto create(ProductRequestDto productRequestDto) {
        Product product = productMapper.convertToEntity(productRequestDto);
        product.setCreatedAt(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        product = productRepository.save(product);
        return productMapper.convertToDto(product);
    }

    @Transactional
    public ProductResponseDto update(UUID code, ProductRequestDto productRequestDto) {
        Product product = productRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with code: " + code));
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setStatus(productRequestDto.getStatus());
        product.setCategory(productRequestDto.getCategory());
        product.setModifiedAt(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return productMapper.convertToDto(productRepository.save(product));

    }

    @Transactional
    public void deleteByCode(@NonNull UUID code) {
        productRepository.deleteByCode(code);
    }





}
