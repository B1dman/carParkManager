package com.wty.JAVAEE.service;

import com.wty.JAVAEE.mapper.CardMapper;
import com.wty.entity.UserCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CardService {
    @Autowired
    CardMapper cardMapper;

    //优惠券列表
    public List<UserCard> showCard(String username, String card_name) {
        return cardMapper.showCard(username,card_name);
    }

    public List<UserCard> getlist(String username ,String nowdate) {
        return cardMapper.getlist(username,nowdate);
    }
}
