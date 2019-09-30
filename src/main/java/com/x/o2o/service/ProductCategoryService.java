package com.x.o2o.service;

import java.util.List;

import com.x.o2o.dto.ProductCategoryExecution;
import com.x.o2o.entity.ProductCategory;
import com.x.o2o.exceptions.ProductCategoryOperationException;

public interface ProductCategoryService {

	List<ProductCategory> getProductCategoryList(long shopId);

	/**
	 * 批量添加商品类别
	 * 
	 * @param productCategoryList
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException;

	/**
	 * 将此类别下的商品里的类别id置为空，再删除掉改商品类别
	 * 
	 * @param productCategory
	 * @param shopId
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationException;
}
