package com.charlancodes.fashapi.service;

import com.charlancodes.fashapi.dtos.RequestDto.CommentDto;
import com.charlancodes.fashapi.entity.Comment;
import com.charlancodes.fashapi.model.PageCriterias.CommentPage;
import org.springframework.data.domain.Page;

public interface CommentService {

    Comment addComment(CommentDto commentDto, Long UserId);

    Comment editComment(CommentDto commentDto, Long id, Long UserId);

    void deleteComment(Long id, Long UserId);

    Page<Comment> viewAllComments(CommentPage commentPage, Long UserId);
}
