package com.charlancodes.fashapi.dtos.ResponseDto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PersonResponseDto {
    private String name;
    private String email;
}
