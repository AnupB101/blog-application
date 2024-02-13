package com.anup.blog.payload;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Valid

@Data
public class ResetPasswordDto {
    @NotBlank
    @Email(message = "Email address invalid")
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;
}
