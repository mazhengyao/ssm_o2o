package com.x.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.x.o2o.entity.Shop;

public interface ShopDao {
	/**
	 * ��ҳ��ѯ���̣���������������������ģ����������״̬�������������ID��owner
	 * �ж��@Param��ԭ��������������ж��������Ҫָ��param��Ψһ��ʶ
	 */
	/**
	 * 
	 * @param shopCondition ��ѯ����
	 * @param rowIndex �ӵڼ��п�ʼȡ����
	 * @param pageSize Ҫ���ض���������
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,
			@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	
	/**
	 * ����queryShopList����
	 * @param shopCondition
	 * @return
	 */
	int queryShopCount(@Param("shopCondition") Shop shopCondition);
	/**
	 * ͨ��employee id ��ѯ����
	 * 
	 * @param employeeId
	 * @return List<shop>
	 */
	List<Shop> queryByEmployeeId(long employeeId);

	/**
	 * ͨ��owner id ��ѯ����
	 * 
	 * @param shopId
	 * @return shop
	 */
	Shop queryByShopId(long shopId);

	/**
	 * ��������
	 * 
	 * @param shop
	 * @return effectedNum
	 */
	int insertShop(Shop shop);

	/**
	 * ���µ�����Ϣ
	 * 
	 * @param shop
	 * @return effectedNum
	 */
	int updateShop(Shop shop);

	/**
	 * ɾ�����̣����棬��ֻ֧��ɾ������û�з�����Ʒ�ĵ��̣�
	 * 
	 * @param shopName
	 * @return effectedNum
	 */
	int deleteShopByName(String shopName);

}
