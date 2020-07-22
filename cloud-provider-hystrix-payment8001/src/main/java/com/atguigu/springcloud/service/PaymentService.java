package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author chenjianwen
 * @Date 2020/7/17
 **/
@Service
public class PaymentService {

    public String ok(Integer id){
        return "线程池："  + Thread.currentThread().getName() + " ok,id=" + id + "\t" + "^_^哈哈";
    }

    //服务降级
    /**
     * hystrix服务降级处理，时间限定为3秒，而timeout方法sleep了5秒，所以降级到paymentInfo_TimeoutHandler()方法
     * @HystrixProperty的name属性见 HystrixCommandProperties 类
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String timeout(Integer id){
        //int a = 10/0; //这里错误代码也可以服务降级
        int time = 5;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："  + Thread.currentThread().getName() + " timeout,id=" + id + "\t" + "-_-呜呜,耗时 " + time + " 秒钟";
    }
    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池："  + Thread.currentThread().getName() + " paymentInfo_TimeoutHandler,id=" + id + "\t" + "系统繁忙，请稍后再试";
    }


    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),     //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),       //时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),       //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("********id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id不能为负数，请稍后再试，id = " + id;
    }
}
