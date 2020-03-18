package top.hhhhhgx.blog.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import top.hhhhhgx.blog.common.result.Result;
import top.hhhhhgx.blog.common.result.ResultUtil;
import top.hhhhhgx.blog.dao.SysMenusMapper;
import top.hhhhhgx.blog.entity.SysMenus;
import top.hhhhhgx.blog.entity.dto.Menus;
import top.hhhhhgx.blog.entity.dto.Routes;
import top.hhhhhgx.blog.service.MenuService;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @program: blog
 * @description: 菜单配置
 * @author: hgx
 * @create: 2020-02-06 20:36
 **/
@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Autowired
    private SysMenusMapper sysMenusMapper;

    @Override
    public Result list(String mName, String mIsvalid) {
        List<SysMenus> menus = sysMenusMapper.list(mName,mIsvalid);
        if (menus != null && menus.size() > 0)
            return ResultUtil.success(menus);
        return ResultUtil.fail(HttpStatus.NO_CONTENT,"暂无菜单信息");
    }

    @Override
    public Result add(SysMenus menus) {
        int num = sysMenusMapper.insert(menus);
        if (num > 0)return ResultUtil.success();
        return ResultUtil.fail("添加失败");
    }

    @Override
    public Result update(Menus menus) {
        int num = sysMenusMapper.updateByPrimaryKeySelective(menus);
        if (num > 0) return ResultUtil.success();
        return ResultUtil.fail("修改失败");
    }

    @Override
    public Result delete(String[] ids) {
        int num = sysMenusMapper.delete(ids);
        //TODO 如果为父节点删除则子节点也应该删除和对应的权限也要删除，后面再完善
        if (num > 0) return ResultUtil.success();
        return ResultUtil.fail("删除失败");
    }

    @Override
    public List<Routes> listByUser(Set<String> ids) {
        return sysMenusMapper.listByUser(ids);
    }
}
