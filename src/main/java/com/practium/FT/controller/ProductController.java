package com.practium.FT.controller;

import com.practium.FT.dto.request.ProductRequestDTO;
import com.practium.FT.dto.response.ProductResponseDTO;
import com.practium.FT.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController  {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO>  findProductByProductId(@PathVariable Long productId){
        return ResponseEntity.ok(productService.findProduct(productId));
    }
    @GetMapping("/date")
    public ResponseEntity<List<ProductResponseDTO>> getAllProductsWithDate() {
        return ResponseEntity.ok(productService.getAllProductsWithDate());
    }

    @GetMapping("/expdate")
    public ResponseEntity<List<ProductResponseDTO>> getAllProductsWithDateExp() {
        return ResponseEntity.ok(productService.getAllProductsWithDateExp());
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createOneProduct(@RequestBody ProductRequestDTO productRequestDto) {
        return ResponseEntity.ok(productService.createOneProduct(productRequestDto));
    }
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO>updateOneProduct(@RequestBody ProductRequestDTO newProduct){
        return ResponseEntity.ok(productService.updateOneProduct(newProduct));
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void>deleteOneProduct(@PathVariable Long productId){
        productService.deleteById(productId);
        return ResponseEntity.notFound().build();

    }
}









