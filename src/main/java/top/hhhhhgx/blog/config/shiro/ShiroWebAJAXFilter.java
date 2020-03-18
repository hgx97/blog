package top.hhhhhgx.blog.config.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.ShiroWebFilterConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.hhhhhgx.blog.config.JWT.JWTFilter;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: blog
 * @description:
 * @author: hgx
 * @create: 2020-01-19 14:56
 **/
@Configuration
public class ShiroWebAJAXFilter extends ShiroWebFilterConfiguration {

    @Bean(name = "jwtFilter")
    public JWTFilter jwtFilter() {
        return new JWTFilter();
    }

    @Override
    protected ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = super.shiroFilterFactoryBean();
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("jwtFilter", jwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        return shiroFilterFactoryBean;
    }
}
