package com.zz.demoai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zz.demoai.mapper")
@SpringBootApplication
public class DemoAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAiApplication.class, args);
        System.out.println("启动成功");
    }

}
