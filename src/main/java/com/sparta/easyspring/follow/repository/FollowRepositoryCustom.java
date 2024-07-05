package com.sparta.easyspring.follow.repository;

import com.sparta.easyspring.follow.dto.Top10ProfileDto;

import java.util.List;

public interface FollowRepositoryCustom {
    List<Long> findFollowingIdsByUserId(Long userId);

    List<Top10ProfileDto> findTop10UsersByFollowerCount();
}
