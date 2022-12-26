package com.wty.H5.mapper;

import com.wty.entity.H5entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface H5Mapper {

    @Select("Select id,card_time from tip where id = #{id} and  flag = '1'")
    TipRule getTicket(String id);

    @Select("SELECT u.username,u.phone,COUNT(uc.card_id) as card_count,um.user_mark as mark,ci.car_card,ci.car_intime,r.free_hour\n" +
            ",r.hour_money,ci.flag from user u\n" +
            "LEFT JOIN car c ON c.username = u.username\n" +
            "LEFT JOIN car_in ci ON c.car_card = ci.car_card\n" +
            "LEFT JOIN usermark um ON um.username = u.username\n" +
            "LEFT JOIN rule r ON r.user_type_id = u.user_type_id\n" +
            "LEFT JOIN user_card uc ON uc.username = u.username and uc.flag = '1'\n" +
            "where u.username = 123456 ")
    HomeUser getHome(@Param("date")String date);

    @Update("Update car_in set flag = '0' where car_card = #{car_card}")
    void pay(@Param("car_card") String car_card);

    @Select("Select car_card,car_intime,pay_money from car_pay where username = '123456'")
    List<Car> getCar();

    @Select("Select c.card_id,c.card_time,uc.card_endtime from user_card uc " +
            "left join card c ON c.card_id = uc.card_id " +
            "where uc.username = '123456' and uc.flag ='1' ")
    List<Card> getCard();

    @Select("Select car_card from car where username = '123456' and flag = '1'\n")
    List<Cars> getCarlist();

    @Update("Update car set flag = '0' where car_card = #{car_card}")
    void delCar(@Param("car_card") String car_card);

    @Select("select money from tip where id = #{id} and flag = '1' ")
    String getMoney(@Param("id") String id);

    @Select("select MAX(card_time) as card_time from tipRule where money <= #{money}")
    TipRule getRule(String money);

    @Update("Update tip set flag ='0' where id = #{id}")
    void getFalse(String id);
}
