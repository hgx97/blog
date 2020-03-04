package top.hhhhhgx.blog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import top.hhhhhgx.blog.common.result.Result;
import top.hhhhhgx.blog.common.result.ResultCode;
import top.hhhhhgx.blog.common.result.ResultUtil;
import top.hhhhhgx.blog.controller.SmsController;
import top.hhhhhgx.blog.dao.SysRolesMapper;
import top.hhhhhgx.blog.dao.SysUserMapper;
import top.hhhhhgx.blog.entity.SysUser;
import top.hhhhhgx.blog.entity.dto.UserDto;
import top.hhhhhgx.blog.entity.vo.UserRoles;
import top.hhhhhgx.blog.service.UserService;
import top.hhhhhgx.blog.utils.JWTUtils;
import top.hhhhhgx.blog.utils.MD5Util;
import top.hhhhhgx.blog.utils.RedisUtils;

import java.util.*;

/**
 * @program: blog
 * @description: 用户管理业务类
 * @author: hgx
 * @create: 2020-02-02 20:40
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRolesMapper sysRolesMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Result list(String account, String userName, String phone,
                       String email, String startTime, String endTime,
                       Integer pageIndex, Integer pageSize) {

        Page p = PageHelper.startPage(pageIndex, pageSize);
        List<SysUser> users = sysUserMapper.list(account, userName, phone, email, startTime, endTime);
        return ResultUtil.success(users, p);
    }

    @Override
    public Result getUserById(Integer id) {
        SysUser user = sysUserMapper.getUser(id);
        if (user == null) return ResultUtil.fail(HttpStatus.NO_CONTENT, "用户不存在");
        return ResultUtil.success(user);
    }

    @Override
    public Result updateUser(UserDto userDto) {
        userDto.setUserUpdatedate(new Date());
        int num = sysUserMapper.updateByPrimaryKeySelective(userDto);
        if (num <= 0) return ResultUtil.fail("更新失败");
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result deleteUser(Integer[] id) {
        int roleNum = sysUserMapper.delUserRole(id);
        int userNum = sysUserMapper.deleteByPrimaryKey(id);
        /*log.info("删除用户(物理删除),角色行数:{},用户行数:{},jwtRedisKey是:{}-行数:{},用户id是:{},account是:{}"
                , roleNum, userNum, key, jwtNum, id, account);*/
        if (userNum > 0) {
            List<String> list = sysUserMapper.getUserAccount(id);
            list.forEach(v -> {
                String key = JWTUtils.getRedisKey(v);
                long jwtNum = redisUtils.remove(key);
            });
            return ResultUtil.success();
        }
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return ResultUtil.fail("删除失败");
    }

    @Override
    public Result binPhone(String account, String phone, Integer code) {
        String key = SmsController.getRedisKey(phone);
        boolean l = redisUtils.exists(key);
        if (!l) return ResultUtil.fail(ResultCode.THREE, "验证码已失效");
        int rcode = redisUtils.get(key);
        if (code.equals(rcode)) {
            UserDto userDto = new UserDto();
            userDto.setUserAccount(account);
            userDto.setUserPhone(phone);
            int num = sysUserMapper.updateByAccount(userDto);
            if (num > 0) {
                redisUtils.remove(key);
                return ResultUtil.success();
            };
            return ResultUtil.fail("修改失败");
        }
        return ResultUtil.fail(ResultCode.TWO, "验证码不一致");
    }

    @Override
    public Result addUser(UserDto userDto) {
        userDto.setUserPassword(MD5Util.getMD5("123456"));
        int num = sysUserMapper.insertSelective(userDto);
        if (num > 0) return ResultUtil.success();
        return ResultUtil.fail("添加失败");
    }

    @Override
    public Result getUserRole(Integer id) {
        List<UserRoles> list = sysRolesMapper.listRoles(0);
        Set<Integer> userRoles = sysRolesMapper.getUserRoles(id,0);
        Map m = new HashMap();
        m.put("roles",list);
        m.put("userRoleIds",userRoles);
        return ResultUtil.success(m);
    }

    @Override
    @Transactional
    public Result addUserRole(Integer[] roleIds, Integer userId) {
        int num = sysRolesMapper.deleteUserRoles(userId);
        if (roleIds.length > 0){
            int numm = sysRolesMapper.addUserRoles(new HashSet<>(Arrays.asList(roleIds)),userId);
            if (numm > 0) return ResultUtil.success();
        }else {
            return ResultUtil.success();
        }
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return ResultUtil.fail("添加失败");
    }
}
