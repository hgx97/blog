package top.hhhhhgx.blog.common.constants;

/**
 * @program: blog
 * @description: shiro会使用到的常量
 * @author: hgx
 * @create: 2020-01-13 16:47
 **/
public interface ShiroConstants {
    //shiro缓存前缀
    String SHIRO_CACHE_PREFIX = "blog-shiro-cache:";

    String ACCOUNT = "account";
    //jwt缓存前缀
    String JWT_REDIS_KEY_PREFIX = "jwt-token:";
    String CURRENTYIMEMILLIS = "currentTimeMillis";
    String AUTHORIZATION = "Authorization";
    //shiro缓存时间
    Long CACHEEXPIRETIMEL = 180000L;  //60 * 15L;
}
