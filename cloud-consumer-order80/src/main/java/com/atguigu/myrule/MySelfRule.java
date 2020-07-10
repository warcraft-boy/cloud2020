package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 定义ribbon的负载均衡策略，有RoundRobinRule轮询，这里用的是RandomRule随机 
 * @Author chenjianwen
 * @Date 2020/7/10
 **/
@Configuration
public class MySelfRule {

    @Bean
    public IRule iRule(){
        return new RandomRule();
    }
}
