package top.hhhhhgx.blog.config.JWT;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @program: blog
 * @description: JWT
 * @author: hgx
 * @create: 2020-01-19 17:45
 **/
public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
