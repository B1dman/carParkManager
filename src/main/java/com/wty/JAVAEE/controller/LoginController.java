package com.wty.JAVAEE.controller;

import com.wty.JAVAEE.service.LoginService;
import com.wty.entity.User;
import com.wty.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.model.IModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;


//登录页面
    @RequestMapping("loginHtml")
    public String intologin(){
        return "login";
    }
//登录
    @RequestMapping("login")
    public String login(HttpServletRequest request , RedirectAttributes attributes){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = null;
        user = loginService.login(username,password);
        //判断是否有此用户
        if(user==null) {
            return "login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        return "redirect:getuser";
    }

//用户信息页面的获取信息
    @RequestMapping("getuser")
    public String getuser(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        User user=(User) session.getAttribute("user");
        UserInfo userinfo = null;
        //判断是否修改手机号，如果修改option就为1
        String option = request.getParameter("option");
        if(option!=null){
            model.addAttribute("option",option);
            userinfo = loginService.getuser(user.getUsername());
            model.addAttribute("userinfo",userinfo);
            return "user";
        }
        userinfo = loginService.getuser(user.getUsername());
        model.addAttribute("userinfo",userinfo);
        return "user";
    }
}
