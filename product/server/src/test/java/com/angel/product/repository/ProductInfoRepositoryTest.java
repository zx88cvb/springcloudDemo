package com.angel.product.repository;

import com.angel.ProductApplicationTests;
import com.angel.product.dataobject.ProductInfo;
import com.angel.product.repository.ProductInfoRepository;
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
public class ProductInfoRepositoryTest extends ProductApplicationTests{
    
    @Autowired
    private ProductInfoRepository productInfoRepository;
    
    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> list = productInfoRepository.findByProductStatus(0);
        Assert.assertTrue(list.size()>0);
    }

    @Test
    public void findByProductIdIn() throws Exception {
        List<ProductInfo> productInfoList = productInfoRepository.findByProductIdIn(Arrays.asList("157875196366160022", "157875227953464068"));
        Assert.assertTrue(productInfoList.size()>0);
    }

}