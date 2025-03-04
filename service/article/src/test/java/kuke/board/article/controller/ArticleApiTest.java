package kuke.board.article.controller;

import kuke.board.article.entity.Article;
import kuke.board.article.service.request.ArticleCreateRequest;
import kuke.board.article.service.response.ArticlePageResponse;
import kuke.board.article.service.response.ArticleResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

class ArticleApiTest {

    RestClient restClient = RestClient.create("http://localhost:9001");

    @Test
    void createTest() {
        ArticleResponse articleResponse = create(new ArticleCreateRequest(
                "hi", "my content", 1L, 1L
        ));

        System.out.println("response : " + articleResponse);
    }

    @Test
    void readAllTest() {
        ArticlePageResponse articlePageResponse = restClient.get()
                .uri("/v1/articles?boardId=1&pageSize=30&page=50000")
                .retrieve()
                .body(ArticlePageResponse.class);

        System.out.println("response.getArticleCount() : " + articlePageResponse.getArticleCount());

        for(ArticleResponse article : articlePageResponse.getArticles()) {
            System.out.println("articleId = " + article.getArticleId());
        }
    }

    @Test
    void readTest() {
        ArticleResponse articleResponse = read(155225031688130560L);
        System.out.println("response : " + articleResponse);
    }

    @Test
    void updateTest() {
        ArticleResponse articleResponse = update(155225031688130560L, new ArticleUpdateRequest(
                "update", "update - content"
        ));
        System.out.println("response : " + articleResponse);
    }

    @Test
    void deleteTest() {
        delete(155225031688130560L);
    }

    void delete(Long articleId) {
        restClient.delete()
                .uri("/v1/articles/{articleId}", articleId);
    }
    ArticleResponse read(Long articleId) {
        return restClient.get()
                .uri("/v1/articles/{articleId}", articleId)
                .retrieve()
                .body(ArticleResponse.class);
    }

    ArticleResponse update(Long articleId, ArticleUpdateRequest request) {
        return restClient.put()
                .uri("/v1/articles/{articleId}", articleId)
                .body(request)
                .retrieve()
                .body(ArticleResponse.class);
    }



    ArticleResponse create(ArticleCreateRequest request) {
        return restClient.post()
                .uri("/v1/articles")
                .body(request)
                .retrieve()
                .body(ArticleResponse.class);
    }


    @Getter
    @AllArgsConstructor
    static class ArticleUpdateRequest {
        private String title;
        private String content;
    }

    @Getter
    @AllArgsConstructor
    static class ArticleCreateRequest {
        private String title;
        private String content;
        private Long writerId;
        private Long boardId;
    }
}