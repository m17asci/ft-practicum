package com.practium.FT.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.NotBlank;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDTO {

    @NotBlank(message = "User Name is mandatory ")
    String userName;
    @NotBlank(message = "User Surname is mandatory ")
    String userSurname;
    @NotBlank(message = "User Email is mandatory ")
    String userEmail;
    @NotBlank(message = "User Phone Number is mandatory ")
    String userPhoneNumber;
}

