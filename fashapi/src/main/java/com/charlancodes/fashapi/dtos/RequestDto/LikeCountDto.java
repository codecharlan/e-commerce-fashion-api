package com.charlancodes.fashapi.dtos.RequestDto;

import com.charlancodes.fashapi.enums.Reaction;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeCountDto {
    private Reaction reaction;
}
