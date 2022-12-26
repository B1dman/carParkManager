package com.wty.JAVAEE.controller;

import com.wty.JAVAEE.service.CardService;
import com.wty.entity.User;
import com.wty.entity.UserCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CardControl {
    @Autowired
    CardService cardService;

//获取优惠券信息
    @RequestMapping("show_card")
    public String showCard(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();

        //小票列表
        List<UserCard> tipCardList = cardService.showCard(username,"小票抵扣");
        //积分列表
        List<UserCard> markCardList = cardService.showCard(username,"积分兑换");
        //此时有过期的
        model.addAttribute("tipCardList",tipCardList);
        model.addAttribute("markCardList",markCardList);
        return "card";
    }
}
