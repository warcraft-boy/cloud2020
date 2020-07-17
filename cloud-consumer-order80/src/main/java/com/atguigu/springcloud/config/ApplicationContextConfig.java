package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author chenjianwen
 * @Date 2020/6/24
 **/
@Configuration
public class ApplicationContextConfig {

    @Bean
    //@LoadBalanced   //若有多个服务提供者，且访问服务不是地址，而是服务名称，restTemplate需要加这个注解来负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
