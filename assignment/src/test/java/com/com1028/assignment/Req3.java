package com.com1028.assignment;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class Req3 {

	@Test
	public void test() throws SQLException {

		Map<String, List<Integer>> customerOrders = new HashMap<String, List<Integer>>();

		BaseQuery bq = new BaseQuery("root", "georgespc");
		SetH_req3 req = new SetH_req3();
		
		req.updateDataLists(req.getCustomerDataFromDatabase(),req.getOrderDataFromDatabase(),req.getPaymentDataFromDatabase());

		ResultSet rs = bq.query(

				"		SELECT customers.customerName, orders.orderNumber, customers.customerNumber, payments.amount \n"
						+ "		FROM Customers\n"
						+ "JOIN Payments ON Payments.customerNumber=Customers.customernumber AND payments.amount>25000"
						+ "		JOIN Orders ON Orders.CustomerNumber=Customers.CustomerNumber \n" + " 	");

		ResultSetMetaData rsmd = rs.getMetaData();

		while (rs.next()) {

			String name = null;
			int orderNumber = 0;

			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				name = rs.getString(1);
				orderNumber = rs.getInt(2);

			}

			if (customerOrders.containsKey(name)) {
				if (!(customerOrders.get(name).contains(orderNumber))) {

					customerOrders.get(name).add(orderNumber);
				}

			} else if (!customerOrders.containsKey(name)) {
				customerOrders.put(name, new ArrayList<Integer>());
				customerOrders.get(name).add(orderNumber);
			}
		}

		assertEquals(req.customerOrders(), customerOrders);

	}

}
