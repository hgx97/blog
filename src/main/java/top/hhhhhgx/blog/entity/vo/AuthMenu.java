package top.hhhhhgx.blog.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: blog
 * @description:
 * @author: hgx
 * @create: 2020-03-02 01:41
 **/
@Data
public class AuthMenu implements Serializable {
    private Integer authId;
    private Integer menuId;
}
