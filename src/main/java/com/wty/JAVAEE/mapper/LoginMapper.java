package com.wty.JAVAEE.mapper;

import com.wty.entity.User;
import com.wty.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginMapper {

//登录
    @Select("select u.username,u.password,ut.user_type_name,ut.user_type_id from user u \n" +
            "LEFT JOIN usertype ut ON ut.user_type_id = u.user_type_id \n" +
            "where u.username=#{username} and u.password = #{password} and u.flag = '1'")
    User login(@Param("username") String username,
               @Param("password") String password);

//获取用户信息
    @Select("SELECT u.username,u.name,ut.user_type_name,COUNT(c.car_card) as car_count,u.user_freetime,u.phone from user u \n" +
            "LEFT JOIN car c ON c.username = u.username and c.flag!='0'\n" +
            "LEFT JOIN usertype ut ON ut.user_type_id = u.user_type_id\n" +
            "WHERE u.username = #{username} and u.flag = '1' ")
    UserInfo getuser(@Param("username") String username);


}
