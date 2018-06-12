package com.angel.product.repository;

import com.angel.ProductApplicationTests;
import com.angel.product.dataobject.ProductCategory;
import com.angel.product.repository.ProductCategoryRepository;
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
public class ProductCategoryRepositoryTest extends ProductApplicationTests{

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<ProductCategory> list = productCategoryRepository.findByCategoryTypeIn(Arrays.asList(11, 22));
        Assert.assertTrue(list.size()>0);

    }

}