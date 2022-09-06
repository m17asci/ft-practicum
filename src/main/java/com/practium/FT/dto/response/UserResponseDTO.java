package com.practium.FT.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDTO {

    Long id;
    @NotBlank(message = "User Name is mandatory ")
    String userName;
    @NotBlank(message = "User Surname is mandatory ")
    String userSurname;
    @NotBlank(message = "User Email is mandatory ")
    String userEmail;
    @NotBlank(message = "User Phone Number is mandatory ")
    String userPhoneNumber;
    @CreationTimestamp
    LocalDateTime createdAt;
    @UpdateTimestamp
    LocalDateTime updatedAt;

}
