package com.practium.FT.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends BaseEntity {



    @Column(name = "isim",length = 50)
    String userName;
    @Column(name = "soyisim",length = 50)
    String userSurname;
    @Column(name = "mail",length = 50)
    String userEmail;
    @Column(name = "telefon_no",length = 15)
    String userPhoneNumber;



}
