package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author chenjianwen
 * @Date 2020/7/15
 **/
@Component
public class MyLB implements LoadBalancer{

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;
        do{
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 :current + 1;
            //atomicInteger.compareAndSet(current, next) 比较的是atomicInteger和current，如果相等，则返回true,且将atomicInteger更新为next
        }while(!this.atomicInteger.compareAndSet(current, next));
        System.out.println("next = " + next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
