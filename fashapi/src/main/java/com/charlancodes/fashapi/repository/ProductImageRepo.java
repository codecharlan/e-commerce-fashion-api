package com.charlancodes.fashapi.repository;

import com.charlancodes.fashapi.entity.BlogPostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepo extends JpaRepository<BlogPostImage, Long> {
}
