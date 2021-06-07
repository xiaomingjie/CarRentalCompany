package com.carrentalcompany.dao;

import com.carrentalcompany.model.CarStock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CarStockDao {

    @Select(value = "select * from rentable_car")
    List<CarStock> queryCarStockInfo();

    @Update(value = "update rentable_car set inStock = inStock + 1 where CarModel = #{CarModel}")
    Integer increaseCarStockByCarModel(String CarModel);

    @Update(value = "update rentable_car set inStock = inStock - 1 where CarModel = #{CarModel} and inStock >= 1")
    Integer decreaseCarStockByCarModel(String CarModel);
}
