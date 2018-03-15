package com.netease.web.meta;

public class Product {
	private int id;
	private String name;
	private float price;
	private String summary;
	private String content;
	private String picture;
	private Seller seller;
	private int sellNum=0;
	private int BuyNum=0;
	private int num;//已经购买的数量
	private String date;
	private float money;
	private int buylistId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	public int getSellNum() {
		return sellNum;
	}
	public void setSellNum(int sellNum) {
		this.sellNum = sellNum;
	}
	public int getBuyNum() {
		return BuyNum;
	}
	public void setBuyNum(int buyNum) {
		BuyNum = buyNum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public int getBuylistId() {
		return buylistId;
	}
	public void setBuylistId(int buylistId) {
		this.buylistId = buylistId;
	}
	
}
