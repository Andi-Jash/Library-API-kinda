package com.example.Library;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private List<Product> products = new ArrayList<>();
    private Long idCounter = 1L;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        if (product == null) {
            return new ResponseEntity<>("No Product provided", HttpStatus.BAD_REQUEST);
        }
        product.setId(idCounter++);
        products.add(product);
        return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Optional<Product> productOptional = products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        Iterator<Product> iterator = products.iterator();

        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getId().equals(id)) {
                iterator.remove();
                return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }
}
