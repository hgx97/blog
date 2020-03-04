package top.hhhhhgx.blog.common.result;

import com.github.pagehelper.Page;
import org.springframework.http.HttpStatus;

/**
 * @program: blog
 * @description: 统一返回结果封装
 * @author: hgx
 * @create: 2020-01-12 21:56
 **/
public class ResultUtil {

    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result success() {
        return new Result()
                .setCode(HttpStatus.OK)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> Result<T> success(T data) {
        return new Result()
                .setCode(HttpStatus.OK)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static <T> Result<T> success(T data, Page p) {
        return new Result()
                .setCode(HttpStatus.OK)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data)
                .setPage(p.getPageNum(), p.getPageSize(), p.getTotal());
    }

    public static Result success(ResultCode resultCode) {
        return new Result()
                .setCode(resultCode.value())
                .setMessage(resultCode.message());
    }

    public static <T> Result<T> success(ResultCode resultCode, T data) {
        return new Result<T>()
                .setCode(resultCode.value())
                .setMessage(resultCode.message())
                .setData(data);
    }

    public static <T> Result<T> success(ResultCode resultCode, T data, Page page) {
        return new Result<T>()
                .setCode(resultCode.value())
                .setMessage(resultCode.message())
                .setData(data)
                .setPage(page.getPageNum(), page.getPageSize(), page.getTotal());
    }

    public static Result fail(String message) {
        return new Result()
                .setCode(HttpStatus.BAD_REQUEST)
                .setMessage(message);
    }

    public static Result fail(HttpStatus httpStatus, String message) {
        return new Result()
                .setCode(httpStatus)
                .setMessage(message);
    }

    public static Result fail(ResultCode resultCode) {
        return new Result()
                .setCode(resultCode.value())
                .setMessage(resultCode.message());
    }

    public static Result fail(ResultCode resultCode, String message) {
        return new Result()
                .setCode(resultCode.value())
                .setMessage(message);
    }

    public static Result notParm(){
        return new Result()
                .setCode(ResultCode.FAIL.value())
                .setMessage("参数不能为空");
    }
}
