package top.hhhhhgx.blog.entity.dto;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @program: blog
 * @description:
 * @author: hgx
 * @create: 2020-02-25 01:01
 **/
@Data
public class AuthDto {

    /**
     * 权限表主键
     */
    private Integer authId;

    /**
     * 权限名
     */
    private String authName;

    /**
     * 权限code   每个接口都有一个唯一的
     */
    private String authCode;

    /**
     * 是否必须的 0必须 1否   一般list 查看接口默认
     */
    private Integer authIsrequired;

    private String authInfo;

    private Integer authMenuId;

    public boolean isNull(){
        return StringUtils.isEmpty(this.authCode) || StringUtils.isEmpty(this.authName);
    }
}
