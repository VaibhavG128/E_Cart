package com.shop.e_cart.util;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ProductUtil {
	
	 List<String> name;
	
	 List<String> category;
	
	 List<Integer> price;
	
	 List<MultipartFile> image;

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}

	public List<Integer> getPrice() {
		return price;
	}

	public void setPrice(List<Integer> price) {
		this.price = price;
	}

	public List<MultipartFile> getImage() {
		return image;
	}

	public void setImage(List<MultipartFile> image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "ProductUtil [name=" + name + ", category=" + category + ", price=" + price + ", image=" + image + "]";
	}
	 
	 

}
