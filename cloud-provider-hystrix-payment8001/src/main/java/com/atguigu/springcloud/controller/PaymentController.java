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

    /**
     * 服务降级
     * @param id
     * @return
     */
    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String timeout(@PathVariable("id") Integer id){
        String result = paymentService.timeout(id);
        log.info("******result =" + result);
        return result;
    }

    /**
     * 服务熔断
     * 测试：我们先用负数测试，发现显示跳到降级错误信息，不停的点击访问一直显示错误信息
     * 当突然改回正数，此时也显示错误信息，多点击几次后才显示正确信息
     * @param id
     * @return
     */
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("*******result = " + result);
        return result;
    }
}
