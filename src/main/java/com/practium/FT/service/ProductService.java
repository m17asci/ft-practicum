package com.practium.FT.service;

import com.practium.FT.dto.request.ProductRequestDTO;
import com.practium.FT.dto.response.ProductResponseDTO;
import com.practium.FT.entity.Product;
import com.practium.FT.exception.ProductNotFoundException;
import com.practium.FT.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;


    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductResponseDTO.class))
                .collect(Collectors.toList());

    }


    public List<ProductResponseDTO>getAllProductsWithDate() {
        productRepository.findAll();

        List<Product> products = productRepository.findByProductExpirationDateAfterOrProductExpirationDateIsNull(LocalDate.of(2022, 9, 8));
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();

        for (Product product : products) {
            productResponseDTOS.add(modelMapper.map(product, ProductResponseDTO.class));
        }

        return productResponseDTOS;
    }

    public List<ProductResponseDTO>getAllProductsWithDateExp(){
        productRepository.findAll();
        List<Product> products = productRepository.findByProductExpirationDateBefore(LocalDate.of(2022,9,8));
        List<ProductResponseDTO>productResponseDTOS=new ArrayList<>();

        for (Product product : products){
            productResponseDTOS.add(modelMapper.map(product,ProductResponseDTO.class));
        }
        return productResponseDTOS;
    }


    public ProductResponseDTO createOneProduct(ProductRequestDTO productRequestDTO) {
        return modelMapper
                .map(productRepository.save(modelMapper.map(productRequestDTO, Product.class)), ProductResponseDTO.class);
    }

    public ProductResponseDTO findProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Yorum bulunamadı."));
        return modelMapper.map(product, ProductResponseDTO.class);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();

    }

    public ProductResponseDTO updateOneProduct(ProductRequestDTO newProductRequest) {
        Product oldProduct = productRepository.findById(newProductRequest.getId()).orElseThrow(() -> new ProductNotFoundException("Ürün Bulunamadı"));
        productRepository.findById(newProductRequest.getId())
                .ifPresent(product -> {
                    product.setProductName(newProductRequest.getProductName());
                    product.setProductPrice(newProductRequest.getProductPrice());
                    product.setProductPrice(newProductRequest.getProductPrice());
                    product.setProductExpirationDate(newProductRequest.getProductExpirationDate());
                    productRepository.save(product);
                });
        return modelMapper.map(productRepository.findById(oldProduct.getId()).get(), ProductResponseDTO.class);
    }

    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }
}