package com.wty.JAVAEE.mapper;

import com.wty.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PayMapper {

    @Select("SELECT ci.car_card,ci.car_intime,ci.car_outtime,ci.car_paytime from user u\n" +
            "LEFT JOIN car c ON c.username = u.username\n" +
            "LEFT JOIN car_in ci ON ci.car_card = c.car_card\n" +
            "where u.username =#{username} and ci.flag = '1'")
    List<UserPaycar> getPaycar(@Param("username") String username);

    @Select("SELECT u.username,u.name,u.phone,u.user_freetime  from car c\n" +
            "LEFT JOIN user u ON u.username = c.username\n" +
            "where car_card = #{car_card}")
    UserInfo getcarUser(@Param("car_card") String car_card);

    @Select("SELECT hour_money,free_hour,user_type_id from rule \n" +
            "where user_type_id = #{user_type_id}")
    UserRule getRule(@Param("user_type_id") String user_type_id);

    @Update("Update car_in set car_outtime = #{nowdate},car_paytime = #{nowdate},flag = 0 " +
            "where car_card = #{car_card}")
    void setTime(@Param("nowdate") String nowdate,
                 @Param("car_card") String car_card);

    @Update("UPDATE user u LEFT JOIN car c ON c.username = u.username\n" +
            "set u.user_freetime= #{nowdate1}\n" +
            "where c.car_card = #{car_card}")
    void setFreetime(@Param("nowdate1") String nowdate1,
                     @Param("car_card") String car_card);

    @Update("Update user_card uc set flag = '0'\n" +
            "where uc.index = #{index}")
    void setIndext(@Param("index") String index);

    @Update("Insert into car_pay (username,car_card,pay_money,car_intime,car_outtime,car_paytime) value(#{username},#{car_card},#{pay_money},#{car_intime},#{car_paytime},#{car_paytime}) ")
    void addPay(@Param("username") String username,
                @Param("pay_money") String pay_money,
                @Param("car_card") String car_card,
                @Param("car_intime") String car_intime,
                @Param("car_paytime") String car_paytime);

    @Select("SELECT username,car_card,car_intime,car_outtime,car_paytime,pay_money from car_pay\n" +
            "where username = #{username}\n" +
            "ORDER BY car_paytime desc " +
            "LIMIT #{pos},#{pageSize}")
    List<UserPaycar> getAllPaycar(@Param("username") String username,
                                  @Param("pos")int pos,
                                  @Param("pageSize")int pageSize);

    @Select("SELECT u.username,c.car_card,u.phone,car_intime from car c \n" +
            "LEFT JOIN user u ON u.username = c.username\n" +
            "LEFT JOIN car_in ci ON ci.car_card = c.car_card \n" +
            "where c.car_card= #{car_card} and ci.flag = '1'")
    OtherCar getOtherCar(@Param("car_card") String car_card);

    @Update("UPDATE car_in set car_outtime = null,car_paytime= null\n" +
            "where car_card = #{car_card}")
    void return0(String car_card);

    @Select("SELECT username,car_card,car_intime,car_outtime,car_paytime,pay_money from car_pay\n" +
            "where username = #{username}\n" +
            "ORDER BY car_paytime desc ")
    List<UserPaycar> getAllPaycar1(String username);
}
