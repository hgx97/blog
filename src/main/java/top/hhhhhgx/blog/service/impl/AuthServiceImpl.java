package top.hhhhhgx.blog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.hhhhhgx.blog.common.result.Result;
import top.hhhhhgx.blog.common.result.ResultUtil;
import top.hhhhhgx.blog.dao.SysAuthorityMapper;
import top.hhhhhgx.blog.entity.SysAuthority;
import top.hhhhhgx.blog.entity.dto.AuthDto;
import top.hhhhhgx.blog.service.AuthService;

import java.util.List;

/**
 * @program: blog
 * @description:
 * @author: hgx
 * @create: 2020-02-23 18:42
 **/
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private SysAuthorityMapper sysAuthorityMapper;

    @Override
    public Result list(String menuId, String authName, String authCode, Integer pageIndex, Integer pageSize) {
        Page p = PageHelper.startPage(pageIndex, pageSize);
        List<SysAuthority> list = sysAuthorityMapper.list(menuId, authName, authCode);
        return ResultUtil.success(list, p);
    }

    @Override
    public Result add(AuthDto authority) {
        if (StringUtils.isEmpty(authority.getAuthIsrequired())){
            authority.setAuthIsrequired(0);
        }
        int num = sysAuthorityMapper.insert(authority);
        if (num > 0) return ResultUtil.success();
        return ResultUtil.fail("添加失败");
    }

    @Override
    public Result update(AuthDto authority) {
        int num = sysAuthorityMapper.updateByPrimaryKeySelective(authority);
        if (num > 0) return ResultUtil.success();
        return ResultUtil.fail("修改失败");
    }

    @Override
    public Result delete(String[] ids) {
        int num = sysAuthorityMapper.delete(ids);
        if (num > 0) return ResultUtil.success();
        return ResultUtil.fail("删除失败");
    }
}
