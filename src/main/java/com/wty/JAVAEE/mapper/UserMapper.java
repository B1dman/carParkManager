package com.wty.JAVAEE.mapper;

import com.wty.entity.UserCar;
import com.wty.entity.UserRule;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @Update("Update user set phone = #{phone} where username = #{username}")
    void editPhone(@Param("username") String username,
                   @Param("phone") String phone);

    @Select("SELECT ut.max_car_count-COUNT(c.car_card)as car_left_count from user u\n" +
            "LEFT JOIN usertype ut ON ut.user_type_id = u.user_type_id \n" +
            "LEFT JOIN car c ON c.username = u.username and c.flag !='0'\n" +
            "WHERE u.username = #{username} " )
    String getLeftCount(@Param("username") String username);

    @Select("SELECT c.car_card,ci.car_intime,c.flag From car c\n" +
            "LEFT JOIN car_in ci ON ci.car_card = c.car_card and ci.flag = '1'\n" +
            "where c.username = #{username}  and c.flag = '1'")
    List<UserCar> getUserCarList(@Param("username") String username);

    @Update("Update car set flag ='0' where car_card = #{car_card}")
    void delCar(@Param("car_card") String car_card);

    @Insert("Insert into car (car_card,username) value(#{car_card},#{username})")
    void addCar(@Param("car_card") String car_card,
                @Param("username") String username);
//获取会员列表
    @Select("Select ut.max_car_count,r.hour_money,r.free_hour,r.user_type_id  from usertype ut\n" +
            "LEFT JOIN rule r ON r.user_type_id = ut.user_type_id")
    List<UserRule> getRole();
//修改会员
    @Update("Update user \n" +
            "set user_type_id = (SELECT user_type_id from usertype where user_type_name = #{user_type_name})\n" +
            "where username = #{username}")
    void editMember(String username, String user_type_name);

    //积分
    @Select("select user_mark from usermark where username = #{username}")
    String getMark(@Param("username") String username);

    //添加优惠券到用户
    @Insert("INSERT INTO user_card (username,card_id,card_endtime) VALUES (#{username},\n" +
            "(SELECT card_id from card where card_name='积分兑换' and card_time = #{card_time})" +
            ", #{card_endtimeStr})")
    void addCard(@Param("card_time") String card_time,
                 @Param("card_endtimeStr") String card_endtimeStr,
                 @Param("username") String username);

    //减少积分
    @Update("UPDATE usermark SET user_mark = user_mark - #{delMark} where username = #{username}")
    void delMark(@Param("delMark") int delMark,
                 @Param("username") String username);

    @Select("Select user_type_id from usertype \n" +
            "where user_type_name = #{user_type_name}")
    String getIdByname(@Param("user_type_name") String user_type_name);
}
