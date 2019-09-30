package com.x.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.x.o2o.entity.ProductCategory;

public interface ProductCategoryDao {

	List<ProductCategory> queryProductCategoryList(long shopId);

	int batchInsertProductCategory(List<ProductCategory> productCategoryList);

	/**
	 * ����productCategoryId �� shopIdɾ����Ʒ��� ��Ϊ�������������� ����ʹ��param��ǩ
	 * 
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
}