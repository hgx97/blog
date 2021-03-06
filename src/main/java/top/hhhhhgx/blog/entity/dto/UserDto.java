package top.hhhhhgx.blog.entity.dto;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: blog
 * @description:
 * @author: hgx
 * @create: 2020-02-02 23:13
 **/
@Data
public class UserDto implements Serializable {
    /**
     * 用户主键id
     */
    private Integer userId;

    private String userPassword;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户是否注销   0否  1是     默认0
     */
    private Integer userIslogout;

    /**
     * 用户是否被封号  0否  1是   默认0
     */
    private Integer userIsseal;

    /**
     * 用户邮箱   默认-1没有绑定邮箱
     */
    private String userEmail;

    /**
     * 用户手机   默认-1没有绑定手机
     */
    private String userPhone;

    /**
     * 用户绑定的微信openid   默认为-1绑定微信
     */
    private String userWechatid;

    /**
     * 用户头像
     */
    private String userHead;

    /**
     * 用户个性签名   默认-1没有签名
     */
    private String userSignature;

    /**
     * 用户更新时间
     */
    private Date userUpdatedate;

    //判断是否为空
    public boolean isNull(){
        if (StringUtils.isEmpty(userName) && StringUtils.isEmpty(userEmail) && StringUtils.isEmpty(userHead)
                && StringUtils.isEmpty(userIslogout) && StringUtils.isEmpty(userIsseal)
                && StringUtils.isEmpty(userPhone) && StringUtils.isEmpty(userSignature)
                && StringUtils.isEmpty(userWechatid)){
            return true;
        }
        return false;
    }
}
