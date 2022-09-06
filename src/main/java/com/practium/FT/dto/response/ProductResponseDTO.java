package com.practium.FT.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ProductResponseDTO {
    Long id;
    @NotBlank(message = "Product Name is mandatory ")
    String productName;
    @NotBlank(message = "Product Price is mandatory ")
    String productPrice;
    LocalDate productExpirationDate;
    @CreationTimestamp
    LocalDateTime createdAt;
    @UpdateTimestamp
    LocalDateTime updatedAt;

}
