package com.x.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.x.o2o.entity.ProductCategory;

public interface ProductCategoryDao {

	List<ProductCategory> queryProductCategoryList(long shopId);

	int batchInsertProductCategory(List<ProductCategory> productCategoryList);

	/**
	 * 根据productCategoryId 和 shopId删除商品类别 因为是有两个参数的 所以使用param标签
	 * 
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
}