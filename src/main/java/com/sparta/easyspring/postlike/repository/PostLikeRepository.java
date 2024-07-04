package com.sparta.easyspring.postlike.repository;

import com.sparta.easyspring.auth.entity.User;
import com.sparta.easyspring.post.entity.Post;
import com.sparta.easyspring.postlike.entity.PostLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    PostLike findByUserAndPost(User user, Post post);


    @Query("SELECT pl.post FROM PostLike pl WHERE pl.user.id = :userId ORDER BY pl.post.createdAt DESC")
    Page<Post> findLikedPostsByUserId(@Param("userId") Long userId, Pageable pageable);
}
