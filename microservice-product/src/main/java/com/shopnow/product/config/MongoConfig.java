package com.shopnow.product.config;

import com.shopnow.product.model.entity.Product;
import com.shopnow.product.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Configuration
public class MongoConfig {

    private ProductRepository productRepository;

    public MongoConfig(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Bean
    CommandLineRunner initDatabase(MongoTemplate mongoTemplate) {
        return args -> {

            if (!mongoTemplate.collectionExists("productDb")) {
                mongoTemplate.createCollection("productDb");
            }

            if (productRepository.count() == 0) {
                /**
                 * Create PRODUCTS
                 */
                Product product1 = Product.builder()
                        .code(UUID.randomUUID())
                        .name("Laptop")
                        .description("IdeaPad Gaming 3i 15")
                        .price(300.0)
                        .status("ACTIVE")
                        .category("ELECTRONICS")
                        .createdAt(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        .modifiedAt(null)
                        .build();

                productRepository.save(product1);
            }

        };
    }
}