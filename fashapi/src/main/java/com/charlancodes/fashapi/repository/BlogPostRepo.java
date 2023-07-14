package com.charlancodes.fashapi.repository;

import com.charlancodes.fashapi.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepo extends JpaRepository<BlogPost, Long> {

}
