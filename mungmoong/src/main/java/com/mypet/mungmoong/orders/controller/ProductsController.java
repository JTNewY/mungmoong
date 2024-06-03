package com.mypet.mungmoong.orders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mypet.mungmoong.orders.model.Products;
import com.mypet.mungmoong.orders.service.ProductsService;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller("products")
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    /**
     * 상품 목록
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("")
    public String products(Model model) throws Exception {
        log.info(":::::::::: 상품 ::::::::::");
        List<Products> products = productsService.list(); 
        log.info(":::::::::: products ::::::::::");
        log.info("products : " + products);
        model.addAttribute("products", products);

        return "/products/index";
    }

    @GetMapping("/{id}")
    public String product(Model model
                         ,@PathVariable("id") String id) throws Exception {
        log.info(":::::::::: 상품 ::::::::::");
        Products product = productsService.select(id);
        model.addAttribute("product", product);
        return "/products/detail";
    }
    
    
}












