package com.atguigu.springcloud.service;

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

    public String timeout(Integer id){
        int time = 3;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："  + Thread.currentThread().getName() + " timeout,id=" + id + "\t" + "-_-呜呜,耗时 " + time + " 秒钟";
    }
}
