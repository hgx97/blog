package top.hhhhhgx.blog.service;

import org.springframework.stereotype.Service;
import top.hhhhhgx.blog.common.result.Result;
import top.hhhhhgx.blog.entity.SysMenus;
import top.hhhhhgx.blog.entity.dto.Menus;
import top.hhhhhgx.blog.entity.dto.Routes;

import java.util.List;
import java.util.Set;

/**
 * @program: blog
 * @description: 菜单配置
 * @author: hgx
 * @create: 2020-02-06 20:35
 **/
@Service
public interface MenuService {

    /*** 
    * @Description: 查询全部菜单信息
    * @Param: [mName]
    * @return: top.hhhhhgx.blog.common.result.Result
    * @Author: hgx
    * @Date: 2020/2/7
    */ 
    Result list(String mName, String mIsvalid);

    /*** 
    * @Description: 新增一个菜单
    * @Param: [menus]
    * @return: top.hhhhhgx.blog.common.result.Result
    * @Author: hgx
    * @Date: 2020/2/7
    */ 
    Result add(SysMenus menus);

    /*** 
    * @Description: 修改菜单
    * @Param: [menus]
    * @return: top.hhhhhgx.blog.common.result.Result
    * @Author: hgx
    * @Date: 2020/2/9
    */ 
    Result update(Menus menus);

    /*** 
    * @Description: 删除菜单
    * @Param: [ids]
    * @return: top.hhhhhgx.blog.common.result.Result
    * @Author: hgx
    * @Date: 2020/2/9
    */ 
    Result delete(String[] ids);

    List<Routes> listByUser(Set<String> ids);
}
