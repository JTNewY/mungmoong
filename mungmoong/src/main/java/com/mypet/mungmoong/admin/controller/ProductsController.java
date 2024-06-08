package com.mypet.mungmoong.admin.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mypet.mungmoong.orders.dto.Products;
import com.mypet.mungmoong.orders.service.ProductsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("adminProducts")
@RequestMapping("/admin/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    
    /**
     * 상품 관리
     * @param param
     * @return
     * @throws Exception 
     */
    @GetMapping("")
    public String adminProducts(Model model) throws Exception {
        List<Products> products = productsService.list(); 
        log.info(":::::::::: adminProducts ::::::::::");
        log.info("products : " + products);
        model.addAttribute("products", products);
        return "/admin/products/index";
    }
    
    /**
     * 상품 관리 - 등록 화면
     * @return
     * @throws Exception 
     */
    @GetMapping("/insert")
    public String adminProductsInsert(Model model) throws Exception {
        return "/admin/products/insert";
    }

    /**
     * 상품 관리 - 등록 처리
     * @param entity
     * @return
     * @throws Exception 
     */
    @PostMapping("/insert")
    public String adminProductsInsertPro(Products products) throws Exception {
        log.info(":::::::::: 상품 관리 - 등록 처리 ::::::::::");
        log.info(":::::::::: products ::::::::::");
        log.info("products : " + products);
        int result = productsService.insert(products);
        if( result > 0 ) 
            return "redirect:/admin/products";
        else 
            return "redirect:/admin/products/insert?id=" + products.getId() + "&error";
    }
    


    /**
     * 상품 관리 - 수정 화면
     * @param model
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/{id}")
    public String adminProducts(Model model, @PathVariable("id") String id) throws Exception {
        Products product = productsService.select(id); 
        log.info("product: " + product);
        model.addAttribute("product", product);
        return "/admin/products/update";
    }


    /**
     * 상품 관리 - 수정 처리
     * @param products
     * @return
     * @throws Exception
     */
    @PostMapping("/update")
    public String adminProductsUpdate(Products products) throws Exception {
        int result = productsService.update(products);
        if( result > 0 ) 
            return "redirect:/admin/products";
        else 
            return "redirect:/admin/products/update?id=" + products.getId() + "&error";
    }

    /**
     * 상품 관리 - 삭제 처리
     * @param deleteIdList
     * @return
     * @throws Exception
     */
    @PostMapping("/delete")
    public String adminProductsDelete(String[] deleteIdList) throws Exception {
        String ids = Arrays.stream(deleteIdList)
                            .map(s -> "'" + s + "'")
                            .collect(Collectors.joining(","));
        log.info("ids : " + ids);
        int result = productsService.delete(ids);

        if( result > 0 ) 
            return "redirect:/admin/products";
        else 
            return "redirect:/admin/products?error";
        
    }
    
}
