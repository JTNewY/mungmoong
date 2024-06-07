package com.mypet.mungmoong.users.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mypet.mungmoong.users.dto.Users;
import com.mypet.mungmoong.users.model.Address;
import com.mypet.mungmoong.users.service.AddressService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller("userAddress")
@RequestMapping("/user/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

     /**
     * 배송지 관리
     * @return
     * @throws Exception 
     */
    @GetMapping("")
    public String userOrders(Model model) throws Exception {
        List<Address> addressList = addressService.list();
        log.info("::::::::::::::::::::: 배송지 관리 - user :::::::::::::::::::::}");
        log.info(addressList.toString());

        model.addAttribute("addressList", addressList);

        return "/user/address/index";
    }

    /**
     * 배송지 관리 - 배송지 등록 화면
     * @return
     * @throws Exception
     */
    @GetMapping("/insert")
    public String addressInsert() throws Exception {
        return "/user/address/insert";
    }


    /**
     * 배송지 관리 - 배송지 등록 처리
     * @return
     * @throws Exception
     */
    @PostMapping("/insert")
    public String addressInsert(Address address
                               ,HttpSession session) throws Exception {
        Users user = (Users) session.getAttribute("user");
        address.setUserId(user.getUserId());

        int result = addressService.insert(address);
        if( result > 0 ) {
            return "redirect:/user/address";
        }
        else {
            return "redirect:/user/address/insert?error";
        }
    }


    /**
     * 배송지 관리 - 배송지 수정 화면
     * @param model
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/{id}")
    public String addressUpdate(Model model
                                ,@PathVariable("id") String id) throws Exception {
        Address address = addressService.select(id);
        model.addAttribute("address", address);
        return "/user/address/update";
    }


    /**
     * 배송지 관리 - 배송지 수정 처리
     * @return
     * @throws Exception
     */
    @PostMapping("/update")
    public String addressUpdate(Address address, String isDefault
                               ,HttpSession session) throws Exception {
        Users user = (Users) session.getAttribute("user");
        address.setUserId(user.getUserId());
        String addrssId = address.getId();
        log.info("::::::::::::::::::::: 배송지 관리 - 수정 처리 :::::::::::::::::::::}");
        log.info("::::::::::::::::::::: address :::::::::::::::::::::");
        log.info(address.toString());
        log.info(isDefault);

        int result = addressService.update(address);
        if( result > 0 ) {
            return "redirect:/user/address";
        }
        else {
            return "redirect:/user/address/"+ addrssId +"?error";
        }
    }


    /**
     * 배송지 관리 - 배송지 삭제 처리
     * @param deleteIdList
     * @return
     * @throws Exception
     */
    @PostMapping("/delete")
    public String userAddressDelete(String[] deleteIdList) throws Exception {
        String ids = Arrays.stream(deleteIdList)
                            .map(s -> "'" + s + "'")
                            .collect(Collectors.joining(","));
        log.info("ids : " + ids);
        int result = addressService.delete(ids);

        if( result > 0 ) 
            return "redirect:/user/address";
        else 
            return "redirect:/user/address?error";
        
    }
    
    
}
