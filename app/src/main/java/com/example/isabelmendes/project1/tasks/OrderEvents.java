package com.example.isabelmendes.project1.tasks;

import java.util.List;
import com.example.isabelmendes.project1.models.Order;
import com.example.isabelmendes.project1.webservice.WebServiceResponse;

public interface OrderEvents {

    void getOrdersFinished(List<Order> orders);
    void getOrdersFailed(WebServiceResponse webServiceResponse);
    void getOrderByIdFinished(Order order);
    void getOrderByIdFailed(WebServiceResponse webServiceResponse);
}
