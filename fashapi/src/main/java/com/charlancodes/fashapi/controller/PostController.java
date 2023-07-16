package com.charlancodes.fashapi.controller;

import com.charlancodes.fashapi.entity.BlogPost;
import com.charlancodes.fashapi.entity.BlogPostImage;
import com.charlancodes.fashapi.exception.CustomAppException;
import com.charlancodes.fashapi.model.PageCriterias.PostPage;
import com.charlancodes.fashapi.service.BlogPostServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/fash")
public class PostController {
    private final BlogPostServiceImpl blogPostService;
    private final HttpSession httpSession;

    public PostController(BlogPostServiceImpl blogPostService, HttpSession httpSession) {
        this.blogPostService = blogPostService;
        this.httpSession = httpSession;
    }

    @PostMapping("/add_product")
    public ResponseEntity<?> addProduct(@RequestBody BlogPost blogPost) {
        BlogPost newblogPost = blogPostService.postProduct(blogPost, (Long) httpSession.getAttribute("admin_id"));
        return new ResponseEntity<>(newblogPost, HttpStatus.OK);
    }
    @PostMapping("/add_image/{id}")
    public ResponseEntity<?> addProductImage(@RequestParam("file") MultipartFile file, @PathVariable("id") Long postId) throws IOException {
        BlogPostImage newblogPostImg = blogPostService.addProductImage(file,postId, (Long) httpSession.getAttribute("admin_id"));
        return new ResponseEntity<>("Product Image Uploaded Successfully", HttpStatus.CREATED);
    }

    @PutMapping("/update_product/{id}")
    public ResponseEntity<?> updateBlogPost(@RequestBody BlogPost blogPost, Long id, @PathVariable("id") String blogId) {
        BlogPost newblogPost = blogPostService.updateBlogPost(blogPost, (Long) httpSession.getAttribute("admin_id"), Long.parseLong(blogId));
        return new ResponseEntity<>(newblogPost, HttpStatus.OK);
    }

    @DeleteMapping("/delete_post/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") String postId) {
        blogPostService.deleteProduct(Long.parseLong(postId), (Long) httpSession.getAttribute("admin_id"));
        return new ResponseEntity<>("ITEM deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("/view_product/{id}")
    public ResponseEntity<?> viewProduct(@PathVariable("id") String id) {
        BlogPost newblogPost = blogPostService.viewProduct(Long.parseLong(id));
        return new ResponseEntity<>(newblogPost, HttpStatus.OK);
    }

    @GetMapping("/view_allproducts")
    public Page<BlogPost> viewAllProducts(@RequestBody PostPage postPage) {
        Page<BlogPost> newblogPost = blogPostService.viewAllProducts(postPage);
        return new ResponseEntity<>(newblogPost, HttpStatus.OK).getBody();
    }
}
