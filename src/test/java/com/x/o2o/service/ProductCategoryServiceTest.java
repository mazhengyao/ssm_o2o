package com.x.o2o.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.x.o2o.BaseTest;
import com.x.o2o.entity.ProductCategory;

public class ProductCategoryServiceTest extends BaseTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void testQueryProductCategoryList() {
        long shopId = 1L;
        List<ProductCategory> productCategories = productCategoryService.getProductCategoryList(shopId);
        System.out.println("该店铺自定义类别数为："+productCategories.size());
    }
}
