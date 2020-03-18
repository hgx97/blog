package top.hhhhhgx.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRoles implements Serializable {
    /**
    * 角色id主键
    */
    private Integer roleId;

    /**
    * 角色名字
    */
    private String roleName;

    private String roleInfo;

    /**
    * 角色是否有效   0有效  1无效   默认0
    */
    private Integer roleValid;

    /**
    * 创建时间
    */
    private Date roleCreatedate;

    /**
    * 更新时间
    */
    private Date roleUpdatedate;


    private List<SysAuthority> sysAuthorities;

    private List<SysUser> sysUsers;

    public boolean isNull(){
        return StringUtils.isEmpty(this.roleName) || StringUtils.isEmpty(this.roleValid);
    }

    private static final long serialVersionUID = 1L;
}