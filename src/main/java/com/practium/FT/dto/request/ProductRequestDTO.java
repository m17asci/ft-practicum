package com.practium.FT.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDTO {

    @NotBlank(message = "Product Name is mandatory ")
    String productName;
    @NotBlank(message = "Product Price is mandatory ")
    String productPrice;
    @JsonFormat(pattern="yyyy-MM-dd")
    LocalDate productExpirationDate;
}
