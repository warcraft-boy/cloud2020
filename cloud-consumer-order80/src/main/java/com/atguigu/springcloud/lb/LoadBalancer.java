package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Description: 自定义轮询策略算法接口
 * @Author chenjianwen
 * @Date 2020/7/15
 **/
public interface LoadBalancer {

    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
