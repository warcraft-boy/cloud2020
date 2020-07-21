package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author chenjianwen
 * @Date 2020/7/21
 **/
@Component
public class PaymentFallbackService implements PaymentHystrixService {

    @Override
    public String ok(Integer id) {
        return "PaymentFallbackService-----ok-------fallback";
    }

    @Override
    public String timeout(Integer id) {
        return "PaymentFallbackService-----timeout-------fallback";
    }
}
