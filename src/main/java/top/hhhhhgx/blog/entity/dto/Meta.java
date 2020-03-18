package top.hhhhhgx.blog.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: blog
 * @description:
 * @author: hgx
 * @create: 2020-02-16 01:28
 **/
@Data
public class Meta implements Serializable {
    private String title;
    private String icon;
    private boolean noCache;
    private boolean breadcrumb;

    public void setNoCache(int noCache) {
        this.noCache = noCache == 0 ? true : false;
    }
    public void setBreadcrumb(int breadcrumb) {
        this.breadcrumb = breadcrumb == 0 ? true : false;
    }
}
