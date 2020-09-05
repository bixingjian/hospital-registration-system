package com.demo.registration.shiro.config;

import com.demo.registration.shiro.filter.ShiroPermissionsFilter;
import com.demo.registration.shiro.realm.CustomAuthRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/*
    shiro 配置类
 */
@Configuration
public class ShiroConfig
{

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/layer/**", "anon");
        filterChainDefinitionMap.put("/submitLogin", "anon");

        filterChainDefinitionMap.put("/**", "authc");
        filterChainDefinitionMap.put("/logout","logout");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        Map<String, Filter> filters=new LinkedHashMap<>();
        filters.put("authc", new ShiroPermissionsFilter());
        shiroFilterFactoryBean.setFilters(filters);

        return shiroFilterFactoryBean;
    }


    @Bean(name="defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("customAuthRealm") CustomAuthRealm customAuthRealm)
    {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(customAuthRealm);
        return defaultWebSecurityManager;
    }
    @Bean(name="customAuthRealm")
    public CustomAuthRealm getRealm()
    {
        return new CustomAuthRealm();
    }

    @Bean(name="shiroPermissionsFilter")
    public ShiroPermissionsFilter getShiroPermissionsFilter(){
        return new ShiroPermissionsFilter();
    }
}
