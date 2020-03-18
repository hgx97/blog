package top.hhhhhgx.blog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.hhhhhgx.blog.common.constants.ShiroConstants;
import top.hhhhhgx.blog.common.result.Result;
import top.hhhhhgx.blog.common.result.ResultCode;
import top.hhhhhgx.blog.common.result.ResultUtil;
import top.hhhhhgx.blog.dao.SysUserMapper;
import top.hhhhhgx.blog.entity.SysUser;
import top.hhhhhgx.blog.service.LoginService;
import top.hhhhhgx.blog.utils.JWTUtils;
import top.hhhhhgx.blog.utils.RedisUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: blog
 * @description: 登录相关业务实现类
 * @author: hgx
 * @create: 2020-01-13 16:52
 **/
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Value("${shiro.jwt.tokenExpireTime}")
    private Long tokenExpireTime;

    @Override
    public SysUser login(String account, String password) {
        return sysUserMapper.login(account, password);
    }

    @Override
    @Cacheable(value = "getUser", key = "#userAccount")
    public SysUser getUser(String userAccount) {
        return sysUserMapper.getUserInfo(userAccount);
    }

    @Override
    public void addTokenToRedis(String token, String account) {
        String key = JWTUtils.getRedisKey(account);
        Long num = tokenExpireTime + 15 * 60;
        boolean l = redisUtils.set(key, token, num);
        log.info("addTokenToRedis-->缓存token key是{},过期时间:{},结果:{}", key, num, l);
    }

    @Override
    @Cacheable(value = "getUserAuth", key = "#account")
    public SysUser getUserAuth(String account) {
        return sysUserMapper.getUserAuth(account);
    }

    @Override
    public Result loginOut(String token) {
        boolean l = JWTUtils.verify(token);
        if (!l) {
            return ResultUtil.fail(ResultCode.TWO,"token验证不通过");
        }
        String account = JWTUtils.getClaim(token, ShiroConstants.ACCOUNT);
        String key = JWTUtils.getRedisKey(account);
        SecurityUtils.getSubject().logout();
        long num = redisUtils.remove(key);
        log.info("loginOut-->account:{},退出登录删除redis key:是{},删除成功记录条数:{}", account, key, num);
        return ResultUtil.success();
    }

    @Override
    public Result onlineList(Integer pageIndex, Integer pageSize, String account) {
        String key = JWTUtils.getRedisKey("*");
        if (!StringUtils.isEmpty(account)) {
            key += account + "*";
        }
        Set<String> keys = redisUtils.getKeys(key);
        if (keys == null || keys.size() == 0) {
            return ResultUtil.fail(ResultCode.TWO,"暂无在线人员信息");
        }
        List<String> accounts = keys.stream().map(k -> k.split(":")[1]).collect(Collectors.toList());

        Page p = PageHelper.startPage(pageIndex, pageIndex);
        List<SysUser> users = sysUserMapper.getUsers(accounts);
        return ResultUtil.success(users, p);
    }

    @Override
    public Result deleteOnline(String account) {
        String key = JWTUtils.getRedisKey(account);
        long num = redisUtils.remove(key);
        log.info("剔除在线人账号:{},redisKey是:{},结果行数:{}", account, key, num);
        if (num > 0) return ResultUtil.success();
        return ResultUtil.fail("剔除失败");
    }
}
