package top.hhhhhgx.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.hhhhhgx.blog.common.result.Result;
import top.hhhhhgx.blog.common.result.ResultUtil;
import top.hhhhhgx.blog.entity.SysRoles;
import top.hhhhhgx.blog.service.RolesService;

/**
 * @program: blog
 * @description: 角色管理
 * @author: hgx
 * @create: 2020-02-28 15:26
 **/
@RestController
@RequestMapping(value = "/role")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @GetMapping
    //@RequiresPermissions("role:list")
    public Result list(String roleName, Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) return ResultUtil.notParm();
        return rolesService.list(roleName, pageNum, pageSize);
    }

    @GetMapping("/{roleId}/auth")
    //@RequiresPermissions("role:auth")
    public Result getRoleAuth(@PathVariable("roleId") Integer roleId,Integer menuId) {
        if (roleId == null) return ResultUtil.notParm();
        return rolesService.getRoleAuth(roleId, menuId);
    }

    @PostMapping
    //@RequiresPermissions("role:add")
    public Result add(SysRoles roles) {
        if (roles.isNull()) return ResultUtil.notParm();
        return rolesService.add(roles);
    }

    @PutMapping
    //@RequiresPermissions("role:update")
    public Result update(SysRoles roles) {
        if (roles.getRoleId() == null) return ResultUtil.notParm();
        return rolesService.update(roles);
    }

    @DeleteMapping
    //@RequiresPermissions("role:delete")
    public Result delete(String[] ids) {
        if (ids == null) return ResultUtil.notParm();
        return rolesService.delete(ids);
    }

    @PostMapping("/{roleId}/auth")
    //@RequiresPermissions("role:addAuth")
    public Result addRoleAuth(@PathVariable("roleId") Integer roleId, Integer[] authIds){
        if (roleId == null) return ResultUtil.notParm();
        return rolesService.addRoleAuth(roleId, authIds);
    }
}
