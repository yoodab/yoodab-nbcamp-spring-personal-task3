package com.sparta.easyspring.post.repository;

import com.sparta.easyspring.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {
    Page<Post> findPostsByFollowedUsers(List<Long> userIds, Pageable pageable);
}
