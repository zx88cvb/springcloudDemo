package com.angel.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/6/19.
 */
@RestController
@RequestMapping("/env")
public class EnvController {
    @Value("${env}")
    private String env;

    @GetMapping("/print")
    public String print(){
        return env;
    }
}
