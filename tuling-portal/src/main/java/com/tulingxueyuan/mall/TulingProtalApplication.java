package com.tulingxueyuan.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages = "com.tulingxueyuan.mall.util")
@SpringBootApplication
public class TulingProtalApplication {

    public static void main(String[] args) {
        SpringApplication.run(TulingProtalApplication.class, args);
        System.out.println("http://localhost:8888");
    }

}
