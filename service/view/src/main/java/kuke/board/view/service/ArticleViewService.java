package kuke.board.view.service;

import jakarta.transaction.Transactional;
import kuke.board.view.repository.ArticleViewCountBackupRepository;
import kuke.board.view.repository.ArticleViewCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleViewService {

    private final ArticleViewCountBackUpProcessor articleViewCountBackUpProcessor;
    private final ArticleViewCountRepository articleViewCountRepository;

    private static final int BACK_UP_BATCH_SIZE = 100;


    public Long increase(Long articleId, Long userId) {
        Long count = articleViewCountRepository.increase(articleId);

        if (count % BACK_UP_BATCH_SIZE == 0) {
            articleViewCountBackUpProcessor.backup(articleId, count);
        }

        return count;
    }

    public Long count(Long articleId) {
        return articleViewCountRepository.read(articleId);
    }

}
