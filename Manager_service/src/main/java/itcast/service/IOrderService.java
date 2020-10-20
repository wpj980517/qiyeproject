package itcast.service;

import itcast.domain.Orders;

import java.util.List;

public interface IOrderService {
    List<Orders> findAllOrders(int page,int size);

    Orders findById(String id);
}
