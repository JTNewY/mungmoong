package com.mypet.mungmoong.orders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mypet.mungmoong.orders.dto.Orders;
import com.mypet.mungmoong.orders.dto.OrdersDetail;
import com.mypet.mungmoong.orders.service.OrdersDetailService;
import com.mypet.mungmoong.orders.service.OrdersService;
import com.mypet.mungmoong.trainer.service.TrainerService;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired                 
    private OrdersService ordersService;
    @Autowired                 
    private OrdersDetailService ordersDetailService;
    // @Autowired                 
    // private TrainerService trainerService;
    /**
     * 결제목록 조회 화면
     */
    @GetMapping("/list")
    public String list(Model model)throws Exception {
        //데이터 요청
        List<Orders> ordersList = ordersService.list();
        //데이터 등록
        model.addAttribute("ordersService", ordersService);
        //뷰페이지 등록
        return "/orders/list"; 
    }
    /** */
      @GetMapping("/orders")
      public String insert() {
        return "/orders/orders";
    }
    /**
     * 예약 등록 처리
     * @param entity
     * @return
     * @throws Exception 
     */
    @PostMapping("/orders")
    public String insertPro(Orders orders) throws Exception {
        //데이터 요청
        int result = ordersService.insert(orders);
        //리다이렉트
        //⭕데이터 처리 성공
        if (result > 0) {
            return "redirect:/orders/list";
        }
        //❌데이터 처리 실패
        return "redirect:/orders/orders?error";
    }
    /**
     * 예약 조회 화면
     */
    @GetMapping("/ordersdetail")
    public String read(@RequestParam("order_no") int order_no, Model model) throws Exception {
        log.info("order_no : " + order_no);
        // 데이터 요청
        Orders orders = ordersService.select(order_no);
        log.info("orders : " + orders);
        int orderId = orders.getOrderId();
        OrdersDetail ordersDetail = ordersDetailService.select(orderId);
        log.info("ordersDetail : " + ordersDetail);

        
        // 모델 등록
        model.addAttribute("orders", orders);
        model.addAttribute("ordersDetail", ordersDetail);
        // 뷰페이지 지정
        return "/orders/ordersdetail";
    }


}

