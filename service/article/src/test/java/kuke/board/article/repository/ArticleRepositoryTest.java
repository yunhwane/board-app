package kuke.board.article.repository;

import kuke.board.article.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    void findAllTest() {
        List<Article> articles = articleRepository.findAll(1L, 1000000L, 30L);
        log.info(articles.size() + " articles");

        for (Article article : articles) {
            log.info("article: " + article);
        }
    }

    @Test
    void countTest() {
        Long count = articleRepository.count(1L, 1000000L);
        log.info(count + " articles");
    }

    @Test
    void findInfiniteScrollTest() {
        List<Article> articles = articleRepository.findAllInfiniteScroll(1L, 30L);
        log.info(articles.size() + " articles");

        for (Article article : articles) {
            log.info("article: " + article);
        }

        Long lastArticleId = articles.getLast().getArticleId();

        List<Article> articles2 = articleRepository.findAllInfiniteScroll(1L, 30L, lastArticleId);

        for (Article article : articles2) {
            log.info("article: " + article);
        }

    }
}