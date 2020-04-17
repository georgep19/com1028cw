package com.com1028.assignment;

public class Orders {

	public Orders(int orderID, int customerNumber) {
		super();
		this.orderID = orderID;
		this.customerNumber = customerNumber;
	}
	private int orderID;
	private int customerNumber;
		
	public int getOrderID() {
		return orderID;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}


}
