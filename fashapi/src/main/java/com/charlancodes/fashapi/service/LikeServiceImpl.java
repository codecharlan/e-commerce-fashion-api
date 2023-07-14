package com.charlancodes.fashapi.service;

import com.charlancodes.fashapi.dtos.RequestDto.LikeDto;
import com.charlancodes.fashapi.entity.BlogPost;
import com.charlancodes.fashapi.entity.Customer;
import com.charlancodes.fashapi.entity.Like;
import com.charlancodes.fashapi.exception.CustomAppException;
import com.charlancodes.fashapi.exception.ResourceNotExistException;
import com.charlancodes.fashapi.repository.BlogPostRepo;
import com.charlancodes.fashapi.repository.CustomerRepo;
import com.charlancodes.fashapi.repository.LikeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService{
    private final LikeRepo likeRepo;
    private final BlogPostRepo blogPostRepo;
    private final CustomerRepo customerRepo;

    public LikeServiceImpl(LikeRepo likeRepo, BlogPostRepo blogPostRepo, CustomerRepo customerRepo) {
        this.likeRepo = likeRepo;
        this.blogPostRepo = blogPostRepo;
        this.customerRepo = customerRepo;
    }
    @Override
    public String addLike(LikeDto likeDto, Long UserId, Long postId){
        Customer customerchk = customerRepo.findById(UserId).
                orElseThrow(()-> new ResourceNotExistException("You cannot perform this action"));
        BlogPost blogPost = blogPostRepo.findById(postId)
                .orElseThrow(() -> new CustomAppException("Product does not exist"));
        Like like = likeRepo.findLikeByCustomerAndBlogPostAndReaction(customerchk, blogPost,likeDto.getReaction());

        Like like1 = likeRepo.findLikeByCustomerAndBlogPost(customerchk, blogPost);

        if(like != null){
            return "You have already " + likeDto.getReaction() + " this product";
        }
        if(like1 != null){
            like1.setReaction(likeDto.getReaction());
            likeRepo.save(like1);
            return "You have successfully " +likeDto.getReaction()+ " this post";
        }

        Like like2 = new Like();
        like2.setReaction(likeDto.getReaction());
        like2.setCustomer(customerchk);
        like2.setBlogPost(blogPost);
        likeRepo.save(like2);
        return "You " +likeDto.getReaction()+ " this product";
    }
    @Override
    public String count(LikeDto likeDto, Long postId) {
        List<Like> likeCount = likeRepo.findLikeByReactionAndBlogPostId
                (likeDto.getReaction(), postId);

        return "Total " +likeDto.getReaction()+ " count is " + likeCount.size();
    }
}
