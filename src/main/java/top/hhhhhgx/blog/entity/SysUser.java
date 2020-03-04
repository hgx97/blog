package top.hhhhhgx.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hhhhhgx.blog.entity.dto.Routes;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {
    /**
    * 用户主键id
    */
    private Integer userId;

    /**
    * 用户姓名
    */
    private String userName;

    /*用户账号*/
    private String userAccount;

    /**
    * 用户密码
    */
    private String userPassword;

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
    * 用户创建时间
    */
    private Date userCreatedate;

    /**
    * 用户更新时间
    */
    private Date userUpdatedate;

    private List<String> roles;

    private List<SysRoles> sysRoles;

    private List<SysMenus> menusList;

    /*为了方便使用*/
    private Set<String> sysAuthorities;

    /*为了方便使用*/
    private Set<String> sysMenus;

    private List<Routes> routesList;

    private static final long serialVersionUID = 1L;
}