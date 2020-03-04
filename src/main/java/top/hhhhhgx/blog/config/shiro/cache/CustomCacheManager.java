package top.hhhhhgx.blog.config.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @program: blog
 * @description: 重写shiro缓存管理器
 * @author: hgx
 * @create: 2020-01-16 15:33
 **/
@Component
public class CustomCacheManager implements CacheManager {

    private RedisTemplate redisTemplate;

    public CustomCacheManager(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return new CustomCache<>(redisTemplate);
    }
}
