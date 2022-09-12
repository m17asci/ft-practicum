package com.practium.FT.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDTO {

    @NotBlank(message = "Product Name is mandatory ")
    String productName;
    @NotBlank(message = "Product Price is mandatory ")
    String productPrice;
    @JsonFormat(pattern="yyyy-MM-dd")
    LocalDate productExpirationDate;
}
