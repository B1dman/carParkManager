package com.wty.JAVAEE.controller;


import com.wty.JAVAEE.service.CardService;
import com.wty.JAVAEE.service.LoginService;
import com.wty.JAVAEE.service.PayService;
import com.wty.entity.*;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PayControl {
    @Autowired
    PayService payService;
    @Autowired
    LoginService loginService;
    @Autowired
    CardService cardService;

    //进入支付列表页面
    @RequestMapping("pay_self")
    public String paySelf(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();
        //支付成功
        String  option = request.getParameter("option");


        //获得进场信息列表 flag = 1
        List<UserPaycar>  userPaycarList= payService.getPaycar(username);
        //若paytime为空  说明未支付 status 0
        for (UserPaycar userPaycar : userPaycarList) {
            if(userPaycar.getCar_paytime()==null){
                userPaycar.setStatus("0");
                //若paytime不为空 但outtime为空 说明未出停车场 status 1
            }else if(userPaycar.getCar_paytime()!=null&&userPaycar.getCar_outtime()==null){
                userPaycar.setStatus("1");
            }
        }
        model.addAttribute("userPaycarList",userPaycarList);
        model.addAttribute("size",userPaycarList.size());
        return "payself";
    }

    @RequestMapping("pay_ready")
    //name username user_type phone car_card
    // intime paytime==nowtime
    //支付规则 优惠券
    public String Pay(HttpServletRequest request, Model model, RedirectAttributes attributes){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();
        System.out.println(user.getUser_type_id());
        String user_type_id = user.getUser_type_id();
        String car_card = request.getParameter("car_card");

        //获取进场时间
        String car_intime = request.getParameter("car_intime");
        //当前用户信息
        UserInfo userinfo = loginService.getuser(username);
        Date user_freetimeD = userinfo.getUser_freetime();
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
        String user_freetime = "0";
        if(user_freetimeD!=null){
            user_freetime = formatter.format(user_freetimeD);
        }
        //先获取此车的用户信息 对应的用户名和手机号
        UserInfo carUser = payService.getcarUser(car_card);
        //优惠券列表,日期要大于今天
        String nowdate = request.getParameter("nowdate");
//        System.out.println(nowdate);
        List<UserCard> userCard = cardService.getlist(username,nowdate);
        //通过user_type_id获取rule
        UserRule userRule = payService.getRule(user_type_id);


        //进行未用支付券的总价计算
        //获取已经过了的时间
        int sum_money = 0;
        int left_time = 0;
        String left_timeStr = request.getParameter("left_time");
        float left_timeF = Float.parseFloat(left_timeStr);
        //判断是否到达免费时间内
        String free_timeStr = userRule.getFree_hour();
        float free_time = Float.parseFloat(free_timeStr);

        System.out.println(user_freetime.equals(nowdate.substring(0,10)));
        //如果已经用过了，就不抵扣
        if(!user_freetime.equals(nowdate.substring(0,10))) {
            //如果小于
            if (left_timeF <= free_time) {
                sum_money = 0;
            } else {
                left_timeF = left_timeF - free_time;
            }
        }

        //本日用过优惠券的情况

        //判断lefttimeF的具体值，低于5分钟不算
            if (left_timeF - (int) left_timeF > 0.0833333333333) {
                left_time = (int) left_timeF + 1;
            } else {
                left_time = (int) left_timeF;
            }

        System.out.println(left_timeF);



        //获取每小时多少钱
        String hour_moneyS = userRule.getHour_money();
        int hour_money = Integer.parseInt(hour_moneyS);
        //获取所有值
        //开始计算
        sum_money = left_time*hour_money;


        //判断是否为支付功能option=1
        String option = request.getParameter("option");
        String left_time1S = request.getParameter("left_time1");
        int left_time1 = 0;
        if (left_time1S!=null) {
            left_time1 = Integer.parseInt(left_time1S);
        }
        //判断时间是否相同
        if(option!=null){
//            System.out.println(left_time+" "+left_time1);
            if(left_time1==left_time){
                //设置paytime和outtime
                //还有用户的freetime
                payService.setTime(nowdate,car_card);
                //获取成date形式本来是datetime形式
                String nowdate1 = nowdate.substring(0,10);
                payService.setFreetime(nowdate1,car_card);
                //将优惠券失效
                String index = request.getParameter("index");
                System.out.println(index);
                //如果为0，就不失效
                if(!index.equals('0')){
                    payService.setIndex(index);
                }
                //数据进入paylist里
                String pay_money =request.getParameter("pay_money");
                payService.addPay(username,pay_money,car_card,car_intime,nowdate);
                //将数据归0
                payService.return0(car_card);
                attributes.addAttribute("option",1);
                return "redirect:pay_self";
            }
        }

        model.addAttribute("userinfo",userinfo);
        model.addAttribute("car_card",car_card);
        model.addAttribute("carUser",carUser);
        model.addAttribute("car_intime",car_intime);
        model.addAttribute("left_timeF",left_timeF);
        model.addAttribute("left_time",left_time);
        model.addAttribute("userRule",userRule);
        model.addAttribute("nowdate",nowdate);
        model.addAttribute("userCard",userCard);
        model.addAttribute("sum_money",sum_money);
        model.addAttribute("hour_money",hour_money);
//        System.out.println(userinfo+" "+car_card+" "+carUser+" "+car_intime+" "+left_timeF+" "+left_time+" "+sum_money);
//        System.out.println(userCard);


        return "pay";

    }

    @RequestMapping("pay_list")
        public String payList(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();
        String currentPage = request.getParameter("currentPage");
        if(currentPage==null){
            currentPage = "1";
        }
        List<UserPaycar> userPaycarList = payService.getAllPaycar(username,currentPage,2);
        model.addAttribute("userPaycarList",userPaycarList);
        int option = 1;
        if(userPaycarList.size()==0){
            option = 0;
        }

        //获取全部
        List<UserPaycar>userPaycarList1 = payService.getAllPaycar1(username);
        //判断长度
        int lengh = userPaycarList1.size();
        System.out.println(lengh);
        if(lengh%2>0){
            lengh = lengh/2;
            lengh++;
        }else {
            lengh = lengh/2;
        }
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("lengh",lengh);
        model.addAttribute("option",option);
        return "pay_list";
        }


    //为他人支付页面
    @RequestMapping("pay_forother")
    public String payForother(HttpServletRequest request,Model model){
        model.addAttribute("option",0);
        return "pay_for_other";
    }
    //展示列表
    @RequestMapping("show_payOther")
    public String showPayOther(HttpServletRequest request,Model model){
        String car_card = request.getParameter("car_card");
        OtherCar otherCar = payService.getOtherCar(car_card);
        if(otherCar==null){
            model.addAttribute("option",0);
        }
        model.addAttribute("otherCar",otherCar);
        return "pay_for_other";
    }

    //规则页面
    @RequestMapping("pay_rule")
        public String payRule(HttpServletRequest request){
        return "pay_rule";
    }
}
