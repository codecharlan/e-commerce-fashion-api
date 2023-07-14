package com.charlancodes.fashapi.dtos.RequestDto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class PersonLoginDto {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
