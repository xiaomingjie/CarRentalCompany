package com.carrentalcompany.dao;

import com.carrentalcompany.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Insert("insert into user values(#{userId},#{password})")
    void register(User user);

    @Select(value = "select * from user where userId=#{userId}")
    User findUserById(String userId);
}
