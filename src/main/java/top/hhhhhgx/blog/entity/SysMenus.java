package top.hhhhhgx.blog.entity;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SysMenus implements Serializable {
    /**
     * 主键
     */
    private Integer mId;

    /**
     * 菜单名
     */
    private String mName;

    /**
     * 父id；默认为0 ，就是根菜单
     */
    private Integer mParentid;

    /**
     * 菜单code
     */
    private String mCode;

    /**
     * 路由url
     */
    private String mUrl;

    /**
     * 菜单图标
     */
    private String mIcon;

    /**
     * 是否有效  0有效  1无效
     */
    private Integer mIsvalid;

    /**
     * 排序
     */
    private Integer mOrder;

    /**
     * 描述
     */
    private String mInfo;

    /**
     * 创建时间
     */
    private Date mCreatedate;

    private String mRedirect;

    private String mComponent;

    private String mTitle;

    private Integer mHidden;

    private Integer mAlwaysShow;

    private Integer mNoCache;

    private Integer mBreadcrumb;

    /**
     * 更新时间
     */
    private Date mUpdatedate;

    private List<SysMenus> menus;

    private static final long serialVersionUID = 1L;


    public boolean isNull() {
        return StringUtils.isEmpty(this.mCode)
                || StringUtils.isEmpty(this.mIsvalid) || StringUtils.isEmpty(this.mName) || StringUtils.isEmpty(this.mOrder)
                || StringUtils.isEmpty(this.mParentid) || StringUtils.isEmpty(this.mUrl) || StringUtils.isEmpty(mComponent)
                || StringUtils.isEmpty(mTitle);
    }


    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Integer getmParentid() {
        return mParentid;
    }

    public void setmParentid(Integer mParentid) {
        this.mParentid = mParentid;
    }

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmIcon() {
        return mIcon;
    }

    public void setmIcon(String mIcon) {
        this.mIcon = mIcon;
    }

    public Integer getmIsvalid() {
        return mIsvalid;
    }

    public void setmIsvalid(Integer mIsvalid) {
        this.mIsvalid = mIsvalid;
    }

    public Integer getmOrder() {
        return mOrder;
    }

    public void setmOrder(Integer mOrder) {
        this.mOrder = mOrder;
    }

    public String getmInfo() {
        return mInfo;
    }

    public void setmInfo(String mInfo) {
        this.mInfo = mInfo;
    }

    public Date getmCreatedate() {
        return mCreatedate;
    }

    public void setmCreatedate(Date mCreatedate) {
        this.mCreatedate = mCreatedate;
    }

    public String getmRedirect() {
        return mRedirect;
    }

    public void setmRedirect(String mRedirect) {
        this.mRedirect = mRedirect;
    }

    public String getmComponent() {
        return mComponent;
    }

    public void setmComponent(String mComponent) {
        this.mComponent = mComponent;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Integer getmHidden() {
        return mHidden;
    }

    public void setmHidden(Integer mHidden) {
        this.mHidden = mHidden;
    }

    public Integer getmAlwaysShow() {
        return mAlwaysShow;
    }

    public void setmAlwaysShow(Integer mAlwaysShow) {
        this.mAlwaysShow = mAlwaysShow;
    }

    public Integer getmNoCache() {
        return mNoCache;
    }

    public void setmNoCache(Integer mNoCache) {
        this.mNoCache = mNoCache;
    }

    public Integer getmBreadcrumb() {
        return mBreadcrumb;
    }

    public void setmBreadcrumb(Integer mBreadcrumb) {
        this.mBreadcrumb = mBreadcrumb;
    }

    public Date getmUpdatedate() {
        return mUpdatedate;
    }

    public void setmUpdatedate(Date mUpdatedate) {
        this.mUpdatedate = mUpdatedate;
    }

    public List<SysMenus> getMenus() {
        return menus;
    }

    public void setMenus(List<SysMenus> menus) {
        this.menus = menus;
    }
}