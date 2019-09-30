package com.x.o2o.entity;

import java.util.Date;
import java.util.List;

public class Product {
	// id
	private Long productId;
	// ����
	private String productName;
	// ����
	private String productDesc;
	// ͼƬ��ַ
	private String imgAddr;
	// �ۿۼ�
	private String normalPrice;
	private String promotionPrice;
	// Ȩ��
	private Integer priority;
	// ����ʱ��
	private Date createTime;
	// ����ʱ��
	private Date lastEditTIme;
	// ״̬��0���¼ܣ�1����ǰ��չʾϵͳչʾ
	private Integer enableStatus;

	// �б� һ�Զ�
	private List<ProductImg> productImgList;
	// ��Ʒ���
	private ProductCategory productCategory;
	// ����
	private Shop shop;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getImgAddr() {
		return imgAddr;
	}

	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}

	public String getNormalPrice() {
		return normalPrice;
	}

	public void setNormalPrice(String normalPrice) {
		this.normalPrice = normalPrice;
	}

	public String getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(String promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastEditTIme() {
		return lastEditTIme;
	}

	public void setLastEditTIme(Date lastEditTIme) {
		this.lastEditTIme = lastEditTIme;
	}

	public Integer getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}

	public List<ProductImg> getProductImgList() {
		return productImgList;
	}

	public void setProductImgList(List<ProductImg> productImgList) {
		this.productImgList = productImgList;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

}