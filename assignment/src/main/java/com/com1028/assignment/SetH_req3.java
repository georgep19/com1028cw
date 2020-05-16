package com.com1028.assignment;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetH_req3 {

	private List<Payments> payments = new ArrayList<Payments>();

	private Map<String, List<Integer>> customerOrders = new HashMap<String, List<Integer>>();
	
	private Map<Integer, ArrayList<Integer>> customerAndOrder = new HashMap<Integer, ArrayList<Integer>>();


	public ArrayList<ArrayList<Object>> getCustomerDataFromDatabase() throws SQLException {

		BaseQuery bq = new BaseQuery("root", "georgespc");

		List<String> columns = new ArrayList<String>();
		columns.add("customerNumber");
		columns.add("customerName");

		ArrayList<ArrayList<Object>> data = bq.select(columns, "customers");

		return data;

	}

	public ArrayList<ArrayList<Object>> getOrderDataFromDatabase() throws SQLException {

		BaseQuery bq2 = new BaseQuery("root", "georgespc");

		List<String> columns2 = new ArrayList<String>();
		columns2.add("orderNumber");
		columns2.add("customerNumber");

		ArrayList<ArrayList<Object>> data = bq2.select(columns2, "orders");

		return data;

	}

	public ArrayList<ArrayList<Object>> getPaymentDataFromDatabase() throws SQLException {

		BaseQuery bq4 = new BaseQuery("root", "georgespc");

		List<String> columns4 = new ArrayList<String>();
		columns4.add("customerNumber");
		columns4.add("checkNumber");
		columns4.add("paymentDate");
		columns4.add("amount");

		ArrayList<ArrayList<Object>> data = bq4.select(columns4, "payments");

		return data;

	}


	public void updateDataLists(ArrayList<ArrayList<Object>> tableOfCustomers,  ArrayList<ArrayList<Object>> tableOfOrders,  ArrayList<ArrayList<Object>> tableOfPayments) {

		for (int i = 0; i < tableOfPayments.size(); i++) {
			Payments p = new Payments((Integer) tableOfPayments.get(i).get(0), tableOfPayments.get(i).get(1).toString(),
					new Date(2), (BigDecimal) tableOfPayments.get(i).get(3));
			if (p.getAmount().doubleValue() > 25000) {
				customerAndOrder.put(p.getCustomerNumber(), new ArrayList<Integer>());
				payments.add(p);

			}
		}
		
		for (int i = 0; i < tableOfOrders.size(); i++) {
			Orders o = new Orders((Integer) tableOfOrders.get(i).get(0), (Integer) tableOfOrders.get(i).get(1));
			if (customerAndOrder.containsKey(o.getCustomerNumber())) {
				customerAndOrder.get(o.getCustomerNumber()).add(o.getOrderID());
			}
		}

		for (int i = 0; i < tableOfCustomers.size(); i++) {
			Customers c = new Customers((Integer) tableOfCustomers.get(i).get(0),
					tableOfCustomers.get(i).get(1).toString());

			for (Payments p : payments) {
				if (p.getCustomerNumber() == c.getCID() && !customerOrders.containsKey(c.getName())) {
					customerOrders.put(c.getName(), customerAndOrder.get(p.getCustomerNumber()));
				}
			}
		}


	}
	
	public Map<String, List<Integer>> customerOrders() {
		return customerOrders;
	}


}
