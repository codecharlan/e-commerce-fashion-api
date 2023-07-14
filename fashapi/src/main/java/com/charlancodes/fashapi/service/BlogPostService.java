package com.charlancodes.fashapi.service;

import com.charlancodes.fashapi.entity.BlogPost;
import com.charlancodes.fashapi.model.PageCriterias.PostPage;
import org.springframework.data.domain.Page;

public interface BlogPostService {
    BlogPost postProduct(BlogPost blogPost, Long id);

    BlogPost updateBlogPost(BlogPost blogPost, Long id, Long blogId);

    void deleteProduct(Long postId, Long adminId);

    BlogPost viewProduct(Long id);

    Page<BlogPost> viewAllProducts(PostPage postPage);
}
