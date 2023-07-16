package com.charlancodes.fashapi.service;

import com.charlancodes.fashapi.entity.Admin;
import com.charlancodes.fashapi.entity.BlogPost;
import com.charlancodes.fashapi.entity.BlogPostImage;
import com.charlancodes.fashapi.exception.CustomAppException;
import com.charlancodes.fashapi.exception.ResourceAlreadyAvailableException;
import com.charlancodes.fashapi.exception.ResourceNotExistException;
import com.charlancodes.fashapi.model.PageCriterias.PostPage;
import com.charlancodes.fashapi.repository.AdminRepo;
import com.charlancodes.fashapi.repository.BlogPostRepo;
import com.charlancodes.fashapi.repository.ProductImageRepo;
import com.charlancodes.fashapi.utils.ImageUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Optional;

@Service

public class BlogPostServiceImpl implements BlogPostService{
    private final AdminRepo adminRepo;
    private final BlogPostRepo blogPostRepo;
    private final ProductImageRepo imageRepo;

    public BlogPostServiceImpl(AdminRepo adminRepo, BlogPostRepo blogPostRepo, ProductImageRepo imageRepo) {
        this.adminRepo = adminRepo;
        this.blogPostRepo = blogPostRepo;
        this.imageRepo = imageRepo;
    }

    @Override
    public BlogPost postProduct(BlogPost blogPost, Long id) {
        Admin adminchk = adminRepo.findById(id).
                orElseThrow(()-> new ResourceNotExistException("Admin does not exist"));
        BlogPost newBlogPost = new BlogPost();
        newBlogPost.setTitle(blogPost.getTitle());
        newBlogPost.setDescription(blogPost.getDescription());
        newBlogPost.setCategory(blogPost.getCategory());
        newBlogPost.setPrice(blogPost.getPrice());
        newBlogPost.setAdmin(adminchk);
        blogPostRepo.save(newBlogPost);
        return newBlogPost;
    }
    @Override
    public BlogPostImage addProductImage(MultipartFile file, Long blogId, Long id) throws IOException {
        Admin adminchk = adminRepo.findById(id).
                orElseThrow(()-> new ResourceNotExistException("Admin does not exist"));
        Optional<BlogPost> checkIfImageExist = Optional.ofNullable(blogPostRepo.findByAdminAndBlogPostImageIsNull(adminchk)
                .orElseThrow(() -> new ResourceAlreadyAvailableException("Image Already Available")));
        Optional<BlogPost> findBlogPost = blogPostRepo.findById(blogId);
        if(findBlogPost.isPresent()){

            imageRepo.save(BlogPostImage.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .blogPost(findBlogPost.get())
                    .image(ImageUtils.compressImage(file.getBytes())).build());
        }
        return new BlogPostImage("Product Image uploaded successfully: " +
                file.getOriginalFilename());
    }
    @Override
    public BlogPost updateBlogPost(BlogPost blogPost, Long id, Long blogId) {
        Admin adminchk = adminRepo.findById(id).
                orElseThrow(()-> new ResourceNotExistException("Admin does not exist"));
        Optional<BlogPost> findBlogPost = blogPostRepo.findById(blogId);
        BlogPost newBlogPost;
        if(findBlogPost.isPresent()){
            newBlogPost = findBlogPost.get();
            newBlogPost.setTitle(blogPost.getTitle());
            newBlogPost.setDescription(blogPost.getDescription());
            newBlogPost.setCategory(blogPost.getCategory());
            newBlogPost.setPrice(blogPost.getPrice());
            newBlogPost.setAdmin(adminchk);
            blogPostRepo.save(newBlogPost);
        }else throw new ResourceNotExistException("ITEM is no longer AVAILABLE");

        return newBlogPost;
    }
    @Override
    public void deleteProduct(Long postId, Long adminId) {
        adminRepo.findById(adminId).
                orElseThrow(()-> new ResourceNotExistException("You're not Authorised to make this request"));
        blogPostRepo.findById(postId)
                .orElseThrow(() -> new CustomAppException("product with: " + postId + " does not exist"));

        blogPostRepo.deleteById(postId);
    }

    @Override
    public BlogPost viewProduct(Long id) {
        Optional<BlogPost> post = blogPostRepo.findById(id);
        if (post.isPresent()){
            return post.get();
        }
        throw new CustomAppException("product with id " + id + " does not exist");
    }

    @Override
    public Page<BlogPost> viewAllProducts(PostPage postPage) {
        Sort sort = Sort.by(postPage.getSortDirection(),postPage.getSortBy());
        Pageable pageable = PageRequest.of(postPage.getPageNumber(), postPage.getPageSize(),sort);
        Page<BlogPost> post = blogPostRepo.findAll(pageable);
        return post;
    }

}
