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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


    @GetMapping("/reply")
    public ResponseEntity<Integer> replyread(Reply reply
                                             , HttpSession session
                                            ,@PathVariable("id") String id) throws Exception {
      // int result = replyService.insert(reply);
      log.info(":::::::::: 댓글 ::::::::::");
      log.info(reply.toString());
    //    if(result>0){
    //     log.info("덧글 등록 성공");
    //    }
    //     log.info(reply.toString());



        return null;       
    }
    @PostMapping("/reply/{id}")
    public ResponseEntity<String> replyInsert(@RequestBody Reply reply
                                             , HttpSession session
                                            ,@PathVariable("id") String id) throws Exception {
      // int result = replyService.insert(reply);
      log.info(":::::::::: 댓글 ::::::::::");
      log.info(reply.toString());
     // reply.setBoardNo(id); // 댓글 객체의 boardNo 필드에 상품 ID 설정
     // boardNo은 Int타입이고 productNo은 String타입이다 형변환을 하였다
            try {
                int boardNo = Integer.parseInt(id);
                reply.setBoardNo(boardNo);
            } catch (NumberFormatException e) {
                // 형변환 실패 시 기본값으로 설정 또는 예외 처리
                // 예를 들어, 기본값으로 -1을 설정할 수 있습니다.
                reply.setBoardNo(-1);
            }
      int result = replyService.insert(reply);
      
      if (result > 0) {
          return ResponseEntity.ok("SUCCESS");
      } else {
          return ResponseEntity.status(500).body("FAILURE");
      }
       // return null;       
    }
    












