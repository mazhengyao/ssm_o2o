package com.x.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.x.o2o.dao.ProductCategoryDao;
import com.x.o2o.dto.ProductCategoryExecution;
import com.x.o2o.entity.ProductCategory;
import com.x.o2o.enums.ProductCategoryStateEnum;
import com.x.o2o.exceptions.ProductCategoryOperationException;
import com.x.o2o.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryDao productCategoryDao;

	public List<ProductCategory> getProductCategoryList(long shopId) {
		return productCategoryDao.queryProductCategoryList(shopId);
	}
	
	@Transactional
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException {
		if(productCategoryList!=null && productCategoryList.size() > 0) {
			try {
				int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
				if(effectedNum <= 0) {
					throw new ProductCategoryOperationException("������𴴽�ʧ��");
				} else {
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				throw new ProductCategoryOperationException("batchAddProductCategory error:"+ e.getMessage());
			}
		}else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}
	
	
	//�����Ƚ���Ʒ��� ��Ʒid��Ϊ�� ��ɾ�� ��Ϊ���� ����ʹ������
    @Transactional
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
            throws ProductCategoryOperationException {
        // TODO ������Ʒ����µ���ƷId��Ϊ��
        try {
            int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
            if(effectedNum <= 0) {
                throw new ProductCategoryOperationException("��Ʒ���ɾ��ʧ��");
            }else {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            }
        }catch (Exception e) {
            throw new ProductCategoryOperationException("deleteProductCategory error :" + e.getMessage());
        }
    }

}
