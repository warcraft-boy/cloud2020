package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @Author chenjianwen
 * @Date 2020/6/24
 **/
@Mapper
public interface PaymentDao{
    int insert(Payment payment);
    Payment selectById(Long id);
}
