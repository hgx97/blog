package top.hhhhhgx.blog.service;

import org.springframework.stereotype.Service;
import top.hhhhhgx.blog.common.result.Result;
import top.hhhhhgx.blog.entity.dto.AuthDto;

/**
 * @program: blog
 * @description:
 * @author: hgx
 * @create: 2020-02-23 18:42
 **/
@Service
public interface AuthService {

    Result list(String menuId,String authName, String authCode, Integer pageIndex, Integer pageSize);

    Result add(AuthDto authority);

    Result update(AuthDto authority);

    Result delete(String[] ids);
}
