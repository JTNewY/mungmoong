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

import com.mypet.mungmoong.orders.model.Categories;
import com.mypet.mungmoong.orders.service.CategoriesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/categories")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;
    
    /**
     * 카테고리 관리
     * @param param
     * @return
     * @throws Exception 
     */
    @GetMapping("")
    public String adminCategories(Model model) throws Exception {
        List<Categories> categories = categoriesService.list(); 
        model.addAttribute("categories", categories);
        return "/admin/categories/index";
    }
    
    /**
     * 카테고리 관리 - 등록 화면
     * @return
     */
    @GetMapping("/insert")
    public String adminCategoriesInsert() {
     
        return "/admin/categories/insert";
    }

    /**
     * 카테고리 관리 - 등록 화면
     * @param entity
     * @return
     * @throws Exception 
     */
    @PostMapping("/insert")
    public String adminCategoriesInsertPro(Categories categories) throws Exception {
        int result = categoriesService.insert(categories);
        if( result > 0 ) 
            return "redirect:/admin/categories";
        else 
            return "redirect:/admin/categories/insert?id=" + categories.getId() + "&error";
    }
    


    /**
     * 카테고리 관리 - 수정 화면
     * @param model
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/{id}")
    public String adminCategories(Model model, @PathVariable("id") String id) throws Exception {
        Categories category = categoriesService.select(id); 
        log.info("category: " + category);
        model.addAttribute("category", category);
        return "/admin/categories/update";
    }


    /**
     * 카테고리 관리 - 수정 처리
     * @param categories
     * @return
     * @throws Exception
     */
    @PostMapping("/update")
    public String adminCategoriesUpdate(Categories categories) throws Exception {
        int result = categoriesService.update(categories);
        if( result > 0 ) 
            return "redirect:/admin/categories";
        else 
            return "redirect:/admin/categories/update?id=" + categories.getId() + "&error";
    }

    /**
     * 카테고리 관리 - 삭제 처리
     * @param deleteIdList
     * @return
     * @throws Exception
     */
    @PostMapping("/delete")
    public String adminCategoriesDelete(String[] deleteIdList) throws Exception {
        String ids = Arrays.stream(deleteIdList)
                            .map(s -> "'" + s + "'")
                            .collect(Collectors.joining(","));
        log.info("ids : " + ids);
        int result = categoriesService.delete(ids);

        if( result > 0 ) 
            return "redirect:/admin/categories";
        else 
            return "redirect:/admin/categories?error";
        
    }
    
}