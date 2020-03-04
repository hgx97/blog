package top.hhhhhgx.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.hhhhhgx.blog.common.result.Result;
import top.hhhhhgx.blog.common.result.ResultCode;
import top.hhhhhgx.blog.common.result.ResultUtil;
import top.hhhhhgx.blog.entity.SysMenus;
import top.hhhhhgx.blog.entity.dto.Menus;
import top.hhhhhgx.blog.service.MenuService;

/**
 * @program: blog
 * @description: 菜单配置
 * @author: hgx
 * @create: 2020-02-06 20:34
 **/
@RestController
@RequestMapping(value = "menu")
@Slf4j
public class MenuController {

    @Autowired
    private MenuService menuService;

    /*** 
    * @Description: 查询全部菜单
    * @Param: [mName]
    * @return: top.hhhhhgx.blog.common.result.Result
    * @Author: hgx
    * @Date: 2020/2/8
    */ 
    @GetMapping
    public Result list(String mName, String mIsvalid){
        return menuService.list(mName,mIsvalid);
    }

    /*** 
    * @Description: 添加菜单
    * @Param: [menus]
    * @return: top.hhhhhgx.blog.common.result.Result
    * @Author: hgx
    * @Date: 2020/2/8
    */ 
    @PostMapping
    public Result add(SysMenus menus){
        if (menus.isNull()) return ResultUtil.fail(ResultCode.FAIL,"请输入需要的参数");
        return menuService.add(menus);
    }

    /*** 
    * @Description: 修改菜单
    * @Param: [menus]
    * @return: top.hhhhhgx.blog.common.result.Result
    * @Author: hgx
    * @Date: 2020/2/8
    */ 
    @PutMapping
    public Result update(Menus menus){
        if (menus.getmId() == null) return ResultUtil.fail(ResultCode.FAIL,"请输入id");
        return menuService.update(menus);
    }

    /*** 
    * @Description: 删除菜单，多个id用英文,拼接
    * @Param: [ids]
    * @return: top.hhhhhgx.blog.common.result.Result
    * @Author: hgx
    * @Date: 2020/2/9
    */ 
    @DeleteMapping
    public Result delete(String[] ids){
        if (StringUtils.isEmpty(ids)) return ResultUtil.fail(ResultCode.FAIL,"请输入id");
        return menuService.delete(ids);
    }
}
