package com.sparta.easyspring.postlike.repository;

import com.sparta.easyspring.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostLikeRepositoryCustom {
    Page<Post> findLikedPostsByUserIdWithPage(long userId, Pageable pageable);
}