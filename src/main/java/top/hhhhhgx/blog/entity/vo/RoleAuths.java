package top.hhhhhgx.blog.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @program: blog
 * @description:
 * @author: hgx
 * @create: 2020-02-28 18:51
 **/
@Data
public class RoleAuths implements Serializable {
    private String name;
    private Set<Integer> menuId;
    private Set<AuthMenu> authIds;
}
