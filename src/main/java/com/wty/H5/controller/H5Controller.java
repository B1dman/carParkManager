package com.wty.H5.controller;

import com.wty.H5.service.H5Service;
import com.wty.entity.H5entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
public class H5Controller {
    @Autowired
    H5Service h5Service;

    //通过小票得tip
    @RequestMapping("get_ticket")
    public TipRule getTicket(HttpServletRequest request){
        String id = request.getParameter("id");
        TipRule Max = null;
        //通过id得到花费钱数
        String money = h5Service.getMoney(id);
        if(money==null){
            return Max;
        }
        System.out.println("测试hot-fix");
        System.out.println("测试2");
        System.out.println("master test");
        //判断money最多能抵多少
        Max = h5Service.getRule(money);
        if(Max!=null){
            h5Service.getFalse(id);
        }
        return Max;
    }

    //首页获取的信息
    @RequestMapping("home")
    public HomeUser Home(HttpServletRequest request){
        String date = request.getParameter("date");
        HomeUser homeUser = h5Service.getHome(date);
        System.out.println(date);
        homeUser.setYear_day(homeUser.getCar_intime().substring(5,7)+"月"+homeUser.getCar_intime().substring(8,10)+"日");
        homeUser.setHour_minutes(homeUser.getCar_intime().substring(11,16));
        return homeUser;
    }

    //将车辆变为支付完的状态
    @RequestMapping("pay")
    public String pay(HttpServletRequest request){
        String car_card = request.getParameter("car_card");
        h5Service.pay(car_card);
        System.out.println(car_card);
        return "1";
    }

    //获取car列表
    @RequestMapping("car")
    public List<Car> car(HttpServletRequest request){
        List<Car> carList = h5Service.getCar();
        return carList;
    }
    //获取card列表
    @RequestMapping("card")
    public List<Card> card(HttpServletRequest request){
        List<Card> cardList = h5Service.getCard();
        return cardList;
    }

    //获取车辆列表
    @RequestMapping("carlist")
    public List<Cars> carlist(HttpServletRequest request){
        List<Cars> carlist1 = h5Service.getCarlist();
        return carlist1;
    }
    @RequestMapping("delCar")
    public String delCars(HttpServletRequest request){
        String car_card = request.getParameter("car_card");
        h5Service.delCar(car_card);
        System.out.println("完成");
        return "1";
    }
}
