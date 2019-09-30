package com.x.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.x.o2o.BaseTest;
import com.x.o2o.entity.Area;
import com.x.o2o.entity.PersonInfo;
import com.x.o2o.entity.Shop;
import com.x.o2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest {
	@Autowired
	private ShopDao shopDao;

	@Test
	public void testInsertShop() throws Exception{
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
		shop.setShopName("���Եĵ���");
		shop.setShopDesc("test");
		shop.setShopAddr("testaddr1");
		shop.setPhone("12345678910");
		shop.setShopImg("test1");
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("�����");
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1, effectedNum);
	}
	
	@Test
	public void testUpdateShop() throws Exception{
		Shop shop = new Shop();
		shop.setShopId(1L);
		shop.setShopDesc("��������");
		shop.setShopAddr("���Ե�ַ");
		shop.setPhone("���Ե绰");
		shop.setLastEditTime(new Date());
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1, effectedNum);
	}
	
	@Test
	public void testQueryByShopId(){
		long shopId=1;
		Shop shop = shopDao.queryByShopId(shopId);
		System.out.println("areaId"+shop.getArea().getAreaId());
		System.out.println("areaName"+shop.getArea().getAreaName());
	}
	
	@Test
	public void testQueryShopListAndCount(){
		Shop shopCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1L);
		shopCondition.setOwner(owner);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
		int count = shopDao.queryShopCount(shopCondition);
		System.out.println("�����б�Ĵ�С"+shopList.size());
		System.out.println("��������"+count);	
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(3L);
		shopCondition.setShopCategory(sc);
		List<Shop> shopList2 = shopDao.queryShopList(shopCondition, 0, 2);
		int count2 = shopDao.queryShopCount(shopCondition);
		System.out.println("�µ����б�Ĵ�С"+shopList2.size());
		System.out.println("��������"+count2);	
		
	}
	
}
