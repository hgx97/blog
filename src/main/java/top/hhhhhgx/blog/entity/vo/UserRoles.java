package top.hhhhhgx.blog.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: blog
 * @description:
 * @author: hgx
 * @create: 2020-03-04 16:01
 **/
@Data
public class UserRoles implements Serializable {
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

}
