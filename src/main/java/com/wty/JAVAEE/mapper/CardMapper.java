package com.wty.JAVAEE.mapper;

import com.wty.entity.UserCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface  CardMapper {
    //优惠券列表
    @Select("SELECT uc.index,u.username,c.card_time,uc.card_endtime from user u \n" +
            "LEFT JOIN user_card uc ON uc.username = u.username\n" +
            "LEFT JOIN card c ON c.card_id = uc.card_id\n" +
            "WHERE u.username = #{username} and c.card_name = #{card_name} and uc.flag!='0'" +
            "order by uc.card_endtime desc")
    List<UserCard> showCard(@Param("username") String username,
                            @Param("card_name") String card_name);

    @Select("SELECT uc.index,u.username,c.card_name,c.card_time,uc.card_endtime from user u \n" +
            "LEFT JOIN user_card uc ON uc.username = u.username\n" +
            "LEFT JOIN card c ON c.card_id = uc.card_id\n" +
            "WHERE u.username = #{username} and uc.card_endtime >= #{nowdate} and uc.flag!='0'")
    List<UserCard> getlist(@Param("username") String username,
                           @Param("nowdate") String nowdate);
}
