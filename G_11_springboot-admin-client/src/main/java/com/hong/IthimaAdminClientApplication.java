package com.hong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class IthimaAdminClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(IthimaAdminClientApplication.class, args);
    }

    @RestController
    @RequestMapping("/user")
    class UserController {

        @RequestMapping("/findById")
        public String findById() {
            return "zhangyuhong小哥哥";
        }
    }

}
