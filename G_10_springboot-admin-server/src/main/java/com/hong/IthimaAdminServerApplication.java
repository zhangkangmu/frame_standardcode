package com.hong;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer  //开启Admin-Server
public class IthimaAdminServerApplication {

   public static void main(String[] args) {
      SpringApplication.run(IthimaAdminServerApplication.class, args);
   }

}
