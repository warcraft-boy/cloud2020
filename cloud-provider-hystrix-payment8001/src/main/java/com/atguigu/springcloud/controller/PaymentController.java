package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author chenjianwen
 * @Date 2020/7/17
 **/
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String ok(@PathVariable("id") Integer id){
        String result = paymentService.ok(id);
        log.info("*******result =" + result);
        return result;
    }

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String timeout(@PathVariable("id") Integer id){
        String result = paymentService.timeout(id);
        log.info("******result =" + result);
        return result;
    }
}