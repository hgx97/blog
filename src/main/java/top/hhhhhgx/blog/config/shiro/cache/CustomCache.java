package top.hhhhhgx.blog.config.shiro.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import top.hhhhhgx.blog.common.constants.ShiroConstants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @program: blog
 * @description:
 * @author: hgx
 * @create: 2020-01-16 14:55
 **/
@Slf4j
public class CustomCache<K, V> implements Cache<K, V> {

    private RedisTemplate<String, V> redisTemplate;

    public CustomCache(RedisTemplate<String, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /***
     * @Description: 获取一个标准的key
     * @Param: [key]
     * @return: java.lang.String
     * @Author: hgx
     * @Date: 2020/1/16
     */
    private String getKeyName(Object key) {
        /*return ShiroConstants.SHIRO_CACHE_PREFIX + JWTUtils.getClaim(key.toString(), ShiroConstants.ACCOUNT);*/
        return (ShiroConstants.SHIRO_CACHE_PREFIX + key.toString());
    }

    @Override
    public V get(K k) throws CacheException {
        return redisTemplate.opsForValue().get(getKeyName(k));
    }

    @Override
    public V put(K k, V v) throws CacheException {
        try {
            redisTemplate.opsForValue().set(getKeyName(k), v, ShiroConstants.CACHEEXPIRETIMEL, TimeUnit.SECONDS);
            return v;
        } catch (Exception e) {
            log.error("存储redis发生异常:{},key是{},val是{}", e.getMessage(), k, v);
        }
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        String key = getKeyName(k);
        V v = get(k);
        redisTemplate.delete(key);
        return v;
    }

    @Override
    public void clear() throws CacheException {
        Set set = keys();
        log.info("cache清空了redis缓存，keys：{}",set);
        redisTemplate.delete(set);
    }

    @Override
    public int size() {
        return keys().size();
    }

    @Override
    public Set keys() {
        return redisTemplate.keys(getKeyName("*"));
    }

    @Override
    public Collection<V> values() {
        Set<K> keys = keys();
        List<V> list = new ArrayList<>();
        keys.forEach(k -> {
            list.add(get(k));
        });
        return list;
    }
}
