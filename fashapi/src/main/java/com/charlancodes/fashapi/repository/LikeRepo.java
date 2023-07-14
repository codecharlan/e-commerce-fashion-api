package com.charlancodes.fashapi.repository;

import com.charlancodes.fashapi.entity.BlogPost;
import com.charlancodes.fashapi.entity.Customer;
import com.charlancodes.fashapi.entity.Like;
import com.charlancodes.fashapi.enums.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepo extends JpaRepository<Like, Long> {
    Like findLikeByCustomerAndBlogPostAndReaction(Customer customer, BlogPost blogPost, Reaction reaction);

    Like findLikeByCustomerAndBlogPost(Customer customer,BlogPost blogPost);

    List<Like> findLikeByReactionAndBlogPostId(Reaction reaction, Long id);
}
