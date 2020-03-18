package top.hhhhhgx.blog.dao;

import org.apache.ibatis.annotations.Param;
import top.hhhhhgx.blog.entity.SysUser;
import top.hhhhhgx.blog.entity.dto.UserDto;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer[] userId);

    int insert(UserDto record);

    int insertSelective(UserDto record);

    SysUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserDto userDto);

    int updateByAccount(UserDto userDto);

    int updateByPrimaryKey(SysUser record);

    /*获取用户*/
    SysUser getUser(Integer id);

    /*获取用户账号*/
    List<String> getUserAccount(@Param("id") Integer[] id);

    /*删除用户角色*/
    int delUserRole(@Param("id") Integer[] id);

    /*登录*/
    SysUser login(@Param("account") String account, @Param("password") String password);

    /*获取用户权限信息*/
    SysUser getUserAuth(@Param("account") String account);

    /*获取用户信息*/
    SysUser getUserInfo(@Param("account") String account);

    /*获取用户*/
    List<SysUser> getUsers(@Param("accounts") List<String> accounts);

    /*获取全部用户*/
    List<SysUser> list(@Param("account") String account, @Param("userName") String userName, @Param("phone") String phone,
                       @Param("email") String email, @Param("startTime") String startTime, @Param("endTime") String endTime);
}