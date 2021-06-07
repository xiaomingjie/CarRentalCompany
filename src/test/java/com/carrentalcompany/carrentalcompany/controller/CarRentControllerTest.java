package com.carrentalcompany.carrentalcompany.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.carrentalcompany.controller.CarRentController;
import com.carrentalcompany.model.CarStock;
import com.carrentalcompany.model.RentHistory;
import com.carrentalcompany.service.CarRentService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class CarRentControllerTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private CarRentController carRentController;

    @Mock
    private CarRentService carRentService;

    @Mock
    private Model model;

    @Test
    public void queryCarStockInfoSucceed() {
        Mockito.doReturn(new ArrayList<>()).when(carRentService).queryCarStockInfo();
        String response = carRentController.queryCarStockInfo();
        JSONObject jo = JSON.parseObject(response);
        Assert.assertEquals("1000", jo.getString("respCode"));
    }

    @Test
    public void queryCarStockInfoFailed() {
        Mockito.doReturn(null).when(carRentService).queryCarStockInfo();
        String response = carRentController.queryCarStockInfo();
        JSONObject jo = JSON.parseObject(response);
        Assert.assertEquals("1001", jo.getString("respCode"));
    }

    @Test
    public void rentCarSucceed() {
        Mockito.doReturn(1).when(carRentService).rentCar(Mockito.any(RentHistory.class));
        String res = carRentController.rentCar("132","123", null);
        Assert.assertEquals("redirect:/main", res);
    }

    @Test
    public void returnCarSucceed() {
        Mockito.doReturn(1).when(carRentService).returnCar(Mockito.any(RentHistory.class));
        String res = carRentController.returnCar(new RentHistory(), null);
        Assert.assertEquals("redirect:/main", res);
    }

    @Test
    public void mainSucceed() {
//        List<CarStock> carStockList = new ArrayList<>();
//        List<RentHistory> rentHistoryList = new ArrayList<>();
//        Mockito.doReturn(carStockList).when(carRentService).queryCarStockInfo();
//        Mockito.doReturn(rentHistoryList).when(carRentService).queryCurrentRentStatus(Mockito.anyString());
//        Mockito.doReturn(rentHistoryList).when(carRentService).queryRentHistory(Mockito.anyString());
//        Mockito.doReturn(model).when(model).addAttribute(Mockito.anyString(),Mockito.anyObject());
//        String res = carRentController.main(model);
//        Assert.assertEquals("main", res);
    }
}
