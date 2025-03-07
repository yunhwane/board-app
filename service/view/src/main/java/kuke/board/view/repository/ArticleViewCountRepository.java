package kuke.board.view.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArticleViewCountRepository {

    private final RedisTemplate<String, String> redisTemplate;

    // key format :
    private static final String KEY_FORMAT = "view::article::%s::view_count";


    public Long read(Long articleId) {
        String result = redisTemplate.opsForValue().get(generateKey(articleId));
        return result == null ? 0 : Long.parseLong(result);
    }

    public Long increase(Long articleId) {
        return redisTemplate.opsForValue().increment(generateKey(articleId));
    }

    public Long decrease(Long articleId) {
        return redisTemplate.opsForValue().increment(generateKey(articleId));
    }


    private String generateKey(Long articleId) {
        return String.format(KEY_FORMAT, articleId);
    }

}
