package com.sparta.easyspring.follow.dto;

import com.sparta.easyspring.auth.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class Top10ProfileDto {
    private final Long followerCount;
    private final Long id;
    private final String username;
    private final String indroduction;

    public Top10ProfileDto(User user, Long followerCount) {
        this.followerCount = followerCount;
        this.id = user.getId();
        this.username = user.getUsername();
        this.indroduction = user.getIntroduction();
    }
}
