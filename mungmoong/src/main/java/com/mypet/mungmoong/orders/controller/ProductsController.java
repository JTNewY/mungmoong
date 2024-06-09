package com.mypet.mungmoong.orders.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mypet.mungmoong.orders.dto.Products;
import com.mypet.mungmoong.orders.service.OrdersService;
import com.mypet.mungmoong.orders.service.ProductsService;
import com.mypet.mungmoong.pet.service.PetService;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller("products")
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @Autowired
    private PetService petService;

    @Autowired
    private OrdersService ordersService;

    
      @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

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

    /**
     * 상품 상세
     * @param model
     * @param request
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/{id}")
    public String product(Model model
                         ,HttpSession session
                         ,@PathVariable("id") String id) throws Exception {
        log.info(":::::::::: /product/detail ::::::::::");
        Products product = productsService.select(id);
        model.addAttribute("product", product);
        
        return "/products/detail";
    }

    // @GetMapping("/{id}")
    // public String productPro(Model model
    //                      ,@PathVariable("id") int id) throws Exception {
    //     log.info(":::::::::: 상품 ::::::::::");
    //     Products product = productsService.selectPro(id);
    //     model.addAttribute("product", product);
    //     return "/products/detail";
    // }
    
    
}












