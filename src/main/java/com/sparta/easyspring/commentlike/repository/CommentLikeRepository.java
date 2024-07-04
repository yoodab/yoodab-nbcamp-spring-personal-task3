
package com.sparta.easyspring.commentlike.repository;


import com.sparta.easyspring.auth.entity.User;
import com.sparta.easyspring.comment.entity.Comment;
import com.sparta.easyspring.commentlike.entity.CommentLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike,Long> {
    Optional<CommentLike> findByUserAndComment(User user, Comment comment);

    @Query("SELECT cl.comment FROM CommentLike cl WHERE cl.user.id = :userId ORDER BY cl.comment.createdAt DESC")
    Page<Comment> findLikedCommentsByUserId(@Param("userId") Long userId, Pageable pageable);
}
