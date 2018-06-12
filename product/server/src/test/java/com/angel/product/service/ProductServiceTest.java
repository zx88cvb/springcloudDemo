package com.angel.product.service;

import com.angel.ProductApplicationTests;
import com.angel.product.common.DecreaseStockInput;
import com.angel.product.dataobject.ProductInfo;
import com.angel.product.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/5/28.
 */
@Component
public class ProductServiceTest extends ProductApplicationTests{

    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> list = productService.findUpAll();
        Assert.assertTrue(list.size()>0);
    }

    @Test
    public void decreaseStock() throws Exception {
        DecreaseStockInput cartDTO = new DecreaseStockInput("157875196366160022",2);
        productService.decreaseStock(Arrays.asList(cartDTO));
    }

}