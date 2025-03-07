package kuke.board.view.service;


import jakarta.transaction.Transactional;
import kuke.board.view.entity.ArticleViewCount;
import kuke.board.view.repository.ArticleViewCountBackupRepository;
import kuke.board.view.repository.ArticleViewCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticleViewCountBackUpProcessor {

    private final ArticleViewCountBackupRepository articleViewCountBackupRepository;

    @Transactional
    public void backup(Long articleId, Long viewCount) {
        int result = articleViewCountBackupRepository.updateViewCount(articleId, viewCount);
        if (result == 0) {
            articleViewCountBackupRepository.findById(articleId)
                    .ifPresentOrElse(ignored -> { },
                            () -> articleViewCountBackupRepository.save(ArticleViewCount.init(articleId, viewCount))
                    );
        }

    }
}
