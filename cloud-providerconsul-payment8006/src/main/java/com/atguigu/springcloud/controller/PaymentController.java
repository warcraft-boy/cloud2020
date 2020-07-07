package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Description:
 * @Author chenjianwen
 * @Date 2020/7/7
 **/
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/consul")
    public String consul(){
        return "springcloud with consul" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
