package com.com1028.assignment;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetH_req3 {
	
	public Map<String, List<Integer>> Req3() throws SQLException { 

		BaseQuery bq = new BaseQuery("root", "georgespc");

		List<String> columns = new ArrayList<String>();
		columns.add("customerNumber");
		columns.add("customerName");

		ArrayList<ArrayList<Object>> tableOfCustomers = bq.select(columns, "customers");

		BaseQuery bq2 = new BaseQuery("root", "georgespc");

		List<String> columns2 = new ArrayList<String>();
		columns2.add("orderNumber");
		columns2.add("customerNumber");

		ArrayList<ArrayList<Object>> tableOfOrders = bq2.select(columns2, "orders");

		BaseQuery bq4 = new BaseQuery("root", "georgespc");

		List<String> columns4 = new ArrayList<String>();
		columns4.add("customerNumber");
		columns4.add("checkNumber");
		columns4.add("paymentDate");
		columns4.add("amount");

		ArrayList<ArrayList<Object>> tableOfPayments = bq4.select(columns4, "payments");

		List<Payments> payments = new ArrayList<Payments>();
		List<Customers> customers = new ArrayList<Customers>();
		List<Orders> orders = new ArrayList<Orders>();

		for (int i = 0; i < tableOfCustomers.size(); i++) {

			Customers c = new Customers((Integer) tableOfCustomers.get(i).get(0),
					tableOfCustomers.get(i).get(1).toString());

			if (!customers.contains(c)) {
				customers.add(c);
			}
		}

		for (int i = 0; i < tableOfPayments.size(); i++) {

			Payments p = new Payments((Integer) tableOfPayments.get(i).get(0), tableOfPayments.get(i).get(1).toString(),
					new Date(0), (BigDecimal) tableOfPayments.get(i).get(3));

			if (!payments.contains(p)) {
				payments.add(p);
			}
		}

		for (int i = 0; i < tableOfOrders.size(); i++) {
			Orders o = new Orders((Integer) tableOfOrders.get(i).get(0), (Integer) tableOfOrders.get(i).get(1));
			if (!orders.contains(o)) {
				orders.add(o);
			}
		}

		// Map that returns an ordersdetails with the customer number

		Map<Customers, Payments> customerPayments = new HashMap<Customers, Payments>();

		Map<String, List<Integer>> customerOrders = new HashMap<String, List<Integer>>();

		for (int i = 0; i < customers.size(); i++) {
			for (Payments p : payments) {
				if (customers.get(i).getCID() == p.getCustomerNumber()) {
					if (p.getAmount().compareTo(new BigDecimal(25000)) == 1) {
						customerPayments.put(customers.get(i), p);


					}
				}

			}
		}

		for (int i = 0; i < tableOfOrders.size(); i++) {

			for (Map.Entry<Customers, Payments> entry : customerPayments.entrySet()) {
				Customers key = entry.getKey();

				if (orders.get(i).getCustomerNumber() == key.getCID()) {
					if (!customerOrders.containsKey(key.getName())) {
						List<Integer> orderNumbers = new ArrayList<Integer>();
						orderNumbers.add(orders.get(i).getOrderID());
						customerOrders.put(key.getName(), orderNumbers);


					} else {
						customerOrders.get(key.getName()).add(orders.get(i).getOrderID());
					}
				}

			}

		}

		return customerOrders;

	}

}
