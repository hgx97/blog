package top.hhhhhgx.blog.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import top.hhhhhgx.blog.common.result.Result;
import top.hhhhhgx.blog.entity.dto.UserDto;

/**
 * @program: blog
 * @description: 用户管理业务类
 * @author: hgx
 * @create: 2020-02-02 20:39
 **/
@Service
public interface UserService {

    /*** 
    * @Description: 获取所有用户信息
    * @Param: [account, userName, phone, email, startTime, endTime, pageIndex, pageSize]
    * @return: top.hhhhhgx.blog.common.result.Result
    * @Author: hgx
    * @Date: 2020/2/2
    */ 
    Result list(String account, String userName, String phone, String email, String startTime, String endTime, Integer pageIndex, Integer pageSize);

    /*** 
    * @Description: 根据id获取用户信息
    * @Param: [id]
    * @return: top.hhhhhgx.blog.common.result.Result
    * @Author: hgx
    * @Date: 2020/2/2
    */ 
    Result getUserById(@PathVariable("id") Integer id);

    /*** 
    * @Description: 更新用户信息
    * @Param: [userDto]
    * @return: top.hhhhhgx.blog.common.result.Result
    * @Author: hgx
    * @Date: 2020/2/2
    */ 
    Result updateUser(UserDto userDto);

    Result deleteUser(Integer[] id);

    Result binPhone(String account,String phone, Integer code);

    Result addUser(UserDto userDto);

    Result getUserRole(Integer id);

    Result addUserRole(Integer[] roleIds,Integer userId);
}
