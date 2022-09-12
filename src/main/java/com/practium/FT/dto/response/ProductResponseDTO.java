package com.practium.FT.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ProductResponseDTO {
    Long id;
    @NotBlank(message = "Product Name is mandatory ")
    String productName;
    @NotBlank(message = "Product Price is mandatory ")
    String productPrice;
    @JsonFormat(pattern="yyyy-MM-dd")
    LocalDate productExpirationDate;
    @CreationTimestamp
    LocalDateTime createdAt;
    @UpdateTimestamp
    LocalDateTime updatedAt;

}
