package com.carrentalcompany.service;

import com.carrentalcompany.dao.CarStockDao;
import com.carrentalcompany.dao.RentDao;
import com.carrentalcompany.dao.RentHistoryDao;
import com.carrentalcompany.model.CarStock;
import com.carrentalcompany.model.RentHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CarRentServiceImpl implements CarRentService {

    public static final Logger logger = Logger.getLogger("CarRentServiceImpl");

    @Autowired
    private CarStockDao carStockDao;

    @Autowired
    private RentHistoryDao rentHistoryDao;

    @Autowired
    private RentDao rentDao;

    @Override
    public List<CarStock> queryCarStockInfo() {
        return carStockDao.queryCarStockInfo();
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public Integer rentCar(RentHistory rentHistory) {
        int res = carStockDao.decreaseCarStockByCarModel(rentHistory.getCarModel());
        if (res == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        rentDao.rentCar(rentHistory);
        rentHistoryDao.recordRentCar(rentHistory);
        return 1;
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public Integer returnCar(RentHistory rentHistory) {
        Integer res = carStockDao.increaseCarStockByCarModel(rentHistory.getCarModel());
        rentDao.returnCar(rentHistory);
        rentHistory.setId(null);
        rentHistoryDao.recordReturnCar(rentHistory);
        return 1;
    }

    @Override
    public List<RentHistory> queryCurrentRentStatus(String userId) {
        return rentDao.queryRentStatus(userId);
    }

    @Override
    public List<RentHistory> queryRentHistory(String userId) {
        return rentHistoryDao.queryRentHistory(userId);
    }
}
