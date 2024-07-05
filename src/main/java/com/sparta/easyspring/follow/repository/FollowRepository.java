package com.sparta.easyspring.follow.repository;

import com.sparta.easyspring.auth.entity.User;
import com.sparta.easyspring.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow,Long> {

    Follow findByFollowingIdAndUser(Long followingId, User user);

    @Query("SELECT f.followingId FROM Follow f WHERE f.user.id = :userId")
    List<Long> findByUserId(Long userId);
}


