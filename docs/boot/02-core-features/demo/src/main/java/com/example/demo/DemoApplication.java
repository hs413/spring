package com.example.demo;

import com.example.demo.bean.MyBean;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
//        SpringApplication application = new SpringApplication(DemoApplication.class);
//        application.setBannerMode(Banner.Mode.OFF);
//        application.run(args);
//        SpringApplication.setAddCommandLineProperties(false);

    }
}
