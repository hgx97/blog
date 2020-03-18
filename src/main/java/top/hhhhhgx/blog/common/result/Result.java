package top.hhhhhgx.blog.common.result;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @program: blog
 * @description: 统一返回结果封装
 * @author: hgx
 * @create: 2020-01-12 21:38
 **/
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    /*状态码*/
    private int status;

    /*描述*/
    private String message;

    /*分页页数*/
    private Integer pageNum;

    /*分页数据条数*/
    private Integer pageSize;

    /*分页总共页数*/
    private Long pageTotal;

    /*数据*/
    private T data;

    public Result setCode(HttpStatus httpStatus) {
        this.status = httpStatus.value();
        return this;
    }

    public Result setCode(int code) {
        this.status = code;
        return this;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    public Result setPage(Integer pageNum, Integer pageSize, Long pageTotal) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pageTotal = pageTotal;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
