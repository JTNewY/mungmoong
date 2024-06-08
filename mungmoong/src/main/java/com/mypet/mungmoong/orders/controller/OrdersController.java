package com.mypet.mungmoong.orders.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mypet.mungmoong.orders.model.OrderItems;
import com.mypet.mungmoong.orders.model.Orders;
import com.mypet.mungmoong.orders.model.Payments;
import com.mypet.mungmoong.orders.model.PaymentsStatus;
import com.mypet.mungmoong.orders.model.Shipments;
import com.mypet.mungmoong.orders.service.OrderItemsService;
import com.mypet.mungmoong.orders.service.OrdersService;

import com.mypet.mungmoong.orders.service.PaymentsService;
import com.mypet.mungmoong.orders.service.ShipmentsService;
import com.mypet.mungmoong.users.dto.Users;
import com.mypet.mungmoong.users.model.Address;
import com.mypet.mungmoong.users.service.AddressService;


import lombok.extern.slf4j.Slf4j;


/**
 * TODO: 전체적으로 권한 설정 및 본인 확인 처리 필요
 */
@Slf4j
@Controller("orders")
@RequestMapping("/orders")
public class OrdersController {


    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrderItemsService orderItemsService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ShipmentsService shipmentsService;

    @Autowired
    private PaymentsService paymentsService;


      @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


    /**
     * 주문하기
     * @param param
     * @return
     */
    @GetMapping("")
    public String orders() {


        return "/orders/index";
    }

    @PostMapping("")
    public String orderPost(Orders orders,
                            HttpSession session,
                            @RequestParam List<String> productId,
                            @RequestParam List<Integer> quantity,
                            @RequestParam String title,
                            @RequestParam String resDate,
                            @RequestParam String addressId) throws Exception {
    
        log.info("::::::::: 주문 등록 - orderPost() ::::::::::");
        log.info("productId : " + productId);
        log.info("quantity : " + quantity);
    
        Users user = (Users) session.getAttribute("user");
        orders.setUserId(user.getUserId());
        orders.setProductId(productId);
        orders.setQuantity(quantity);
        orders.setTitle(title);
        orders.setAddressId(addressId); // addressId 설정
    
        // String을 Date로 변환
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(resDate);
        orders.setResDate(date);
    
        // 주문 등록
        int result = ordersService.insert(orders);
        log.info("신규 등록된 주문ID : " + orders.getID());
    
        if (result > 0) {
            return "redirect:/orders/index" + orders.getID();
        } else {
            return "redirect:/orders/index";
        }
    }

    /**
     * 주문 완료
     * @param model
     * @param session
     * @param ordersId
     * @return
     * @throws Exception 
     */
    @GetMapping("/success")
    public String orderSuccess(Model model
                              ,Payments payments
                              ,HttpSession session
                              ,@RequestParam("orderId") String orderId) throws Exception {

        payments.setOrdersId(orderId);
        payments.setStatus(PaymentsStatus.PAID);
        paymentsService.merge(payments);

        Shipments shipments = shipmentsService.selectByOrdersId(orderId);
        log.info(":::::::::::::::::::: 주문 완료 - /order/success ::::::::::::::::::::");
        log.info(":::::::::::::::::::: shipments ::::::::::::::::::::");
        log.info(shipments.toString());
        
        payments = paymentsService.selectByOrdersId(orderId);
        log.info(":::::::::::::::::::: payments ::::::::::::::::::::");
        log.info(payments.toString());

        Orders order = ordersService.select(orderId);
        log.info(":::::::::::::::::::: orders ::::::::::::::::::::");
        log.info(payments.toString());

        model.addAttribute("shipments", shipments);
        model.addAttribute("payments", payments);
        model.addAttribute("order", order);
        return "/orders/success";
    }
    

    /**
     * 주문 실패 
     * @param model
     * @param session
     * @param ordersId
     * @return
     * @throws Exception 
     */
    @GetMapping("/fail")
    public String orderFail(Model model
                              ,Payments payments
                              ,HttpSession session
                              ,@RequestParam("orderId") String orderId
                              ,@ModelAttribute String errorMsg) throws Exception {

        payments.setOrdersId(orderId);
        payments.setStatus(PaymentsStatus.PAID);
        paymentsService.insert(payments);

        Shipments shipments = shipmentsService.selectByOrdersId(orderId);
        log.info(":::::::::::::::::::: 주문 완료 - /order/success ::::::::::::::::::::");
        log.info(":::::::::::::::::::: shipments ::::::::::::::::::::");
        log.info(shipments.toString());
        
        // ⭐ 결제 실패 시, 결제 상태 PENDING 으로 변경
        payments = paymentsService.selectByOrdersId(orderId);
        payments.setStatus(PaymentsStatus.PENDING);
        paymentsService.merge(payments);
        log.info(":::::::::::::::::::: payments ::::::::::::::::::::");
        log.info(payments.toString());

        Orders order = ordersService.select(orderId);
        log.info(":::::::::::::::::::: orders ::::::::::::::::::::");
        log.info(payments.toString());

        log.info("[결제 실패] 에러 메시지 : " + errorMsg);

        model.addAttribute("shipments", shipments);
        model.addAttribute("payments", payments);
        model.addAttribute("order", order);
        return "/orders/fail";
    }
    


    /**
     * 주문/결제  
     * - ➡ 결제하기
     * @param model
     * @param orderId
     * @return
     * @throws Exception
     */
    @GetMapping("/{orderId}")
    public String checkout(Model model
                          ,HttpSession session
                          ,@PathVariable("orderId") String orderId) throws Exception {
        
        // 로그인 사용자
        Users user = (Users) session.getAttribute("user");
        // 주문 정보
        Orders order = ordersService.select(orderId);
        // 주문 항목 정보
        List<OrderItems> orderItems = orderItemsService.listByOrderId(orderId);
        // 기본 배송지
        List<Address> addressList = addressService.listByUserId(user.getUserId());
        if( addressList == null || addressList.size() == 0) {
            return "redirect:/orders/checkout?orderId";
        }
        Address address = addressList.stream().filter((add) -> {return add.getIsDefault();}).findFirst().get();
        log.info("기본 배송지 : " + address.getAddress());
        
        if( order == null ) return "redirect:/orders?error";
        log.info(":::::::::::::::::::: order ::::::::::::::::::::");
        log.info(order.toString());
        log.info(":::::::::::::::::::: order items ::::::::::::::::::::");
        log.info(orderItems.toString());


        model.addAttribute("order", order);
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("address", address);
        return "/orders/checkout";
    }
    


    
    
    
}
