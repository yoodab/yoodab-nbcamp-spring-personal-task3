package com.sparta.easyspring.follow.controller;

import com.sparta.easyspring.auth.security.UserDetailsImpl;
import com.sparta.easyspring.follow.dto.Top10ProfileDto;
import com.sparta.easyspring.follow.repository.FollowRepository;
import com.sparta.easyspring.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;
    private final FollowRepository followRepository;

    @PostMapping("/{followingId}")
    public ResponseEntity<String> addFollow(@PathVariable(name = "followingId") Long followingId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        followService.addFollow(followingId,userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body("팔로우 완료");
    }

    @DeleteMapping("/{followingId}")
    public ResponseEntity<String> deleteFollow(@PathVariable(name = "followingId") Long followingId,@AuthenticationPrincipal UserDetailsImpl userDetails){
        followService.deleteFollow(followingId,userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body("팔로우 취소 완료");
    }

    @GetMapping
    public ResponseEntity<List<Top10ProfileDto>> getFollows() {
        List<Top10ProfileDto> response = followService.findTop10Profile();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
