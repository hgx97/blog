package top.hhhhhgx.blog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import top.hhhhhgx.blog.common.result.Result;
import top.hhhhhgx.blog.common.result.ResultUtil;
import top.hhhhhgx.blog.dao.SysRolesMapper;
import top.hhhhhgx.blog.entity.SysRoles;
import top.hhhhhgx.blog.entity.vo.RoleAuths;
import top.hhhhhgx.blog.service.RolesService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @program: blog
 * @description:
 * @author: hgx
 * @create: 2020-02-28 15:27
 **/
@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private SysRolesMapper sysRolesMapper;

    @Override
    public Result list(String roleName, Integer pageNum, Integer pageSize) {
        Page p = PageHelper.startPage(pageNum, pageSize);
        List<SysRoles> list = sysRolesMapper.list(roleName);
        return ResultUtil.success(list, p);
    }

    @Override
    public Result getRoleAuth(Integer roleId, Integer menuId) {
        RoleAuths roleAuths = sysRolesMapper.getRoleAuth(roleId, menuId);
        return ResultUtil.success(roleAuths);
    }

    @Override
    public Result add(SysRoles roles) {
        int num = sysRolesMapper.insert(roles);
        if (num > 0) return ResultUtil.success();
        return ResultUtil.fail("添加失败");
    }

    @Override
    public Result update(SysRoles roles) {
        int num = sysRolesMapper.updateByPrimaryKeySelective(roles);
        if (num > 0) return ResultUtil.success();
        return ResultUtil.fail("修改失败");
    }

    @Override
    @Transactional
    public Result delete(String[] ids) {
        int num = sysRolesMapper.delete(ids);
        int numm = sysRolesMapper.deleteRoleAuth(Arrays.asList(ids));
        if (numm > 0) return ResultUtil.success();
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return ResultUtil.fail("删除失败");
    }

    @Override
    @Transactional
    public Result addRoleAuth(Integer roleId, Integer[] authIds) {
        // 先删除原来的权限  然后在新增
        int num = sysRolesMapper.deleteRoleAuth(Arrays.asList(roleId));
        if (authIds.length > 0){
            int numm = sysRolesMapper.addRoleAuth(roleId, new HashSet<>(Arrays.asList(authIds)));
            if (numm > 0) return ResultUtil.success();
        }else {
            return ResultUtil.success();
        }
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return ResultUtil.fail("修改失败");
    }
}
