package top.hhhhhgx.blog.dao;

import org.apache.ibatis.annotations.Param;
import top.hhhhhgx.blog.entity.SysMenus;
import top.hhhhhgx.blog.entity.dto.Menus;
import top.hhhhhgx.blog.entity.dto.Routes;

import java.util.List;
import java.util.Set;

public interface SysMenusMapper {
    int deleteByPrimaryKey(Integer mId);

    int insert(SysMenus record);

    int insertSelective(SysMenus record);

    SysMenus selectByPrimaryKey(Integer mId);

    int updateByPrimaryKeySelective(Menus record);

    int updateByPrimaryKey(SysMenus record);

    List<SysMenus> list(@Param("mName") String mName,@Param("mIsvalid") String mIsvalid);

    int delete(@Param("ids") String[] ids);

    List<Routes> listByUser(@Param("ids") Set<String> ids);
}