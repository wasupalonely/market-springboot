package com.springcourse.market.web.controller;

import com.springcourse.market.domain.Product;
import com.springcourse.market.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    @Operation(summary = "Get products", description = "Get all supermarket products")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get products by id", description = "Search a product with an id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<Product> getProduct(@Parameter(description = "The id of the product", required = true, example = "1")
                                                  @PathVariable("id") Long productId) {
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/category/{id}")
    @Operation(summary = "Get products by its category id", description = "Search a product with its category id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Products not found")
    })
    public ResponseEntity<List<Product>> getByCategory(@Parameter(description = "The id of the category", required = true, example = "1")
                                                           @PathVariable("id") Long categoryId) {
        return productService.getByCategory(categoryId)
                .filter(Predicate.not(List::isEmpty))
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    @Operation(summary = "Create product", description = "Create an specified product")
    @ApiResponse(responseCode = "201", description = "CREATED")
    public ResponseEntity<Product> save(@Parameter(description = "The product", required = true) @RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product by its id", description = "Search a product with its id and deletes it")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Products not found")
    })
    public ResponseEntity delete(@Parameter(description = "The id of the product", required = true, example = "1")
                                     @PathVariable("id") Long productId) {
        return productService.delete(productId) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
