package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description:
 * @Author chenjianwen
 * @Date 2020/7/17
 **/
@Component
@FeignClient(value = "cloud-provider-hystrix-payment", fallback = PaymentFallbackService.class)
//这里fallback用法是这里调用ok()方法，会调用cloud-provider-hystrix-payment服务里面/payment/hystrix/ok/{id}这个接口，如果关掉cloud-provider-hystrix-payment服务
//就会调用PaymentFallbackService.class方法里面的ok()方法
public interface PaymentHystrixService {

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    String ok(@PathVariable("id") Integer id);

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    String timeout(@PathVariable("id") Integer id);
}
