package top.hhhhhgx.blog.config.JWT;

import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import top.hhhhhgx.blog.common.constants.ShiroConstants;
import top.hhhhhgx.blog.common.result.ResultUtil;
import top.hhhhhgx.blog.service.LoginService;
import top.hhhhhgx.blog.utils.JWTUtils;
import top.hhhhhgx.blog.utils.RedisUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @program: blog
 * @description: 自定义jwt过滤器
 * @author: hgx
 * @create: 2020-01-19 17:47
 **/
@Component
@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {


    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private LoginService loginService;

    /***
     * @Description: 拒绝访问
     * @Param: [request, response]
     * @return: boolean
     * @Author: hgx
     * @Date: 2020/1/19
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        this.sendChallenge(request, response);
        return false;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //查询header是否包含查看当前Header中是否携带Authorization属性
        if (this.isLoginAttempt(request,response)) {
            try {
                this.executeLogin(request, response);
            } catch (Exception e) {
                if (e.getCause() instanceof TokenExpiredException) {
                    // 该异常为JWT的AccessToken已过期，判断RefreshToken未过期就进行AccessToken刷新
                    if (this.refreshToken(request, response)) {
                        return true;
                    }
                }
                // Token认证失败直接返回Response信息
                this.handler401(response, e.getCause().getMessage());
                return false;
            }
        }
        //不带的都给通过
        return true;
    }

    private void handler401(ServletResponse response, String msg) {
        PrintWriter out = null;
        HttpServletResponse res = WebUtils.toHttp(response);
        try {
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json");
            //res.setStatus(HttpStatus.UNAUTHORIZED.value());
            out = response.getWriter();
            out.println(ResultUtil.fail(HttpStatus.UNAUTHORIZED, msg));
        } catch (Exception e) {
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }

    /**
     * 此处为AccessToken刷新，进行判断RefreshToken是否过期，未过期就返回新的AccessToken且继续正常访问
     */
    private boolean refreshToken(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        //获取header，tokenStr
        String oldToken = this.getAuthzHeader(request);
        if (!StringUtils.isEmpty(oldToken)) {
            String account = JWTUtils.getClaim(oldToken, ShiroConstants.ACCOUNT);
            String key = JWTUtils.getRedisKey(account);
            //获取redis tokenStr
            String oldRedisToken = (String) redisUtils.get(key);
            //如果redis不存在在token代表已过期
            if (oldRedisToken != null && oldToken.equals(oldRedisToken)) {
                //重写生成token(刷新)
                String newTokenStr = JWTUtils.sign(account);
                JWTToken jwtToken = new JWTToken(newTokenStr);
                loginService.addTokenToRedis(newTokenStr, account);
                SecurityUtils.getSubject().login(jwtToken);
                httpServletResponse.setHeader(ShiroConstants.AUTHORIZATION, newTokenStr);
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        /*String token = this.getAuthzHeader(request);
        if (!StringUtils.isEmpty(token)) return true;
        handler401(response, "不带" + ShiroConstants.AUTHORIZATION);*/
        return this.getAuthzHeader(request) != null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        // 拿到当前Header中Authorization的AccessToken(Shiro中getAuthzHeader方法已经实现)
        JWTToken token = new JWTToken(this.getAuthzHeader(request));
        // 提交给UserRealm进行认证，如果错误他会抛出异常并被捕获
        this.getSubject(request, response).login(token);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }
}
