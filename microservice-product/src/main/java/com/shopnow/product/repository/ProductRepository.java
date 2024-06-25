package com.shopnow.product.repository;

import com.shopnow.product.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends MongoRepository<Product, String>{

    @NonNull
    Page<Product> findAll(@NonNull Pageable pageable);

    @NonNull
    Optional<Product> findByCode(@NonNull UUID code);

    List<Product> findByNameContainingIgnoreCase(String name);

    void deleteByCode(@NonNull UUID code);
}
