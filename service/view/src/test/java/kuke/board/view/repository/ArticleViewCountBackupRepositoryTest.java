package kuke.board.view.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import kuke.board.view.entity.ArticleViewCount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ArticleViewCountBackupRepositoryTest {

    @Autowired
    ArticleViewCountBackupRepository articleViewCountBackupRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @Transactional
    void updateViewCountTest() {
        // given
        articleViewCountBackupRepository.save(
                ArticleViewCount.init(1L, 0L)
        );

        entityManager.flush();
        entityManager.clear();

        //when
        int result1 = articleViewCountBackupRepository.updateViewCount(1L, 100L);
        int result2 = articleViewCountBackupRepository.updateViewCount(1L, 300L);
        int result3 = articleViewCountBackupRepository.updateViewCount(1L, 200L);

        ArticleViewCount articleViewCount = articleViewCountBackupRepository.findById(1L).get();

        //then
        assertThat(articleViewCount.getViewCount()).isEqualTo(300L);


    }

}