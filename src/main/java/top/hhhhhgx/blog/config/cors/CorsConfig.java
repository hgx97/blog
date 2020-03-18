package top.hhhhhgx.blog.config.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.hhhhhgx.blog.common.constants.ShiroConstants;

/**
 * @program: blog
 * @description: 跨域配置
 * @author: hgx
 * @create: 2020-02-13 20:54
 **/
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*")
                .exposedHeaders(ShiroConstants.AUTHORIZATION);
    }

}
