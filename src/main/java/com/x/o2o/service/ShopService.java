package com.x.o2o.service;


import java.io.InputStream;

import com.x.o2o.dto.ShopExecution;
import com.x.o2o.entity.Shop;
import com.x.o2o.exceptions.ShopOperationException;

public interface ShopService{
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
	ShopExecution addShop(Shop shop,InputStream shopImgInputStream,String fileName)throws ShopOperationException;
	Shop getByShopId(long shopId);
	ShopExecution modifyShop(Shop shop,InputStream shopImgInputStream,String fileName)throws ShopOperationException;

}