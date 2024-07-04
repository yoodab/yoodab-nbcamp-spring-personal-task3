package com.sparta.easyspring.commentlike.repository;

import com.sparta.easyspring.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentLikeRepositoryCustom {
    Page<Comment> findLikedCommentsByUserIdWithPage(long userId, Pageable pageable);
}
