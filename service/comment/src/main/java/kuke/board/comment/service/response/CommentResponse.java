package kuke.board.comment.service.response;


import kuke.board.comment.entity.Comment;
import kuke.board.comment.entity.CommentV2;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class CommentResponse {
    private Long commentId;
    private String content;
    private Long parentCommentId;
    private Long articleId;
    private Long writerId;
    private Boolean deleted;
    private String path;
    private LocalDateTime createdAt;

    public static CommentResponse from(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.commentId = comment.getCommentId();
        response.content = comment.getContent();
        response.parentCommentId = comment.getParentCommentId();
        response.articleId = comment.getArticleId();
        response.writerId = comment.getWriterId();
        response.deleted = comment.getDeleted();
        response.createdAt = comment.getCreatedAt();
        return response;
    }

    public static CommentResponse from(CommentV2 commentV2) {
        CommentResponse response = new CommentResponse();
        response.commentId = commentV2.getCommentId();
        response.content = commentV2.getContent();
        response.articleId = commentV2.getArticleId();
        response.writerId = commentV2.getWriterId();
        response.deleted = commentV2.getDeleted();
        response.createdAt = commentV2.getCreatedAt();
        response.path = commentV2.getCommentPath().getPath();
        return response;
    }
}
