package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    String ok(@PathVariable("id") Integer id){
        String result = paymentHystrixService.ok(id);
        log.info("*******result = " + result);
        return result;
    }

    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    String timeout(@PathVariable("id") Integer id){
        String result = paymentHystrixService.timeout(id);
        log.info("********result = " + result);
        return result;
    }
}
