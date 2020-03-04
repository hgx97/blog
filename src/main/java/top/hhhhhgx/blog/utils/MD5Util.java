package top.hhhhhgx.blog.utils;

import org.springframework.util.DigestUtils;

/**
 * @program: blog
 * @description: md5
 * @author: hgx
 * @create: 2020-03-04 21:33
 **/
public class MD5Util {
    
    /*** 
    * @Description: 生成md5
    * @Param: [str]
    * @return: java.lang.String
    * @Author: hgx
    * @Date: 2020/3/4
    */ 
    public static String getMD5(String str) {
        String md5 = DigestUtils.md5DigestAsHex(str.getBytes());
        return md5;
    }

    public static void main(String[] args) {
        System.out.println(getMD5("123456"));
    }
}
