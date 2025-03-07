package kuke.board.view.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "article_view_count")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ArticleViewCount {

    @Id
    @Column(name = "article_id")
    private Long articleId;  // Shard key
    @Column(name = "view_count")
    private Long viewCount;

    public static ArticleViewCount init(Long articleId, Long viewCount) {
        ArticleViewCount articleViewCount = new ArticleViewCount();
        articleViewCount.articleId = articleId;
        articleViewCount.viewCount = viewCount;
        return articleViewCount;
    }
}
