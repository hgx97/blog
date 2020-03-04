package top.hhhhhgx.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.hhhhhgx.blog.common.constants.ShiroConstants;
import top.hhhhhgx.blog.common.result.Result;
import top.hhhhhgx.blog.common.result.ResultCode;
import top.hhhhhgx.blog.common.result.ResultUtil;
import top.hhhhhgx.blog.entity.SysUser;
import top.hhhhhgx.blog.service.LoginService;
import top.hhhhhgx.blog.service.MenuService;
import top.hhhhhgx.blog.utils.JWTUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: blog
 * @description: 登录相关Controller
 * @author: hgx
 * @create: 2020-02-01 22:21
 **/
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private MenuService menuService;

    /*** 
     * @Description: 登录
     * @Param: [account, password, response]
     * @return: top.hhhhhgx.blog.common.result.Result
     * @Author: hgx
     * @Date: 2020/2/1
     */
    @PostMapping(value = "/login")
    public Result login(String account, String password, HttpServletResponse response) {
        if (account == null || password == null) {
            return ResultUtil.fail(ResultCode.FAIL, "请输入账号或密码");
        }
        SysUser user = loginService.login(account, password);
        if (user != null) {
            String token = JWTUtils.sign(user.getUserAccount());
            loginService.addTokenToRedis(token, user.getUserAccount());
            response.setHeader(ShiroConstants.AUTHORIZATION, token);
            /*user = loginService.getUserAuth(user.getUserAccount());
            List<SysMenus> menus = menuService.listByUser(user.getSysMenus());*/
            return ResultUtil.success(token);
        }
        return ResultUtil.fail("账号名或密码错误");
    }

    /*** 
     * @Description: 退出登录
     * @Param: [request]
     * @return: top.hhhhhgx.blog.common.result.Result
     * @Author: hgx
     * @Date: 2020/2/2
     */
    @PostMapping(value = "/loginOut")
    //@RequiresPermissions("user:loginOut")
    public Result loginOut(HttpServletRequest request) {
        String token = request.getHeader(ShiroConstants.AUTHORIZATION);
        if (StringUtils.isEmpty(token)) {
            return ResultUtil.fail(HttpStatus.UNAUTHORIZED, "您还没有登录");
        }
        return loginService.loginOut(token);
    }

    /***
     * @Description: 查看在线人数
     * @Param: []
     * @return: top.hhhhhgx.blog.common.result.Result
     * @Author: hgx
     * @Date: 2020/2/2
     */
    @GetMapping(value = "/online")
    @RequiresPermissions("user:onlineList")
    // @RequiresPermissions("user:list")
    public Result onlineList(Integer pageIndex, Integer pageSize, String account) {
        if (pageIndex == null || pageSize == null) {
            return ResultUtil.fail(ResultCode.FAIL, "请输入分页参数");
        }
        return loginService.onlineList(pageIndex, pageSize, account);
    }

    /***
     * @Description: 剔除在线用户
     * @Param: [account]
     * @return: top.hhhhhgx.blog.common.result.Result
     * @Author: hgx
     * @Date: 2020/2/2
     */
    @DeleteMapping(value = "/online/{account}")
    @RequiresPermissions("user:del")
    public Result deleteOnline(@PathVariable("account") String account) {
        if (StringUtils.isEmpty(account)) {
            return ResultUtil.fail(ResultCode.FAIL, "请输入用户id");
        }
        return loginService.deleteOnline(account);
    }
}
