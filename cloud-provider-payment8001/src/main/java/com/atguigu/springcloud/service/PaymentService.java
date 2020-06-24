package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;

/**
 * @Description:
 * @Author chenjianwen
 * @Date 2020/6/24
 **/
public interface PaymentService {

    public int createPayment(Payment payment);

    public Payment getPaymentById(Long id);
}
