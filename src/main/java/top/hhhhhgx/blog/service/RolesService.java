package top.hhhhhgx.blog.service;

import org.springframework.stereotype.Service;
import top.hhhhhgx.blog.common.result.Result;
import top.hhhhhgx.blog.entity.SysRoles;

/**
 * @program: blog
 * @description:
 * @author: hgx
 * @create: 2020-02-28 15:27
 **/
@Service
public interface RolesService {

    Result list(String roleName, Integer pageNum, Integer pageSize);

    Result getRoleAuth(Integer roleId,Integer menuId);

    Result add(SysRoles roles);

    Result update(SysRoles roles);

    Result delete(String[] ids);

    Result addRoleAuth(Integer roleId, Integer[] authIds);
}
