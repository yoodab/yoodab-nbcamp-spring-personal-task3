package com.sparta.easyspring.post.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.easyspring.post.entity.Post;
import com.sparta.easyspring.post.entity.QPost;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Post> findPostsByFollowedUsers(List<Long> userIds, Pageable pageable) {
        QPost post = QPost.post;

        if (userIds == null || userIds.isEmpty()) {
            return Page.empty(pageable);
        }

        JPAQuery<Post> query = jpaQueryFactory.selectFrom(post)
                .where(post.user.id.in(userIds))
                .orderBy(post.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<Post> posts = query.fetch();

        long total = jpaQueryFactory.selectFrom(post)
                .where(post.user.id.in(userIds))
                .fetchCount();

        return new PageImpl<>(posts, pageable, total);

    }
}
