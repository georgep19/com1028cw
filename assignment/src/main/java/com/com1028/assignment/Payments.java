package com.com1028.assignment;

import java.math.BigDecimal;
import java.sql.Date;

public class Payments {

	private Date paymentDate;
	private BigDecimal amount;
	private int customerNumber;
	private String checkedNumber;

	public Payments(int customerNumber, String checkedNumber, Date date, BigDecimal amount) {
		this.paymentDate = date;
		this.amount = amount;
		this.customerNumber = customerNumber;
		this.checkedNumber = checkedNumber;

	}

	public BigDecimal getAmount() {
		return amount;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	@Override
	public String toString() {
		return paymentDate + ", " + amount + ", " + customerNumber + ", " + checkedNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((checkedNumber == null) ? 0 : checkedNumber.hashCode());
		result = prime * result + customerNumber;
		result = prime * result + ((paymentDate == null) ? 0 : paymentDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payments other = (Payments) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (checkedNumber == null) {
			if (other.checkedNumber != null)
				return false;
		} else if (!checkedNumber.equals(other.checkedNumber))
			return false;
		if (customerNumber != other.customerNumber)
			return false;
		if (paymentDate == null) {
			if (other.paymentDate != null)
				return false;
		} else if (!paymentDate.equals(other.paymentDate))
			return false;
		return true;
	}

}
