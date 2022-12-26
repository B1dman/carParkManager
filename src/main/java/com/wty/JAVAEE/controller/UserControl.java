package com.wty.JAVAEE.controller;

import com.wty.JAVAEE.service.LoginService;
import com.wty.JAVAEE.service.UserService;
import com.wty.entity.User;
import com.wty.entity.UserCar;
import com.wty.entity.UserInfo;
import com.wty.entity.UserRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserControl {
    @Autowired
    UserService userService;
    @Autowired
    LoginService loginService;

//更改手机号
    @RequestMapping("editPhone")
    public String editPhone(HttpServletRequest request, RedirectAttributes attributes){
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        userService.editPhone(username,phone);
        attributes.addAttribute("option",1);
        return "redirect:getuser";
    }
//查看绑定车辆
    @RequestMapping("show_car")
    public String showCar(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserInfo userinfo = null;
        userinfo = (UserInfo) loginService.getuser(user.getUsername());
        //会员的剩余能绑定车辆
        String car_left_count = userService.getLeftCount(user.getUsername());
        List<UserCar> userCarList =null;
        //获取车辆列表
        userCarList = userService.getUserCarList(user.getUsername());
        model.addAttribute("car_count",userinfo.getCar_count());
        model.addAttribute("user_type_name",user.getUser_type_name());
        model.addAttribute("car_left_count",car_left_count);
        model.addAttribute("userCarList",userCarList);
        model.addAttribute("option",1);
        if(userCarList.size()==0){
            model.addAttribute("option",0);
        }

        return "car";
    }
//删除车辆绑定
    @RequestMapping("del_car")
    public String delcar(HttpServletRequest request,RedirectAttributes attributes){
        String car_card = request.getParameter("car_card");
        userService.delCar(car_card);
        return "redirect:show_car";
    }
//添加车辆
    @RequestMapping("add_car")
    public String addcar(HttpServletRequest request,RedirectAttributes attributes){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();
        String car_card = request.getParameter("car_card");
        userService.addCar(car_card,username);
        return "redirect:show_car";
    }

//获取会员信息
    @RequestMapping("show_member")
    public String showMember(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String option = request.getParameter("option");
        //修改过来的页面
        if(option!=null){
            model.addAttribute(option);
        }
        UserInfo userinfo = (UserInfo) loginService.getuser(user.getUsername());
        String user_type_name=userinfo.getUser_type_name();
        //获取会员数据
        List<UserRule> userRule = userService.getRole();
        model.addAttribute("option",0);
        model.addAttribute("user_type_name",user_type_name);
        model.addAttribute("userRole", userRule);
        return "member";
    }
//提升会员
    @RequestMapping("edit_member")
    public String addMember(HttpServletRequest request,RedirectAttributes attributes){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();
        String user_type_name = request.getParameter("user_type_name");
        //修改
        userService.editMember(username,user_type_name);
        //获取user_type_id
        String user_type_id  =userService.getIdByname(user_type_name);
        user.setUser_type_id(user_type_id);
        session.setAttribute("user",user);
        attributes.addAttribute("option",1);
        return "redirect:show_member";
    }

//获取积分
    @RequestMapping("show_mark")
    public String showMark(HttpServletRequest request,Model model){
        //判断是否为金卡会员
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String option = request.getParameter("option");
        if(option!=null){
            model.addAttribute("option",1);
        }
        String username = user.getUsername();
        UserInfo userinfo = (UserInfo) loginService.getuser(user.getUsername());
        String user_type_name=userinfo.getUser_type_name();
        if(!user_type_name.equals("金卡会员")){
            return "member_mark";
        }
        String user_mark = userService.getMark(username);
        model.addAttribute("user_type_name",user_type_name);
        model.addAttribute("user_mark",user_mark);
        return "member_mark";
    }

//积分兑换
    @RequestMapping("del_mark")
    public String delMark(HttpServletRequest request,RedirectAttributes attributes){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();
        String card_time = request.getParameter("card_time");
        String card_endtimeStr = request.getParameter("card_endtime");
        String delMark = request.getParameter("delMark");
        System.out.println(card_endtimeStr);
        //添加优惠券到用户里
        userService.addCard(card_time,card_endtimeStr,username);
        //将用户的积分点数减少
        userService.delMark(delMark,username);
        attributes.addAttribute("option",1);
        return "redirect:show_mark";
    }
}
