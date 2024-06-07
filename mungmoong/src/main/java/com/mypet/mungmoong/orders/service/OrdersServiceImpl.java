package com.mypet.mungmoong.orders.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypet.mungmoong.orders.mapper.OrdersMapper;
import com.mypet.mungmoong.orders.model.OrderItems;
import com.mypet.mungmoong.orders.model.Orders;
import com.mypet.mungmoong.orders.model.Products;
import com.mypet.mungmoong.orders.model.Shipments;
import com.mypet.mungmoong.orders.model.ShipmentsStatus;
import com.mypet.mungmoong.users.model.Address;
import com.mypet.mungmoong.users.service.AddressService;
import com.mypet.mungmoong.users.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private ProductsService productsService;

    @Autowired
    private OrderItemsService orderItemsService;

    @Autowired
    private UsersService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ShipmentsService shipmentsService;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private UsersService usersService;

    @Override
    public List<Orders> list() throws Exception {
        List<Orders> orders = ordersMapper.list();
        // for (Orders order : orders) {
        //     Users user = usersService.select(orders.getUserId());
        //     order.setUser(user);
        //     Shipments shipments = shipmentsService.selectByOrdersId(order.getId());
        //     if( shipments != null ) order.setShipments(shipments);
        // }
        return orders;
    }

    @Override
    public Orders select(String id) throws Exception {
        Orders order = ordersMapper.select(id);
        // String userId = order.getUserId();
        // log.info("::::::::::: orders ~ user :::::::::::");
        // log.info(" userId : " + userId);
        // Users user = usersService.select(id);
        // log.info(user.toString());
        // order.setUser(user);
        // log.info("::::::::::: orders ~ shipments :::::::::::");
        // Shipments shipments = shipmentsService.selectByOrdersId(id);
        // if( shipments != null ) order.setShipments(shipments);
        return order;
    }

    @Override
    public int insert(Orders orders) throws Exception {
        List<String> productIdList = orders.getProductId();
        List<Integer> quantityList = orders.getQuantity();
    
        if (productIdList == null || quantityList == null || productIdList.size() != quantityList.size()) {
            return 0;
        }
    
        String orderId = UUID.randomUUID().toString();
        orders.setID(orderId);
    
        if (orders.getUserId() == null || orders.getUserId().isEmpty()) {
            throw new IllegalArgumentException("User ID is required");
        }
        if (orders.getTitle() == null || orders.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (orders.getResDate() == null) {
            throw new IllegalArgumentException("Reservation Date is required");
        }
    
        int totalCount = productIdList.size();
        int totalQuantity = 0;
        int totalPrice = 0;
        String title = "";
    
        List<OrderItems> orderItemList = new ArrayList<>();
        for (int i = 0; i < productIdList.size(); i++) {
            String productId = productIdList.get(i);
            Products product = productsService.select(productId);
            if (i == 0) title = product.getName();
            if (product == null) continue;
            OrderItems orderItem = new OrderItems();
            orderItem.setId(UUID.randomUUID().toString());
            orderItem.setOrdersId(orderId);
            orderItem.setProductsId(productId);
            int quantity = quantityList.get(i);
            int price = product.getPrice();
            int amount = price * quantity;
            totalPrice += amount;
            totalQuantity += quantity;
            orderItem.setQuantity(quantity);
            orderItem.setPrice(price);
            orderItem.setAmount(amount);
            orderItemList.add(orderItem);
        }
        title += " 외 " + totalCount + "종";
    
        orders.setTitle(title);
        orders.setTotalPrice(totalPrice);
        orders.setTotalQuantity(totalQuantity);
        orders.setTotalCount(totalCount);
    
        // 주문 등록
        int result = ordersMapper.insert(orders);
    
        if (result > 0) {
            // 주문 항목 등록
            for (OrderItems orderItems : orderItemList) {
                orderItemsService.insert(orderItems);
            }
            // 배송 정보 등록
            Shipments shipments = new Shipments();
            shipments.setOrderId(orderId);
            shipments.setAddressId(orders.getAddressId());
            shipments.setStatus(ShipmentsStatus.PENDING);
            shipmentsService.insert(shipments);
        }
        return result;
    }


    @Override
    public int update(Orders orders) throws Exception {
        int result = ordersMapper.update(orders);
        return result;
    }

    @Override
    public int delete(String id) throws Exception {
        int result = ordersMapper.delete(id);
        return result;
    }

    @Override
    public List<Orders> listByUserId(String userId) throws Exception {
        List<Orders> orders = ordersMapper.listByUserId(userId);
        // for (Orders order : orders) {
        //     Users user = userService.select(orders.getId());
        //     order.setUser(user);
        //     Shipments shipments = shipmentsService.selectByOrdersId(order.getId());
        //     if( shipments != null ) order.setShipments(shipments);
        // }
        return orders;
    }
    
}
