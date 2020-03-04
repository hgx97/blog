package top.hhhhhgx.blog.dao;

import org.apache.ibatis.annotations.Param;
import top.hhhhhgx.blog.entity.SysRoles;
import top.hhhhhgx.blog.entity.vo.RoleAuths;
import top.hhhhhgx.blog.entity.vo.UserRoles;

import java.util.List;
import java.util.Set;

public interface SysRolesMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(SysRoles record);

    int insertSelective(SysRoles record);

    SysRoles selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(SysRoles record);

    int updateByPrimaryKey(SysRoles record);

    SysRoles getRoles(Integer id);

    List<SysRoles> list(@Param("roleName") String roleName);

    RoleAuths getRoleAuth(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

    int delete(@Param("ids") String[] ids);

    int deleteRoleAuth(@Param("roleId") List roleId);

    int addRoleAuth(@Param("roleId") Integer roleId,@Param("authIds") Set<Integer> authIds);

    List<UserRoles> listRoles(@Param("roleValid") Integer roleValid);

    Set<Integer> getUserRoles(@Param("userId") Integer userId,@Param("roleValid") Integer roleValid);

    int deleteUserRoles(@Param("userId") Integer userId);

    int addUserRoles(@Param("roleIds") Set<Integer> roleIds,@Param("userId") Integer userId);
}