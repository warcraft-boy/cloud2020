package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author chenjianwen
 * @Date 2020/7/27
 **/
@RestController
@RefreshScope   //支持nacos动态刷新
public class ConfigClientController {

    @Value("${config.info}") //配置在nacos中
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo(){
        return configInfo;
    }
}
