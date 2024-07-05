package com.sparta.easyspring.follow.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.easyspring.auth.entity.QUser;
import com.sparta.easyspring.auth.entity.User;
import com.sparta.easyspring.follow.dto.Top10ProfileDto;
import com.sparta.easyspring.follow.entity.QFollow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Long> findFollowingIdsByUserId(Long userId) {
        QFollow follow = QFollow.follow;

        return jpaQueryFactory.select(follow.followingId)
                .from(follow)
                .where(follow.user.id.eq(userId))
                .fetch();
    }

    @Override
    public List<Top10ProfileDto> findTop10UsersByFollowerCount() {
        QFollow follow = QFollow.follow;
        QUser user = QUser.user;

        List<Tuple> results = jpaQueryFactory
                .select(follow.followingId, follow.count())
                .from(follow)
                .groupBy(follow.followingId)
                .orderBy(follow.count().desc())
                .limit(10)
                .fetch();


        List<Long> userIds = results.stream()
                .map(tuple -> tuple.get(follow.followingId))
                .collect(Collectors.toList());

        List<User> users = jpaQueryFactory
                .selectFrom(user)
                .where(user.id.in(userIds))
                .fetch();

        return users.stream()
                .map(u -> {
                    Long followerCount = results.stream()
                            .filter(tuple -> tuple.get(follow.followingId).equals(u.getId()))
                            .findFirst()
                            .map(tuple -> tuple.get(follow.count()))
                            .orElse(0L);
                    return new Top10ProfileDto(u, followerCount);
                }).sorted((dto1, dto2) -> dto2.getFollowerCount().compareTo(dto1.getFollowerCount()))
                .collect(Collectors.toList());
    }

}
