package com.charlancodes.fashapi.controller;

import com.charlancodes.fashapi.dtos.RequestDto.CommentDto;
import com.charlancodes.fashapi.entity.Comment;
import com.charlancodes.fashapi.model.PageCriterias.CommentPage;
import com.charlancodes.fashapi.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("api/fash")
public class CommentController {
    private final CommentService commentService;
    private final HttpSession httpSession;

    public CommentController(CommentService commentService, HttpSession httpSession) {
        this.commentService = commentService;
        this.httpSession = httpSession;
    }

    @PostMapping("/create_comment")
    public ResponseEntity<?> addComment(@RequestBody CommentDto commentDto) {
        Comment comment = commentService.addComment(commentDto, (Long) httpSession.getAttribute("user_id"));
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PutMapping("edit_comment/{id}")
    public ResponseEntity<?> editComment(@RequestBody CommentDto commentDto, @PathVariable String id) {
        Comment comment = commentService.editComment(commentDto, Long.parseLong(id), (Long) httpSession.getAttribute("user_id"));
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @DeleteMapping("delete_comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable String id) {
        commentService.deleteComment(Long.parseLong(id), (Long) httpSession.getAttribute("user_id"));
        return new ResponseEntity<>("Comment Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("view_allcomments")
    public ResponseEntity<?> viewAllComments(@RequestBody CommentPage commentPage) {
        Page<Comment> comment = commentService.viewAllComments(commentPage, (Long) httpSession.getAttribute("user_id"));
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
}
