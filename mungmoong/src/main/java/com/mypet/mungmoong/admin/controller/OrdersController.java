package com.mypet.mungmoong.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("adminOrders")
@RequestMapping("/admin/orders")
public class OrdersController {

    /**
     * 주문 관리
     * @param param
     * @return
     */
    @GetMapping("")
    public String adminOrders() {
        log.info("::::::::::::::::::::: 주문 관리 :::::::::::::::::::::");
        return "/admin/orders/index";
    }
    
    
}
