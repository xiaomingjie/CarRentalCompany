package com.carrentalcompany.service;

import com.carrentalcompany.model.CarStock;
import com.carrentalcompany.model.RentHistory;

import java.util.List;

public interface CarRentService {
    List<CarStock> queryCarStockInfo();
    List<RentHistory> queryCurrentRentStatus(String userId);
    List<RentHistory> queryRentHistory(String userId);
    Integer rentCar(RentHistory rentHistory);
    Integer returnCar(RentHistory rentHistory);
}
