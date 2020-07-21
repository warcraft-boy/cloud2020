package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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

    /**
     * hystrix服务降级处理，时间限定为3秒，而timeout方法sleep了5秒，所以降级到paymentInfo_TimeoutHandler()方法
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
}
