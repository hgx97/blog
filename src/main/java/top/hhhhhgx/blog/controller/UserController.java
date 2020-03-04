package top.hhhhhgx.blog.controller;

import com.aliyuncs.exceptions.ClientException;
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
import top.hhhhhgx.blog.entity.dto.Routes;
import top.hhhhhgx.blog.entity.dto.UserDto;
import top.hhhhhgx.blog.service.LoginService;
import top.hhhhhgx.blog.service.MenuService;
import top.hhhhhgx.blog.service.UserService;
import top.hhhhhgx.blog.utils.JWTUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: blog
 * @description: 用户管理相关
 * @author: hgx
 * @create: 2020-02-02 20:38
 **/
@RestController
@Slf4j
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private MenuService menuService;

    /*** 
     * @Description: 获取所有用户
     * @Param: [account, userName, phone, email, startTime, endTime, pageIndex, pageSize]
     * @return: top.hhhhhgx.blog.common.result.Result
     * @Author: hgx
     * @Date: 2020/2/3
     */
    @GetMapping
    @RequiresPermissions("user:list")
    public Result list(String account, String userName, String phone
            , String email, String startTime, String endTime, Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) {
            return ResultUtil.fail(ResultCode.FAIL, "请输入分页参数");
        }
        return userService.list(account, userName, phone, email, startTime, endTime, pageNum, pageSize);
    }

    /*** 
     * @Description: 根据id获取用户信息
     * @Param: [id]
     * @return: top.hhhhhgx.blog.common.result.Result
     * @Author: hgx
     * @Date: 2020/2/3
     */
    @GetMapping(value = "/{id}")
    public Result getUserById(@PathVariable("id") Integer id) throws ClientException {
        if (id == null) {
            return ResultUtil.fail(ResultCode.FAIL, "请输入id");
        }
        return userService.getUserById(id);
    }

    /*** 
     * @Description: 更新用户信息
     * @Param: [userDto, id]
     * @return: top.hhhhhgx.blog.common.result.Result
     * @Author: hgx
     * @Date: 2020/2/3
     */
    @PatchMapping(value = "/{id}")
    public Result updateUser(UserDto userDto, @PathVariable("id") Integer id) {
        if (id == null) return ResultUtil.fail(ResultCode.FAIL, "请输入id");
        userDto.setUserId(id);
        return userService.updateUser(userDto);
    }

    /*** 
     * @Description: 物理删除用户信息（谨慎使用），会先删除用户角色->用户->jwtredis
     * @Param: [id]
     * @return: top.hhhhhgx.blog.common.result.Result
     * @Author: hgx
     * @Date: 2020/2/3
     */
    @DeleteMapping
    public Result deleteUser(Integer[] id) {
        if (id == null || id.length == 0) return ResultUtil.fail(ResultCode.FAIL, "请输入id");
        return userService.deleteUser(id);
    }

    @PatchMapping(value = "/{account}/phone/{phone}")
    public Result binPhone(@PathVariable("account") String account
            , @PathVariable("phone") String phone, Integer code) {
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(phone) || code == null)
            return ResultUtil.fail(ResultCode.FAIL, "请输入账号或手机号");
        return userService.binPhone(account, phone, code);
    }

    @GetMapping(value = "/info")
    //@RequiresPermissions("user:info")
    public Result getUserInfoAuth(HttpServletRequest request) {
        String token = request.getHeader(ShiroConstants.AUTHORIZATION);
        if (!StringUtils.isEmpty(token)) {
            String account = JWTUtils.getClaim(token, ShiroConstants.ACCOUNT);
            SysUser user = loginService.getUserAuth(account);
            if (user != null) {
                List<Routes> routesList = menuService.listByUser(user.getSysMenus());
                //设置无找到路由 都跳到404页面
               // routesList.add(new Routes("*", "/404", true));
                user.setRoutesList(routesList);
                return ResultUtil.success(user);
            }
            return ResultUtil.fail(HttpStatus.NO_CONTENT, "暂无数据");
        }
        return ResultUtil.fail(HttpStatus.UNAUTHORIZED, "您还没有登录");
    }

    @PostMapping
    public Result addUser(UserDto userDto){
        if (StringUtils.isEmpty(userDto.getUserAccount())
                || StringUtils.isEmpty(userDto.getUserName())
                || StringUtils.isEmpty(userDto.getUserHead())
        ){
            return ResultUtil.notParm();
        }
        return userService.addUser(userDto);
    }

    @GetMapping(value = "/role")
    public Result getUserRole(Integer id){
        if (id == null) return ResultUtil.notParm();
        return userService.getUserRole(id);
    }

    @PostMapping(value = "/role")
    public Result addUserRole(Integer[] roleIds,Integer userId){
        if (userId == null) return ResultUtil.notParm();
        return userService.addUserRole(roleIds, userId);
    }
}
