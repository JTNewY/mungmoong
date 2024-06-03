// package com.mypet.mungmoong.board.controller;


// import javax.servlet.RequestDispatcher;
// import javax.servlet.http.HttpServletRequest;

// import org.springframework.boot.web.servlet.error.ErrorController;
// import org.springframework.http.HttpStatus;

// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;


// @Controller
// public class CustomErrorController implements ErrorController{

    
//     @GetMapping("/error")
//     public String handleError(HttpServletRequest request){
//         Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//         if(null != status){
//             int statusCode = Integer.valueOf(status.toString());
//             if(statusCode == HttpStatus.FORBIDDEN.value()){
//                 return "/error/s403";
//             }else if(statusCode == HttpStatus.NOT_FOUND.value()){
//                 return "/error/404";
//             }
//         }
//         return "/error/500";
//     } 

//     // @Override
//     // public String getErrorPath() {
//     //     return "/error";
//     // }
// }