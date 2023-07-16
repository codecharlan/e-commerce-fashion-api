package com.charlancodes.fashapi.service;

import com.charlancodes.fashapi.entity.BlogPost;
import com.charlancodes.fashapi.entity.BlogPostImage;
import com.charlancodes.fashapi.model.PageCriterias.PostPage;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BlogPostService {
    BlogPost postProduct(BlogPost blogPost, Long id);
    BlogPostImage addProductImage(MultipartFile file, Long blogId, Long id) throws IOException;

    BlogPost updateBlogPost(BlogPost blogPost, Long id, Long blogId);

    void deleteProduct(Long postId, Long adminId);

    BlogPost viewProduct(Long id);

    Page<BlogPost> viewAllProducts(PostPage postPage);
}
