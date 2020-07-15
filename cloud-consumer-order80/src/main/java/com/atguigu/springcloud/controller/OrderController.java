package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @Description:
 * @Author chenjianwen
 * @Date 2020/6/24
 **/
@RestController
@Slf4j
@RequestMapping(value = "/order")
public class OrderController {

    //private static final String PAYMENT_URL = "http://localhost:8001";
    //多个服务提供者提供服务，访问的是应用名称，而不是服务地址，因为一个应用名称下可以有多个服务地址
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private LoadBalancer loadBalancer;

    @GetMapping(value = "/consumer/create")
    public CommonResult create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping(value = "/consumer/query/{id}")
    public CommonResult queryPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/query/" + id, CommonResult.class);
    }

    /**
     * 自定义轮询算法访问接口
     * @return
     */
    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances == null | instances.size() <= 0){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instance(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }
}
