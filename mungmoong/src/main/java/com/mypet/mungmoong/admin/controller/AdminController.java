package com.mypet.mungmoong.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mypet.mungmoong.QnA.dto.QnA;
import com.mypet.mungmoong.QnA.service.QnAService;
import com.mypet.mungmoong.board.dto.Board;
import com.mypet.mungmoong.board.service.BoardService;
import com.mypet.mungmoong.orders.dto.Orders;
import com.mypet.mungmoong.orders.dto.Products;
import com.mypet.mungmoong.orders.service.OrdersService;
import com.mypet.mungmoong.orders.service.ProductsService;
import com.mypet.mungmoong.pet.dto.Pet;
import com.mypet.mungmoong.pet.service.PetService;
import com.mypet.mungmoong.trainer.dto.Option;
import com.mypet.mungmoong.trainer.dto.Page;
import com.mypet.mungmoong.trainer.dto.Trainer;
import com.mypet.mungmoong.trainer.service.TrainerService;
import com.mypet.mungmoong.users.dto.Users;
import com.mypet.mungmoong.users.service.UsersService;

import lombok.extern.slf4j.Slf4j;




@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsersService userService;

    @Autowired
    private PetService petService;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ProductsService productsService;

    @Autowired
    private QnAService qnaService;

    /**
     * 관리자 회원정보 조회
     * 
     * @return
     * @throws Exception
     */
    @GetMapping("/admin_info")
    public String list(Model model, Page page, Option option) throws Exception {

        List<Users> usersList = userService.list( page, option );

        log.info("Users page 로그 : " + page);
        // log.info(usersList.toString());
        model.addAttribute("usersList", usersList);

        return "/admin/admin_info";
    }

    /**
     * 관리자 회원정보 상세 페이지
     * 
     * @param userId
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/admin_info_read")
    public String read(@RequestParam("userId") String userId, Model model) throws Exception {
        Users users = userService.select(userId);
        model.addAttribute("users", users);
        return "/admin/admin_info_read";
    }

    /**
     * 관리자 회원정보 수정 화면
     * 
     * @param userId
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/admin_info_read_update")
    public String update(@RequestParam("userId") String userId, Model model) throws Exception {

        // 데이터 요청
        Users users = userService.select(userId);

        // 모델 등록
        model.addAttribute("users", users);

        return "/admin/admin_info_read_update";
    }

    /**
     * 게시글 수정 처리
     * 
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/admin_info_read_update")
    public String updatePro(Users user) throws Exception {
        int result = userService.update(user);

        if (result > 0) {
            return "redirect:/admin/admin_info";
        }
        String userId = user.getUserId();
        return "redirect:/admin/admin_info_read_update?userId=" + userId + "&error";
    }

    /**
     * 관리자 회원 반려견 정보
     * @param petNo
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/admin_info_read_pet")
    public String read(HttpSession session, Model model) throws Exception {
        Users loginUser = (Users) session.getAttribute("user");
        String userId = loginUser.getUserId();

        List<Pet> petList = petService.findPetByUserId(userId);

        model.addAttribute("petList", petList);
        return "/admin/admin_info_read_pet";
    }

    /**
     * 관리자 훈련사 정보 삭제
     * 
     * @param userId
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping("/delete")
    public String delete(@RequestParam("userId") String userId) throws Exception {
        log.info("userId : " + userId);
        int result = userService.delete(userId);

        if (result > 0) {
            return "redirect:/admin/admin_info";
        }

        return "redirect:/admin/admin_info_read_update?userId=" + userId + "&error";
    }



    /**
     * 관리자 훈련사 
     * @param no
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/admin_trainer")
    public String trainerList(Model model, Page page, Option option) throws Exception {


        List<Trainer> trainerList = trainerService.adminTrainerList(page, option);
        log.info("트레이너 " + trainerList.toString());


        model.addAttribute("trainerList", trainerList);

        return "/admin/admin_trainer";
    }

    /**
     * 관리자 훈련사정보 상세 페이지(이동)
     * @param userId
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/admin_trainer_read")
    public String select(@RequestParam("userId") String userId, Model model) throws Exception {
        Trainer trainer = trainerService.select(userId);
        // log.info("트레이너 상세화면 : " + trainer.toString());
        model.addAttribute("trainer", trainer);
        return "/admin/admin_trainer_read";
    }

    /**
     * 관리자 훈련사정보 수정페이지(이동)
     * @param param
     * @return
     */
    // @GetMapping("path")
    // public String getMethodName(@RequestParam String param) {
    //     return new String();
    // }

    /**
     * 관리자 훈련사정보 수정(처리)
     * @param entity
     * @return
     */
    // @PostMapping("path")
    // public String postMethodName(@RequestBody String entity) {
        
    //     return entity;
    // }
    
    /**
     * 관리자 훈련사정보 삭제
     * @param entity
     * @return
     * @throws Exception 
     */
    // @PostMapping("path")
    // public String postMethodName(@RequestBody String entity) {
        
    //     return entity;
    // }
    
    /**
     * 관리자 보드 리스트
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/admin_board")
    public String getMethodName(Model model, Page page, Option option) throws Exception {

        List<Board> boardList = boardService.list(page, option);

        log.info("Board page 로그 : " + page);
        model.addAttribute("boardList", boardList);
        return "/admin/admin_board";
    }

    @GetMapping("/admin_board_notice")
    public String board_notice(Model model, Page page, Option option) throws Exception {

        List<QnA> qnaList = qnaService.list(page, option);

        log.info("QnA page 로그 : " + page);
        model.addAttribute("qnaList", qnaList);
        return "/admin/admin_board_notice";
    }


    /**
     * 관리자 게시판 상세 화면
     * @param param
     * @return
     * @throws Exception 
     */
    @GetMapping("/admin_board_read")
    public String read(@RequestParam("no") int no, Model model) throws Exception {
        Board board = boardService.select(no);
        log.info("보드 : " + board.toString());
        model.addAttribute("board", board);
        return "/admin/admin_board_read";
    }


    /**
     * 관리자 게시판 수정화면(이동)
     * @param no
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/admin_board_read_update")
    public String update(@RequestParam("no") int no, Model model) throws Exception {
        Board board = boardService.select(no);

        log.info("업데이트 보드 : " + board);

        model.addAttribute("board", board);


        return "/admin/admin_board_read_update";
    
    }


    /**
     * 관리자 공지사항 수정 처리
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping("/admin_board_read_update")
    public String BupdatePro(Board board) throws Exception {

        int result = boardService.update(board);

        log.info("result : " + result);
        
        if(result > 0) {
            return "redirect:/admin/admin_board";
        }
        int no = board.getNo();
        return "redirect:/admin/admin_board_read_update?no=" + no + "&error";
    }

    /**
     * 관리자 게시판 삭제 처리
     * @param no
     * @return
     * @throws Exception
     */
    @PostMapping("/BoardDelete")
    public String BoardDelete(@RequestParam("no") int no) throws Exception {
        log.info("no : " + no);
        int result = boardService.BoardDelete(no);

        if (result > 0) {
            return "redirect:/admin/admin_board";
        }

        return "redirect:/admin/admin_board_read_update?no=" + no + "&error";
    }

    @PostMapping("/ListBoardDelete")
    public String ListBoardDelete(@RequestParam("no") int no) throws Exception {
        log.info("no : " + no);
        int result = boardService.BoardDelete(no);

        if (result > 0) {
            log.info(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
            log.info("삭제됨");
            return "redirect:/admin/admin_board";
            }
            
            log.info("삭제안됨");
        return "redirect:/admin/admin_board";
    }
    
    
    /**
     * 관리자 예약관리 화면
     * @param model
     * @return
     * @throws Exception
     */
    // 사용자별 총 예약 금액을 보여주는 관리자 예약 목록
    @GetMapping("/admin_reserve")
    public String ReserveList(Model model) throws Exception {

        List<Orders> ordersList = ordersService.list();

        model.addAttribute("ordersList", ordersList);
        
        return "/admin/admin_reserve";
    }


    @GetMapping("/admin_reserve_pay")
    public String ReservePayList(Model model) throws Exception {

        List<Orders> ordersList = ordersService.list();

        model.addAttribute("ordersList", ordersList);
        
        return "/admin/admin_reserve_pay";
    }

    /**
     * 관리자 훈련사 권한 승인 처리
     * @param user
     * @param trainer
     * @return
     * @throws Exception
     */
    @PostMapping("/admin_trainer_role")
    public String TrainerRole(Users user, Trainer trainer) throws Exception {

        int result = userService.roleUp(user);

        if(result > 0) {
            return "redirect:/admin/admin_trainer";
        }
        int no = trainer.getNo();
        return "redirect:/admin/admin_trainer?no=" + no + "&error";
    }



    // @PostMapping("/admin/checkDelete")
    // public String BoardCheckDelete(int no) {

        
        
    //     return entity;
    // }
    
    /**
     * 훈련사 상품 등록 화면
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/admin_product")
    public String AdminProduct(Model model) throws Exception {

        List<Products> productsList = productsService.list();

        model.addAttribute("productsList", productsList);
        
        return "/admin/admin_product";
    }

    @GetMapping("/admin_product_insert")
    public String AdminProductInsert() throws Exception {

        return "/admin/admin_product_insert";

    }

    /**
     * 훈련사 상품 등록 처리
     * @param products
     * @return
     * @throws Exception
     */
    @PostMapping("/admin_product_insert")
    public String AdminProductInsertPro(Products products) throws Exception {

        int result = productsService.adminInsert(products);

        if ( result > 0 ) {
            return "redirect:/admin/admin_product";
        }
        
        return "redirect:/admin/admin_product_insert?&error";
    }

    /**
     * 관리자 상품정보 상세 화면
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/admin_product_read")
    public String AdminProductRead(@RequestParam("id") String id, Model model) throws Exception {

        Products products = productsService.select(id);

        model.addAttribute("products", products);

        return "/admin/admin_product_read";

    }


    /**
     * 관리자 상품정보 수정 화면
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/admin_product_update")
    public String AdminProductUpdate(@RequestParam("id") String id, Model model) throws Exception {

        Products products = productsService.select(id);

        model.addAttribute("products", products);

        return "/admin/admin_product_update";

    }


    /**
     * 관리사 상품정보 수정 처리 화면
     * @param entity
     * @return
     * @throws Exception 
     */
    @PostMapping("/admin_product_update")
    public String AdminProductUpdatePro(@RequestParam("id") String id , Products products) throws Exception {

        int result = productsService.adminUpdate(products);

        if ( result > 0 ) {
            return "redirect:/admin/admin_product";
        }
        
        return "redirect:/admin/admin_ product_update?id" + id + "&error";
    }
    
    
    @PostMapping("/admin_product_delete")
    public String ProductsDelete(@RequestParam("id") String id) throws Exception {

        int result = productsService.adminDelete(id);

        if( result > 0 ) {
            return "redirect:/admin/admin_product";
        }

        
        return "redirect:/admin/admin_product_update?=id" + id + "&error";
        
    }


    @PostMapping("/admin_orders_status")
    public String AdminProductStatus(Orders orders) throws Exception {

        int result = ordersService.Status(orders);

        if( result > 0 ) {
            return "redirect:/admin/admin_reserve_pay";
        }
        
        int no = orders.getNo();
        return "redirect:/admin/admin_orders?no=" + no + "&error";
    }
    

    
    

}
