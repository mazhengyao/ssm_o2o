package com.x.o2o.service;

import java.util.List;

import com.x.o2o.dto.ProductCategoryExecution;
import com.x.o2o.entity.ProductCategory;
import com.x.o2o.exceptions.ProductCategoryOperationException;

public interface ProductCategoryService {

	List<ProductCategory> getProductCategoryList(long shopId);

	/**
	 * ���������Ʒ���
	 * 
	 * @param productCategoryList
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException;

	/**
	 * ��������µ���Ʒ������id��Ϊ�գ���ɾ��������Ʒ���
	 * 
	 * @param productCategory
	 * @param shopId
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationException;
}
