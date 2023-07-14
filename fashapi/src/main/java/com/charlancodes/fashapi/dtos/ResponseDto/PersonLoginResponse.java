package com.charlancodes.fashapi.dtos.ResponseDto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PersonLoginResponse {
    private Long id;
    private String name;
    private String email;
    private String role;
}
