package com.angel.product.controller;

import com.angel.product.common.DecreaseStockInput;
import com.angel.product.dataobject.ProductCategory;
import com.angel.product.dataobject.ProductInfo;
import com.angel.product.enums.ResponseCode;
import com.angel.product.service.CategoryService;
import com.angel.product.service.ProductService;
import com.angel.product.vo.ProductInfoVO;
import com.angel.product.vo.ProductVO;
import com.angel.product.vo.ResultVo;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018/5/27.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询所有上架商品
     * 获取类目type
     * 查询类目
     * 构造数据
     */
    @GetMapping("list")
    public ResultVo list(){
        //查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //获取类目type
        List<Integer> categoryTypeList = productInfoList
                .stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        //查询类目
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //构造数据
        List<ProductVO> productVOsList = Lists.newArrayList();
        categoryList.forEach(category -> {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(category.getCategoryName());
            productVO.setCategoryType(category.getCategoryType());

            List<ProductInfoVO> productInfoVOList = Lists.newArrayList();
            productInfoList.forEach(productInfoItem -> {
                if(productInfoItem.getCategoryType().equals(category.getCategoryType())){
                    ProductInfoVO productInfoVo = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfoItem,productInfoVo);
                    productInfoVOList.add(productInfoVo);
                }
            });
            productVO.setProductInfoVOList(productInfoVOList);
            productVOsList.add(productVO);
        });
        return ResultVo.createBySuccess(ResponseCode.SUCCESS.getDesc(), productVOsList);
    }

    /**
     * 获取商品列表(提供给订单服务)
     * @param productIdList
     * @return
     */
    @PostMapping("listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList){
        return productService.findList(productIdList);
    }

    /**
     * 扣库存
     * @param cartDTOList
     */
    @PostMapping("decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> cartDTOList){
        productService.decreaseStock(cartDTOList);
    }
}
