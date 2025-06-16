package com.example.planify.dto;

import com.example.planify.constans.Gender;
import com.example.planify.validators.PasswordCheck;
import jakarta.validation.constraints.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class UserDTO {

    private Long id;
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @Size(min = 3, max = 20)
    @PasswordCheck
    private String password;

    @Positive
    @Min(18)
    private Integer age;

    private Gender gender;

    private String role;
}
