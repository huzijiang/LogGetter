package com.hq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

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
public class LogGetterStarter {
    public static void main(String[] args) {
        SpringApplication.run(LogGetterStarter.class, args);
        System.out.println("Application run success！");
    }
}