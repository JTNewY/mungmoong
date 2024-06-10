package com.mypet.mungmoong.orders.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mypet.mungmoong.board.controller.ReplyController;
import com.mypet.mungmoong.board.dto.Reply;
import com.mypet.mungmoong.board.service.ReplyService;
import com.mypet.mungmoong.orders.dto.Products;
import com.mypet.mungmoong.orders.service.OrdersService;
import com.mypet.mungmoong.orders.service.ProductsService;
import com.mypet.mungmoong.pet.service.PetService;
import com.mypet.mungmoong.users.dto.Users;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




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

    @Autowired
    private ReplyService replyService;
    
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
        Products product = productsService.select(id);
        

        log.info(product.toString());
        model.addAttribute("product", product);
        
        return "/products/detail";
    }


    @PostMapping("/reply")
    public ResponseEntity<Integer> replyInsert(Reply reply, HttpSession session,@PathVariable("id") String id) throws Exception {
       int result = replyService.insert(reply);

       if(result>0){
        log.info("덧글 등록 성공");
       }
        log.info(reply.toString());
        // int starNo = payload.get("starNo");


        return null;       
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












