package com.yangtao.vote.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yangtao.vote.entity.VoteUser;
import com.yangtao.vote.service.VoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangtao
 * @since 2021-03-22
 */
@Slf4j
@Controller
@RequestMapping("/voteUser")
public class VoteUserController {

    @Autowired
    VoteUserService userService;



    @RequestMapping("/")
    public String toLogin(){
        return "login";
    }
    @RequestMapping({"/toRegister"})
    public String toRegister(){
        return "user/regist";
    }

    @RequestMapping({"/add"})
    public String add(){
        return "user/regist";
    }

    /**
     * 登录
     * @param vuUserName 用户名
     * @param vuPassword 密码
     * @param model m
     * @return 首页
     */
    @RequestMapping("/login")
    public String login(String vuUserName, String vuPassword, Model model){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        log.info("获取当前用户___________{}",subject);
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(vuUserName,vuPassword);
        try{
            //执行登录的方法，如果没有异常，登陆成功
            log.info("获取当前用户token___________{}",token);
            subject.login(token);
            return "forward:/voteSubject/page";
        }catch (UnknownAccountException e){
            model.addAttribute("errorMsg","用户名不存在或错误");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("errorMsg","密码错误");
            return "login";
        }
    }

    @RequestMapping("/register")
    public String register(VoteUser voteUser){
        boolean isSave = userService.save(voteUser);
        log.info("用户注册：{}",isSave);
        return "login";
    }

    /**
     * 注销
     * @return  首页
     */
    @RequestMapping("/out")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }


    @RequestMapping("/noauth")
    @ResponseBody
    public String unauthorized(){
        return "未经授权无法访问此页面";
    }



    @RequestMapping({"/selectOne"})
    @ResponseBody
    public String selectOne(String name){
        if ("".equals(name)){
            return "请填写您的用户名";
        }
            System.out.println("--------------------------------");
            QueryWrapper<VoteUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("vu_user_name",name);
            VoteUser one = userService.getOne(queryWrapper);
            if (one==null){
                return "用户名可以使用";  //用户名可以使用
            }else {
                return "用户名已被使用"; //用户名已被使用
            }

    }



}

