package com.charlancodes.fashapi.repository;

import com.charlancodes.fashapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {

}
