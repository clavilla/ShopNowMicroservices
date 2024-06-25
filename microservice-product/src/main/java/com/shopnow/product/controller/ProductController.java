package com.shopnow.product.controller;

import com.shopnow.product.model.dto.ProductRequestDto;
import com.shopnow.product.model.dto.ProductResponseDto;
import com.shopnow.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<Page<ProductResponseDto>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<ProductResponseDto> products = productService.findAll(PageRequest.of(page, size));
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{UUID}")
    public ResponseEntity<ProductResponseDto> getProductByUUID(@PathVariable String uuid) {
        ProductResponseDto product = productService.findByUUID(UUID.fromString(uuid));
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<ProductResponseDto>> getProductByNameContaining(@RequestParam String name) {
        List<ProductResponseDto> products = productService.findByNameContainingIgnoreCase(name.toLowerCase());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/createProduct")
    public ResponseEntity<ProductResponseDto> createProduct(@Validated @RequestBody ProductRequestDto productDto) {
        ProductResponseDto newProduct = productService.create(productDto);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{UUID}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable String uuid, @Validated @RequestBody ProductRequestDto productDto) {
        ProductResponseDto updatedProduct = productService.update(UUID.fromString(uuid), productDto);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{UUID}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID uuid) {
        productService.deleteByCode(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
