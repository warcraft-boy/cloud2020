package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @Description: http://localhost:9001/hystrix 访问，监控cloud-provider-hystrix-payment8001
 * 下面路径写 http://localhost:8001/hystrix.stream 这个，然后cloud-provider-hystrix-payment8001这个/payment/circuit/{id}接口，正数负数访问，可监控情况
 * @Author chenjianwen
 * @Date 2020/7/22
 **/
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardMain9001.class, args);
    }
}
