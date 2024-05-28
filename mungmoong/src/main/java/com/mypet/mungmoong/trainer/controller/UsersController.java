// package com.mypet.mungmoong.trainer.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;

// import com.mypet.mungmoong.users.dto.Users;
// import com.mypet.mungmoong.users.service.UsersService;
// import org.springframework.web.bind.annotation.PostMapping;



// @Controller("Trainer_usersController")
// @RequestMapping("/users")
// public class UsersController {


//     @Autowired
//     private UsersService userService;

//     @GetMapping("/{page}")
//     public String test(@PathVariable("page") String page) {
//         return "/users/" + page;
//     }   
    

//     @PostMapping("/register")
//     public String registerPro(Users user) throws Exception {
//         int result = userService.join(user);

//         if(result >0){
//             return "redirect:/login";

//         }
        
//         return "redirect:/register?error";
//     }
    

// }
