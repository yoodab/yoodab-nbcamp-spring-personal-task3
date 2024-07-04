package com.sparta.easyspring.commentlike.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.easyspring.comment.entity.Comment;
import com.sparta.easyspring.comment.entity.QComment;
import com.sparta.easyspring.commentlike.entity.QCommentLike;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentLikeRepositoryImpl implements CommentLikeRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Comment> findLikedCommentsByUserIdWithPage(long userId, Pageable pageable) {
        QCommentLike commentLike = QCommentLike.commentLike;
        QComment comment = QComment.comment1;

        JPAQuery<Comment> query = jpaQueryFactory.select(comment)
                .from(commentLike)
                .join(commentLike.comment, comment)
                .where(commentLike.user.id.eq(userId))
                .orderBy(comment.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<Comment> comments = query.fetch();
        long total = query.fetchCount();

        return new PageImpl<>(comments, pageable, total);
    }
}