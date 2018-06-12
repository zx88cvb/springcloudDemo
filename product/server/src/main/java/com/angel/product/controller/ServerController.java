package com.angel.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/6/10.
 */

@RestController
public class ServerController {

    @GetMapping("/msg")
    public String msg() {
        return "this is product msg 2";
    }
}
