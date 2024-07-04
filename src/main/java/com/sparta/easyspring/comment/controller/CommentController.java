package com.sparta.easyspring.comment.controller;

import com.sparta.easyspring.auth.security.UserDetailsImpl;
import com.sparta.easyspring.comment.dto.CommentRequestDto;
import com.sparta.easyspring.comment.dto.CommentResponseDto;
import com.sparta.easyspring.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/liked")
    public ResponseEntity<List<CommentResponseDto>> getLikedComment(@RequestParam(value = "page",defaultValue = "1") int page,
                                                              @RequestParam(value = "sortBy",defaultValue = "createdAt") String sortBy,
                                                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.getLikedComments(page-1,sortBy,userDetails.getUser()));
    }

    @PostMapping("/{postId}")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable("postId") Long postId, @RequestBody CommentRequestDto commentRequestDto) {
        return ResponseEntity.ok().body(commentService.createNewComment(postId, commentRequestDto));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentResponseDto>> getComment(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok().body(commentService.getAllComments(postId));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        return ResponseEntity.ok().body(commentService.updateExistingComment(commentId, commentRequestDto));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        commentService.deleteExistingComment(commentId);
        return ResponseEntity.ok().body("댓글 삭제에 성공했습니다.");
    }

}
