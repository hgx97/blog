package top.hhhhhgx.blog.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.hhhhhgx.blog.common.result.Result;
import top.hhhhhgx.blog.common.result.ResultUtil;

/**
 * @program: blog
 * @description: 统一异常拦截
 * @author: hgx
 * @create: 2020-01-15 00:11
 **/
@ControllerAdvice
@ResponseBody
@Slf4j
public class UniteExceptionHandler {

    /***
    * @Description: 没有权限异常拦截
    * @Param: []
    * @return: com.alibaba.fastjson.JSONObject
    * @Author: hgx
    * @Date: 2020/1/15
    */
    //@ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public Result unauthorizedExceptionHandler() {
        return ResultUtil.fail(HttpStatus.FORBIDDEN,"您没有权限访问该接口");
    }

    /*** 
    * @Description: 没有登录异常拦截
    * @Param: []
    * @return: com.alibaba.fastjson.JSONObject
    * @Author: hgx
    * @Date: 2020/1/15
    */
    //@ResponseStatus(HttpStatus.PROXY_AUTHENTICATION_REQUIRED)
    @ExceptionHandler(UnauthenticatedException.class)
    public Result unauthenticatedException() {
        return ResultUtil.fail(HttpStatus.UNAUTHORIZED,"您还没登录");
    }

    //@ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(TokenExpiredException.class)
    public Result tokenExpiredException(){
        return ResultUtil.fail(HttpStatus.UNAUTHORIZED,"无效的token");
    }
}
