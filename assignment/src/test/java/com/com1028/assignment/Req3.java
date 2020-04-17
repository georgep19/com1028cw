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
		SetH_req3 requirements = new SetH_req3();

		ResultSet rs = bq.query(
				"		SELECT customers.customerName, orders.orderNumber, customers.customerNumber, payments.amount \n"
						+ "		FROM Customers\n"
						+ "		JOIN Orders ON Orders.CustomerNumber=Customers.CustomerNumber \n"
						+ " 	JOIN Payments ON Payments.customerNumber=Customers.customernumber AND payments.amount>25000"
						+ "");
		ResultSetMetaData rsmd = rs.getMetaData();

		while (rs.next()) {
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				String name = rs.getString(1);
				int orderNumber = rs.getInt(2);

				System.out.println(rs.getString(i));

				if (!(customerOrders.containsKey(name))) {
					customerOrders.put(name, new ArrayList<Integer>());
					customerOrders.get(name).add(orderNumber);
				} else if (!(customerOrders.get(name).contains(orderNumber))) {
					customerOrders.get(name).add(orderNumber);
				}

			}
		}

		assertEquals(requirements.Req3(), customerOrders);

	}

}
