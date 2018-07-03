package com.angel;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/6/28.
 */
@Component
public class ZuulConfig {

    @ConfigurationProperties("zuul")
    @RefreshScope
    public ZuulProperties zuulProperties() {
        return new ZuulProperties();
    }

}
