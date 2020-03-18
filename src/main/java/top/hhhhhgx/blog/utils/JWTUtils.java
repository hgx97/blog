package top.hhhhhgx.blog.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.hhhhhgx.blog.common.constants.ShiroConstants;

import java.util.Date;

/**
 * @program: blog
 * @description: JWT工具类
 * @author: hgx
 * @create: 2020-01-16 15:45
 **/
@Slf4j
@Component
public class JWTUtils {

    //token过期时间
    private static Long tokenExpireTime;

    //jwt签名字符串
    private static String encryptJWTKey;

    @Value("${shiro.jwt.tokenExpireTime}")
    public void setTokenExpireTime(Long tokenExpireTime) {
        JWTUtils.tokenExpireTime = tokenExpireTime;
    }

    @Value("${shiro.jwt.encryptJWTKey}")
    public void setEncryptJWTKey(String encryptJWTKey) {
        JWTUtils.encryptJWTKey = encryptJWTKey;
    }

    /***
     * @Description: 获取jwt存在redis的key
     * @Param: [account]
     * @return: java.lang.String
     * @Author: hgx
     * @Date: 2020/2/2
     */
    public static String getRedisKey(String account){
        return ShiroConstants.JWT_REDIS_KEY_PREFIX + account;
    }

    /***
     * @Description: 验证token
     * @Param: [token]
     * @return: boolean
     * @Author: hgx
     * @Date: 2020/1/19
     */
    public static boolean verify(String token) {
        //账号+签名字符串组成秘钥
        String secret = getClaim(token, ShiroConstants.ACCOUNT) + encryptJWTKey;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.error("效验token发生异常，token是:{},异常信息是:{}", token, e.getMessage());
        }
        return false;
    }

    /***
     * @Description: 根据claim从token获取信息
     * @Param: [token, claim]
     * @return: java.lang.String
     * @Author: hgx
     * @Date: 2020/1/19
     */
    public static String getClaim(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
            log.error("获取token的用户名发生异常，token是:{},异常信息是:{}", token, e.getMessage());
            return null;
        }
    }

    /***
     * @Description: 根据账号生成jwt字符串，过期时间单位为秒
     * @Param: [account]
     * @return: java.lang.String
     * @Author: hgx
     * @Date: 2020/1/19
     */
    public static String sign(String account) {
        try {
            //账号+签名字符串组成秘钥
            String secret = account + encryptJWTKey;
            //token过期时间
            Long currentTimeMillis = System.currentTimeMillis() + (tokenExpireTime * 1000);
            Date date = new Date(currentTimeMillis);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带account、currentTimeMillis信息
            return JWT.create()
                    .withClaim(ShiroConstants.ACCOUNT, account)
                    .withClaim(ShiroConstants.CURRENTYIMEMILLIS, currentTimeMillis)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("生成jwt发生异常，account:{},异常信息是:{}", account, e.getMessage());
            return null;
        }
    }
}
