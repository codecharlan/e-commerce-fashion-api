package com.charlancodes.fashapi.service;

import com.charlancodes.fashapi.dtos.RequestDto.LikeCountDto;
import com.charlancodes.fashapi.dtos.RequestDto.LikeDto;

public interface LikeService {
    String addLike(LikeDto likeDto, Long UserId, Long postId);

    String count(LikeDto likeDto, Long postId);
}
