package top.hhhhhgx.blog.config.shiro;

import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import top.hhhhhgx.blog.common.constants.ShiroConstants;
import top.hhhhhgx.blog.config.JWT.JWTToken;
import top.hhhhhgx.blog.entity.SysUser;
import top.hhhhhgx.blog.service.LoginService;
import top.hhhhhgx.blog.utils.JWTUtils;
import top.hhhhhgx.blog.utils.RedisUtils;

import java.util.Set;

/***
 * @Description: 自定义Realm
 * @Param:
 * @return:
 * @Author: hgx
 * @Date: 2020/1/13
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }


    /***
     * @Description: 授权方法；进行权限校验的时候会调用这个方法
     * @Param: [principals]
     * @return: org.apache.shiro.authz.AuthorizationInfo
     * @Author: hgx
     * @Date: 2020/1/13
     */
    @Override
    @SuppressWarnings("unchecked")
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //获取token
        String token = principals.toString();

        //从jwt获取账号
        String account = JWTUtils.getClaim(token, ShiroConstants.ACCOUNT);

        //查询用户的权限
        Set<String> permission = loginService.getUserAuth(account).getSysAuthorities();

        log.info("permission的值为:" + permission);
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permission);
        return authorizationInfo;
    }

    /***
     * @Description: 认证方法；当用户登录的时候会调用该方法
     * @Param: [authcToken]
     * @return: org.apache.shiro.authc.AuthenticationInfo
     * @Author: hgx
     * @Date: 2020/1/13
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        //获取到token
        String token = (String) authcToken.getCredentials();
        if (StringUtils.isEmpty(token)) {
            throw new UnauthenticatedException("token不存在");
        }
        String account = JWTUtils.getClaim(token, ShiroConstants.ACCOUNT);
        String redisKey = JWTUtils.getRedisKey(account);
        String redisJwt = redisUtils.get(redisKey);
        if (JWTUtils.verify(token) && !StringUtils.isEmpty(redisJwt)) {
            SysUser user = loginService.getUser(account);
            if (user == null) {
                //没找到帐号
                throw new UnknownAccountException();
            }
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(token, token, this.getClass().getName());
            return authenticationInfo;
        }
        throw new TokenExpiredException("token已失效");
    }
}
