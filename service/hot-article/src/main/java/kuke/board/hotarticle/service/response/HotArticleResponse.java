package kuke.board.hotarticle.service.response;


import kuke.board.hotarticle.client.ArticleClient;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HotArticleResponse {
    private Long articleId;
    private String title;
    private LocalDateTime createdAt;

    public static HotArticleResponse from(ArticleClient.ArticleResponse articleResponse) {
        HotArticleResponse HotArticleResponse = new HotArticleResponse();
        HotArticleResponse.articleId = articleResponse.getArticleId();
        HotArticleResponse.title = articleResponse.getTitle();
        HotArticleResponse.createdAt = articleResponse.getCreatedAt();
        return HotArticleResponse;
    }
}
