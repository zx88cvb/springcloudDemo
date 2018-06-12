package com.angel.product.service;

import com.angel.product.common.DecreaseStockInput;
import com.angel.product.dataobject.ProductInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/5/28.
 */
public interface ProductService {

    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfo> findList(List<String> productIdList);

    /**
     * 扣库存
     * @param cartDTOList
     */
    void decreaseStock(List<DecreaseStockInput> cartDTOList);
}
