package com.yangtao.vote.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author：杨涛
 * @create: 2021-03-18 09:49
 */

@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加shiro的内置过滤器
        /*
         * anon ：无需认证就可以访问
         * authc：必须认证才能访问
         * user：必须拥有 记住我 功能才能访问
         * perms：拥有对某个资源的权限才能访问
         * role：拥有某个角色权限才能访问
         */
        //拦截
        Map<String, String> filterMap = new LinkedHashMap<>();
        //授权 未授权跳转 未授权页面
        filterMap.put("/voteUser/regist","anon");
        filterMap.put("/voteUser/login","anon");
        filterMap.put("/voteUser/out", "authc");
        filterMap.put("/voteSubject/maintain.html","perms[vote:admin]");
        filterMap.put("/voteSubject/add.html","authc");
        filterMap.put("/voteSubject/page","authc");
        filterMap.put("voteUser/out","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //没权限 设置登录请求 跳转登录页面
        shiroFilterFactoryBean.setLoginUrl("/voteUser/");
        //未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/voteUser/noauth");
        return shiroFilterFactoryBean;
    }


    //DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager GetDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    //创建 Realm对象 ，需要自定义
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }


    //整合shiroDialect：用来整合 shiro 和 thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }



}
