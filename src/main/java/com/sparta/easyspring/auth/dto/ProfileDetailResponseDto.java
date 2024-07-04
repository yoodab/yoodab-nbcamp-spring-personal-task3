package com.sparta.easyspring.auth.dto;

import lombok.Getter;

@Getter
public class ProfileDetailResponseDto extends ProfileResponseDto {
    int likedPostsCount;
    int likedCommentsCount;

    public ProfileDetailResponseDto(Long id, String username, String indroduction, int likedPostsCount, int likedCommentsCount) {
        super(id, username, indroduction);
        this.likedPostsCount = likedPostsCount;
        this.likedCommentsCount = likedCommentsCount;
    }
}
