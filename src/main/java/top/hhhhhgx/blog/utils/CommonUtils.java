package top.hhhhhgx.blog.utils;

/**
 * @program: blog
 * @description: 公用的帮助类
 * @author: hgx
 * @create: 2020-02-04 21:01
 **/
public class CommonUtils {

    /***
    * @Description: 获取4位随机数字
    * @Param: []
    * @return: int
    * @Author: hgx
    * @Date: 2020/2/4
    */
    public static int getRandom(){
       return (int) (Math.random() * 10000);
    }
}
