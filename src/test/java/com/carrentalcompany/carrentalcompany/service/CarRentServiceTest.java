package com.carrentalcompany.carrentalcompany.service;

import com.carrentalcompany.dao.CarStockDao;
import com.carrentalcompany.dao.RentDao;
import com.carrentalcompany.dao.RentHistoryDao;
import com.carrentalcompany.model.CarStock;
import com.carrentalcompany.model.RentHistory;
import com.carrentalcompany.service.CarRentServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CarRentServiceTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private CarRentServiceImpl carRentService;

    @Mock
    private CarStockDao carStockDao;

    @Mock
    private RentHistoryDao rentHistoryDao;

    @Mock
    private RentDao rentDao;

    @Before
    public void initMock(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void queryCarStockInfoSucceed() {
        List<CarStock> carStockList = new ArrayList<>();
        carStockList.add(new CarStock());
        Mockito.doReturn(carStockList).when(carStockDao).queryCarStockInfo();
        carRentService.queryCarStockInfo();
        Mockito.verify(carStockDao, Mockito.times(1)).queryCarStockInfo();
    }

    @Test
    public void rentCarSucceed() {
        Mockito.doReturn(1).when(carStockDao).decreaseCarStockByCarModel(Mockito.anyString());
        int res = carStockDao.decreaseCarStockByCarModel(Mockito.anyString());
        Assert.assertEquals(1,res);
        RentHistory rentHistory = new RentHistory();
        rentHistory.setCarModel("123");
        carRentService.rentCar(rentHistory);
        Mockito.verify(rentDao, Mockito.times(1)).rentCar(Mockito.any(RentHistory.class));
    }

    @Test
    public void returnCarSucceed() {
        Mockito.doReturn(1).when(carStockDao).increaseCarStockByCarModel(Mockito.anyString());
        RentHistory rentHistory = new RentHistory();
        rentHistory.setCarModel("123");
        carRentService.returnCar(rentHistory);
        Mockito.verify(rentHistoryDao, Mockito.times(1)).recordReturnCar(Mockito.any(RentHistory.class));
    }

    @Test
    public void queryCurrentRentStatusSucceed() {
        List<RentHistory> rentHistoryList = new ArrayList<>();
        Mockito.doReturn(rentHistoryList).when(rentDao).queryRentStatus(Mockito.anyString());
        carRentService.queryCurrentRentStatus("123");
        Mockito.verify(rentDao, Mockito.times(1)).queryRentStatus(Mockito.anyString());
    }

    @Test
    public void queryRentHistorySucceed() {
        List<RentHistory> rentHistoryList = new ArrayList<>();
        Mockito.doReturn(rentHistoryList).when(rentHistoryDao).queryRentHistory(Mockito.anyString());
        carRentService.queryRentHistory("123");
        Mockito.verify(rentHistoryDao, Mockito.times(1)).queryRentHistory(Mockito.anyString());
    }
}
