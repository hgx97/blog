package top.hhhhhgx.blog.dao;

import org.apache.ibatis.annotations.Param;
import top.hhhhhgx.blog.entity.SysAuthority;
import top.hhhhhgx.blog.entity.dto.AuthDto;

import java.util.List;

public interface SysAuthorityMapper {
    int deleteByPrimaryKey(Integer authId);

    int delete(@Param("ids") String[] ids);

    int insert(AuthDto record);

    int insertSelective(SysAuthority record);

    SysAuthority selectByPrimaryKey(Integer authId);

    int updateByPrimaryKeySelective(AuthDto record);

    int updateByPrimaryKey(SysAuthority record);

    SysAuthority getSysAuthority(Integer id);

    List<SysAuthority> list(@Param("menuId") String menuId, @Param("authName") String authName, @Param("authCode") String authCode);
}