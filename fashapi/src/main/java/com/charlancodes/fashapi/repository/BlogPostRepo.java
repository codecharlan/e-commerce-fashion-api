package com.charlancodes.fashapi.repository;

import com.charlancodes.fashapi.entity.Admin;
import com.charlancodes.fashapi.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogPostRepo extends JpaRepository<BlogPost, Long> {
    Optional<BlogPost> findByAdminAndBlogPostImageIsNull(Admin admin_id);
}
