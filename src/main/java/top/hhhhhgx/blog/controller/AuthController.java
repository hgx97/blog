package top.hhhhhgx.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.hhhhhgx.blog.common.result.Result;
import top.hhhhhgx.blog.common.result.ResultUtil;
import top.hhhhhgx.blog.entity.SysAuthority;
import top.hhhhhgx.blog.entity.dto.AuthDto;
import top.hhhhhgx.blog.service.AuthService;

/**
 * @program: blog
 * @description:
 * @author: hgx
 * @create: 2020-01-11 23:19
 **/
@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping
    public Result list(String menuId, String authName, String authCode, Integer pageIndex, Integer pageSize) {
        return authService.list(menuId, authName, authCode, pageIndex, pageSize);
    }

    @PostMapping
    public Result add(AuthDto authority){
        if (authority.isNull()) return ResultUtil.notParm();
        return authService.add(authority);
    }

    @PutMapping
    public Result update(AuthDto authority){
        return authService.update(authority);
    }

    @DeleteMapping
    public Result delete(String[] ids){
        return authService.delete(ids);
    }
}
