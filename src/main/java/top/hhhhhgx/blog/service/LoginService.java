package top.hhhhhgx.blog.service;

import org.springframework.stereotype.Service;
import top.hhhhhgx.blog.common.result.Result;
import top.hhhhhgx.blog.entity.SysUser;

/**
 * @program: blog
 * @description: 登录相关业务类
 * @author: hgx
 * @create: 2020-01-13 16:51
 **/
@Service
public interface LoginService {

    /***
    * @Description: 登录
    * @Param: [username, password]
    * @return: top.hhhhhgx.blog.entity.SysUser
    * @Author: hgx
    * @Date: 2020/2/1
    */
    SysUser login(String account, String password);

    /***
    * @Description: 获取用户信息
    * @Param: [userAccount]
    * @return: top.hhhhhgx.blog.entity.SysUser
    * @Author: hgx
    * @Date: 2020/2/1
    */
    SysUser getUser(String userAccount);

    /***
    * @Description: 缓存token到redis
    * @Param: [token, account]
    * @return: void
    * @Author: hgx
    * @Date: 2020/2/1
    */
    void addTokenToRedis(String token, String account);

    /***
    * @Description: 获取用户的权限
    * @Param: [account]
    * @return: top.hhhhhgx.blog.entity.SysUser
    * @Author: hgx
    * @Date: 2020/2/1
    */
    SysUser getUserAuth(String account);

    /*** 
    * @Description: 退出登录
    * @Param: [token]
    * @return: top.hhhhhgx.blog.common.result.Result
    * @Author: hgx
    * @Date: 2020/2/2
    */ 
    Result loginOut(String token);

    /*** 
    * @Description: 查看在线人数
    * @Param: [pageIndex, pageSize, account]
    * @return: top.hhhhhgx.blog.common.result.Result
    * @Author: hgx
    * @Date: 2020/2/2
    */ 
    Result onlineList(Integer pageIndex, Integer pageSize, String account);

    Result deleteOnline(String id);
}
