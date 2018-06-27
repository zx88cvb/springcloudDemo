package com.angel.controller;

import com.angel.dto.CartDTO;
import com.angel.product.client.ProductClient;
import com.angel.product.common.DecreaseStockInput;
import com.angel.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/6/10.
 */

@RestController
@Slf4j
public class ClientController {

    /*@Autowired
    private LoadBalancerClient loadBalancerClient;*/
    /*@Autowired
    private RestTemplate restTemplate;*/

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        //1.第一种方式
        /*RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8080/msg", String.class);
        log.info("response={}",response);*/

        //第二种方式
        /*RestTemplate restTemplate = new RestTemplate();
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort())+"/msg";
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}",response);*/

        //第三种方式 利用@loadBalanced注解
        /*String response = restTemplate.getForObject("http://PRODUCT/msg", String.class);
        log.info("response={}",response);*/


        //Feign 方法
        String response = productClient.productMsg();
        log.info("response={}",response);
        return response;
    }

    @GetMapping("/getProductList")
    public String getProductList(){
        List<ProductInfoOutput> productInfos = productClient.listForOrder(Arrays.asList("157875196366160022", "157875227953464068"));
        log.info("response={}",productInfos);
        return "success";
    }

    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock() {
        productClient.decreaseStock(Arrays.asList(new DecreaseStockInput("164103465734242707",2)));
        return "success";
    }
}
