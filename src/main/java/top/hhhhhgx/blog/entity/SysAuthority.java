package top.hhhhhgx.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysAuthority implements Serializable {
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

    /**
    * 创建时间
    */
    private Date authCreatedate;

    private Integer authMenuId;

    /**
    * 更新时间
    */
    private Date authUpdatedate;

    private List<SysRoles> sysRoles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysAuthority that = (SysAuthority) o;
        return Objects.equals(authId, that.authId) &&
                Objects.equals(authName, that.authName) &&
                Objects.equals(authCode, that.authCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authId, authName, authCode);
    }

    public boolean isNull(){
        return StringUtils.isEmpty(this.authCode) || StringUtils.isEmpty(this.authName);
    }

    private static final long serialVersionUID = 1L;
}