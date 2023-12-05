package com.emlakjet.demo.security.request;

import com.emlakjet.demo.common.consts.RegexConstant;
import com.emlakjet.demo.common.util.ToLowerCaseConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "Email cannot be blank")
    @Pattern(regexp = RegexConstant.EMAIL, message = "Email format incorrect")
    @JsonDeserialize(converter = ToLowerCaseConverter.class)
    private String email;

    @NotNull
    @Size(min = 8, max = 32 , message = "Password length should be in between 8-32")
    @Pattern(regexp = RegexConstant.PASSWORD, message = "Password must contains at least a uppercase, at least a lowercase and at least a number characters")
    private String password;
}
