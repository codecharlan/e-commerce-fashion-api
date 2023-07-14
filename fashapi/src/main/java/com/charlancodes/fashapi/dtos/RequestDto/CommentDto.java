package com.charlancodes.fashapi.dtos.RequestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private Long postId;
    private String title;
    private String description;
}
