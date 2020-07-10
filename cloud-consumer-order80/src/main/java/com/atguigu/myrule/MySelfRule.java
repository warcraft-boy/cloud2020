package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 定义ribbon的负载均衡策略，有RoundRobinRule轮询，这里用的是RandomRule随机
 * 由于ribbon默认的是轮询策略，想要修改自定义，官网建议不要被@ComponentScan注解扫描到
 * 因为启动类@SpringBootApplication注解里面有@ComponentScan注解，所以自定义配置类不要放在和启动类同级包下面
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
