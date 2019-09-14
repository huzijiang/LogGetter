package com.hq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动程序
 * 
 * @author huzj
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan("com.hq.*.mapper")
//启用服务注册与发现
//@EnableDiscoveryClient
//启用feign进行远程调用
//@EnableFeignClients(basePackages = "com.hq.web.service")
public class Starter {
    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
        System.out.println("Application run success！");
    }
}