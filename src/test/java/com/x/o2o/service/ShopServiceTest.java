package com.x.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.x.o2o.BaseTest;
import com.x.o2o.dto.ShopExecution;
import com.x.o2o.entity.Area;
import com.x.o2o.entity.PersonInfo;
import com.x.o2o.entity.Shop;
import com.x.o2o.entity.ShopCategory;
import com.x.o2o.enums.ShopStateEnum;
import com.x.o2o.exceptions.ShopOperationException;

public class ShopServiceTest extends BaseTest {
	@Autowired
	private ShopService shopService;

	@Test
	public void testModifyShop() throws ShopOperationException, FileNotFoundException {
		Shop shop = new Shop();
		shop.setShopId(2L);
		shop.setShopName("咖啡店55");
		File shopImg = new File("D:\\fuzhu\\db.jpg");
		InputStream is = new FileInputStream(shopImg);
		ShopExecution shopExecution = shopService.modifyShop(shop, is, shopImg.getName());
		System.out.println("新的图片地址为：" + shopExecution.getShop().getShopImg());
	}

	@Test
	public void testAddShop() throws Exception {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试的店铺3");
		shop.setShopDesc("test3");
		shop.setShopAddr("testaddr3");
		shop.setPhone("45646520");
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");

		File shopImg = new File("D:\\fuzhu\\wln.jpg");
		InputStream is = new FileInputStream(shopImg);
		ShopExecution shopExecution = shopService.addShop(shop, is, shopImg.getName());
		assertEquals(ShopStateEnum.CHECK.getState(), shopExecution.getState());

	}

	@Test
	public void testGetShopList() {
		Shop shopCondition = new Shop();
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(3L);
		shopCondition.setShopCategory(sc);
		ShopExecution se = shopService.getShopList(shopCondition, 3, 2);// 第一页，每页两条数据
		System.out.println("店铺列表数为" + se.getShopList().size());
		System.out.println("店铺总数为" + se.getCount());
	}

}
