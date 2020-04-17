package com.com1028.assignment;

import java.math.BigDecimal;
import java.util.Objects;

public class Products {

	private String ID;
	private String name;
	private String productType;
	private String productScale;
	private String vendor;
	private String description;
	private Integer stock;
	private BigDecimal price;
	private BigDecimal MRSP;

	public Products(String iD, String name, String productType, String productScale, String vendor, String description,
			Integer stock, BigDecimal price, BigDecimal mRSP) {
		super();
		ID = iD;
		this.name = name;
		this.productType = productType;
		this.productScale = productScale;
		this.vendor = vendor;
		this.description = description;
		this.stock = stock;
		this.price = price;
		MRSP = mRSP;
	}


	@Override
	public String toString() {
		return "\n\nID=" + ID + ", name=" + name + ", productType=" + productType + ", productScale="
				+ productScale + ", vendor=" + vendor + ", description=" + description + ", stock=" + stock + ", price="
				+ price + ", MRSP=" + MRSP + "\n\n"; 
	}
	
	@Override
	public boolean equals(final Object o) {
	    if (o == null || this.getClass() != o.getClass()) {
	        return false;
	    }
	    final Products products = (Products) o;
	    return Objects.equals(this.ID, products.ID) &&
	            Objects.equals(this.name, products.name) &&
	            Objects.equals(this.productType, products.productType) &&
	            Objects.equals(this.productScale, products.productScale) &&
	            Objects.equals(this.vendor, products.vendor) &&
	            Objects.equals(this.description, products.description) &&
	            Objects.equals(this.stock, products.stock) &&
	            Objects.equals(this.price, products.price) &&
	            Objects.equals(this.MRSP, products.MRSP);
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(
	            this.ID,
	            this.name, 
	            this.productType, 
	            this.productScale, 
	            this.vendor, 
	            this.description, 
	            this.stock, 
	            this.price, 
	            this.MRSP);
	}



}
