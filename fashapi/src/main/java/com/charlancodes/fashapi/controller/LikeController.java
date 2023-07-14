package com.charlancodes.fashapi.controller;

import com.charlancodes.fashapi.dtos.RequestDto.LikeCountDto;
import com.charlancodes.fashapi.dtos.RequestDto.LikeDto;
import com.charlancodes.fashapi.service.LikeServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/fash")
public class LikeController {
    private final LikeServiceImpl likeService;
    private final HttpSession httpSession;

    public LikeController(LikeServiceImpl likeService, HttpSession httpSession) {
        this.likeService = likeService;
        this.httpSession = httpSession;
    }

    @PostMapping("/addlike/{id}")
    public String likeBlogPost(@RequestBody LikeDto likeDto, @PathVariable("id") Long postId) {
        return likeService.addLike(likeDto, (Long) httpSession.getAttribute("user_id"), postId);
    }

    @GetMapping("/countlike/{id}")
    public String count(@RequestBody LikeDto likeDto, @PathVariable("id") Long postId) {
        return likeService.count(likeDto, postId);
    }
}
