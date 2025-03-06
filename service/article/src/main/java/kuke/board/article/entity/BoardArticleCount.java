package kuke.board.article.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "board_article_count")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class BoardArticleCount {

    @Id
    private Long boardId; // shard key
    private Long articleCount;

    public static BoardArticleCount init(Long boardId, Long articleCount) {
        BoardArticleCount boardArticleCount = new BoardArticleCount();
        boardArticleCount.boardId = boardId;
        boardArticleCount.articleCount = articleCount;
        return boardArticleCount;
    }

}
