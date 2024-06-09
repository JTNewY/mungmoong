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

import com.mypet.mungmoong.orders.dto.Orders;
import com.mypet.mungmoong.orders.service.OrdersService;
import com.mypet.mungmoong.pet.dto.Pet;
import com.mypet.mungmoong.pet.service.PetService;
import com.mypet.mungmoong.trainer.dto.Trainer;
import com.mypet.mungmoong.trainer.service.TrainerService;
import com.mypet.mungmoong.users.dto.Users;
import com.mypet.mungmoong.users.service.UsersService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;



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
    private UsersService usersService;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private PetService petService;



    // ALOHA : 무엇?
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


    /**
     * 결제 준비
     * @param order
     * @param session
     * @return
     * @throws Exception
     */
    @GetMapping("")
    public String orders(Orders order
                        ,HttpSession session) throws Exception {
        log.info("resDate - 예약일자 : " + order.getRegDate());
        log.info("address - 주소 : " + order.getAddress());
        log.info("memo - 요청사항 : " + order.getAddress());
        log.info("productId - 상품ID : " + order.getProductId());
        Users user = (Users) session.getAttribute("user");
        order.setUserId(user.getUserId());
        int result = ordersService.insert(order);
        
        if( result > 0 ) {
            log.info("등록된 orderNo : " + order.getNo());
            return "redirect:/orders/" + order.getNo();
        }
        else {
            return "/products/" + order.getProductId();
        }
    }


    /**
     * 결제 화면
     * @param orderNo
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("{orderNo}")
    public String orderPay(@PathVariable("orderNo") int orderNo
                          ,Model model) throws Exception {

        Orders order = ordersService.select(orderNo);

        log.info("::::::::::::::: 주문자 정보 (users) ::::::::::::");
        String userId = order.getUserId();
        Users user = usersService.select(userId);
        order.setUser(user);
        log.info(user.toString());
        
        log.info("::::::::::::::: 훈련사 정보 (trainer) ::::::::::::");
        int trainerNo = order.getTrainerNo();
        Trainer trainer = trainerService.selectByNo(trainerNo);
        String trainerUserId = trainer.getUserId();
        Users trainerUser = usersService.select(trainerUserId);
        trainer.setUser(trainerUser);
        log.info(trainer.toString());
        log.info(trainerUser.toString());

        // 펫 리스트 조회
        List<Pet> petList = petService.findPetByUserId(user.getUserId());
        log.info("petList : " + petList);
        model.addAttribute("petList", petList);

        model.addAttribute("user", user);
        model.addAttribute("trainer", trainer);
        model.addAttribute("order", order);

        return "/orders/checkout";
    }
    
    /**
     * 결제 성공 처리
     * @return
     * @throws Exception 
     */
    @GetMapping("/success")
    public String orderSuccess(Orders order, Model model) throws Exception {
        log.info("::::::::::::::::::::: 결제 성공 ::::::::::::::::::::::::::");
        log.info("order : " + order);
        
        int orderNo = order.getNo();
        int petNo = order.getPetNo();
        String status = "paid";         // 결제완료
        order = ordersService.select(orderNo);
        order.setPetNo(petNo);
        order.setStatus(status);
        int result = ordersService.update(order);
        if( result > 0 ) {
            log.info("결제 성공 후 주문 데이터 수정 성공!");
        }
        // 결제 데이터 추가

        return "redirect:/orders/success/" + orderNo;
    }

    /**
     * 결제 성공 화면
     * @param orderNo
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/success/{orderNo}")
    public String getMethodName(@PathVariable("orderNo") int orderNo, Model model) throws Exception {
        Orders order = ordersService.select(orderNo);
        model.addAttribute("order", order);
        return "/orders/success";
    }
    

    /**
     * 결제 실패 화면
     * @return
     * @throws Exception 
     */
    @GetMapping("/fail")
    public String orderFail(@RequestParam("no") int no, Model model) throws Exception {
        log.info("::::::::::::::::::::: 결제 실패 ::::::::::::::::::::::::::");
        model.addAttribute("no", no);
        return "/orders/fail";
    }

    
    
}
