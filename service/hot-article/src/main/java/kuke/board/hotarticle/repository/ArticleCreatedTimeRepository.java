package kuke.board.hotarticle.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Repository
@RequiredArgsConstructor
public class ArticleCreatedTimeRepository {

    private final StringRedisTemplate redisTemplate;

    //hot-article::article::{articleId}::created-time

    private static final String KEY_FORMAT = "hot-article::article::%s::created-time";


    public void createOrUpdate(Long articleId, LocalDateTime createdAt, Duration ttl) {
        redisTemplate.opsForValue().set(
                generateKey(articleId),
                String.valueOf(createdAt.toInstant(ZoneOffset.UTC).toEpochMilli()),
                ttl
        );
    }

    public void delete(Long articleId) {
        redisTemplate.delete(generateKey(articleId));
    }

    /**
     * 좋아요 이벤트 요청 시 이 이벤트에 대한 게시글이 오늘 게시글인지 확인하려면 게시글 서비스에 조회가 필요하다.
     * 게시글 생성 시간을 저장하고 있으면, 오늘 게시글인지 알기 위해 게시판 서비스를 요청하지 않더라도 알 수 있기 때문에 설게함.
     */


    public LocalDateTime read(Long articleId) {
        String result = redisTemplate.opsForValue().get(generateKey(articleId));

        if(result == null) return null;

        return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(Long.valueOf(result)), ZoneOffset.UTC
        );

    }

    private String generateKey(Long articleId) {
        return String.format(KEY_FORMAT, articleId);
    }
}
