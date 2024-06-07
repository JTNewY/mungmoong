package com.mypet.mungmoong.orders.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mypet.mungmoong.board.dto.Board;
import com.mypet.mungmoong.orders.model.Products;
import com.mypet.mungmoong.orders.service.OrdersService;
import com.mypet.mungmoong.orders.service.ProductsService;
import com.mypet.mungmoong.pet.dto.Pet;
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

    @GetMapping("/{id}")
    public String product(Model model
                         ,HttpServletRequest request 
                         ,@PathVariable("id") String id) throws Exception {
        log.info(":::::::::: 내상품 ::::::::::");
        Products product = productsService.select(id);
        HttpSession session = request.getSession();
        List<Pet> pets = (List<Pet>) session.getAttribute("pets");
        model.addAttribute("pets", pets);
        model.addAttribute("product", product);
        return "/products/detail";
    }
     @PostMapping("/{id}")
    public String productPro(Model model
                            ,Products products
                            ,HttpServletRequest request 
                            ,@PathVariable("id") String id) throws Exception {
        // 데이터 요청
        int result = productsService.insert(products);
        // 리다이렉트
        // ⭕ 데이터 처리 성공
        if( result > 0 ) {
            return "redirect:/orders/index";
        }
        // ❌ 데이터 처리 실패
        return "redirect:/orders/index?error";  
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












