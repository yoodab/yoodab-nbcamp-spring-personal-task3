package com.sparta.easyspring.postlike.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.easyspring.post.entity.Post;
import com.sparta.easyspring.post.entity.QPost;
import com.sparta.easyspring.postlike.entity.QPostLike;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostLikeRepositoryImpl implements PostLikeRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Page<Post> findLikedPostsByUserIdWithPage(long userId, Pageable pageable) {
        QPostLike postLike = QPostLike.postLike;
        QPost post = QPost.post;

        JPAQuery<Post> query = jpaQueryFactory.select(post)
                .from(postLike)
                .join(postLike.post, post)
                .where(postLike.user.id.eq(userId))
                .orderBy(post.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<Post> posts = query.fetch();
        long total = query.fetchCount();

        return new PageImpl<>(posts, pageable, total);
    }
}
