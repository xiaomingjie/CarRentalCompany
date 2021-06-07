package com.carrentalcompany.dao;

import com.carrentalcompany.model.RentHistory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RentDao {
    @Select(value = "select * from rent where userId=#{userId} order by id desc")
    List<RentHistory> queryRentStatus(String userId);

    @Insert(value = "insert into rent(id, carModel, userId, date) values(#{id}, #{carModel}, #{userId}, #{date})")
    Integer rentCar(RentHistory rentHistory);

    @Delete(value = "delete from rent where id=#{id}")
    Integer returnCar(RentHistory rentHistory);
}
