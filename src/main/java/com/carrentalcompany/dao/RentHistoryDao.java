package com.carrentalcompany.dao;

import com.carrentalcompany.model.RentHistory;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface RentHistoryDao {

    @Select(value = "select * from rent_history where userId=#{userId} order by id desc")
    List<RentHistory> queryRentHistory(String userId);

    @Insert(value = "insert into rent_history(id, userId, operation, date, carModel) values(#{id}, #{userId}, 2, #{date}, #{carModel})")
    Integer recordReturnCar(RentHistory rentHistory);

    @Insert(value = "insert into rent_history(id, userId, operation, date, carModel) values(#{id}, #{userId}, 1, #{date}, #{carModel})")
    Integer recordRentCar(RentHistory rentHistory);
}
