package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
@DefaultProperties(defaultFallback = "payment_global_fallbackMethod") //每一个方法又一个服务降级太麻烦，这里配置统一共享的一个
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    String ok(@PathVariable("id") Integer id){
        String result = paymentHystrixService.ok(id);
        log.info("*******result = " + result);
        return result;
    }

    /**
     * 客户端服务降级
     * @param id
     * @return
     */
    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    //指定了fallbackMethod = "paymentTimeoutFallbackMethod"
//    @HystrixCommand(fallbackMethod = "paymentTimeoutFallbackMethod", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    @HystrixCommand //未指定降级fallback,跳到payment_global_fallbackMethod()方法
    String timeout(@PathVariable("id") Integer id){
        String result = paymentHystrixService.timeout(id);
        log.info("********result = " + result);
        return result;
    }
    public String paymentTimeoutFallbackMethod(@PathVariable("id") Integer id){
        return "我是消费者80，对方支付系统繁忙情10秒钟后再试或者自己运行出错请检查自己，o(*_*)o";
    }

    //下面是全局fallback
    public String payment_global_fallbackMethod(){
        return "global全局处理，请稍后再试";
    }
}
