package top.hhhhhgx.blog.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: blog
 * @description: 路由类
 * @author: hgx
 * @create: 2020-02-16 00:59
 **/
@Data
public class Routes implements Serializable {

    private Integer id;
    private String path;
    private String component1;
    private String redirect;
    private boolean hidden;
    private boolean alwaysShow;
    private String name;
    private List<Routes> children;
    private Meta meta;

    public void setHidden(int hidden) {
        this.hidden = hidden == 0 ? true : false;
    }
    public void setAlwaysShow(int alwaysShow) {
        this.alwaysShow = alwaysShow == 0 ? true : false;
    }
}
