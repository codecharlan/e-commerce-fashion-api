package com.charlancodes.fashapi.service;

import com.charlancodes.fashapi.dtos.RequestDto.CommentDto;
import com.charlancodes.fashapi.entity.BlogPost;
import com.charlancodes.fashapi.entity.Comment;
import com.charlancodes.fashapi.entity.Customer;
import com.charlancodes.fashapi.exception.ResourceAlreadyAvailableException;
import com.charlancodes.fashapi.exception.ResourceNotExistException;
import com.charlancodes.fashapi.model.PageCriterias.CommentPage;
import com.charlancodes.fashapi.repository.BlogPostRepo;
import com.charlancodes.fashapi.repository.CommentRepo;
import com.charlancodes.fashapi.repository.CustomerRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CommentServiceImpl implements CommentService {
    private final CustomerRepo customerRepo;
    private final BlogPostRepo postRepo;
    private final CommentRepo commentRepo;

    public CommentServiceImpl(CustomerRepo customerRepo, BlogPostRepo postRepo, CommentRepo commentRepo) {
        this.customerRepo = customerRepo;
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }


    @Override
    public Comment addComment(CommentDto commentDto, Long UserId) {
        Customer newCustomer = customerRepo.findById(UserId)
                .orElseThrow(() -> new ResourceNotExistException("User with id:" + UserId + " was not found"));
        BlogPost post = postRepo.findById(commentDto.getPostId())
                .orElseThrow(() -> new ResourceNotExistException("Post with id:" + commentDto.getPostId() + " was not found"));
        Comment newComment = new Comment();
        newComment.setTitle(commentDto.getTitle());
        newComment.setDescription(commentDto.getDescription());
        newComment.setBlogPost(post);
        newComment.setCustomer(newCustomer);
        return commentRepo.save(newComment);
    }

    @Override
    public Comment editComment(CommentDto commentDto, Long id, Long UserId) {
        Customer newCustomer = customerRepo.findById(UserId)
                .orElseThrow(() -> new ResourceNotExistException("User with id:" + UserId + " was not found"));
        BlogPost post = postRepo.findById(commentDto.getPostId())
                .orElseThrow(() -> new ResourceNotExistException("Post with id:" + commentDto.getPostId() + " was not found"));
        Optional<Comment> editComment = commentRepo.findById(id);
        Comment newComment = new Comment();
        if(editComment.isPresent()){
            newComment = editComment.get();
            newComment.setTitle(commentDto.getTitle());
            newComment.setDescription(commentDto.getDescription());
        }else throw new ResourceAlreadyAvailableException("Comment with id: " + id + " does not exist");

        return commentRepo.save(newComment);
    }

    @Override
    public void deleteComment(Long id, Long UserId) {
        Customer newCustomer = customerRepo.findById(UserId)
                .orElseThrow(() -> new ResourceNotExistException("User with id:" + UserId + " was not found"));
        commentRepo.findById(id)
                .orElseThrow(() -> new ResourceAlreadyAvailableException("Comment with id: " +id+ " does not exist"));

        commentRepo.deleteById(id);

    }

    @Override
    public Page<Comment> viewAllComments(CommentPage commentPage, Long UserId) {
        Customer newCustomer = customerRepo.findById(UserId)
                .orElseThrow(() -> new ResourceNotExistException("User with id:" + UserId + " was not found"));
        Sort sort = Sort.by(commentPage.getSortDirection(),commentPage.getSortBy());
        Pageable pageable = PageRequest.of(commentPage.getPageNumber(), commentPage.getPageSize(),sort);
        Page<Comment> comment = commentRepo.findAll(pageable);
        return comment;
    }
}
