package kuke.board.common.event.payload;

import kuke.board.common.event.EventPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleUnlikedEventPayload implements EventPayload {

    private Long articleLikedId;
    private Long articleId;
    private Long userId;
    private LocalDateTime modifiedAt;
    private Long articleLikeCount;

}
