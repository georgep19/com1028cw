package com.com1028.assignment;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Req2 {

	@Test
	public void test() throws SQLException {

		BaseQuery bq = new BaseQuery("root", "georgespc");
		SetH_req2 req = new SetH_req2();
		req.createAndloopPayments(req.getDataFromDatabase("payments"));

		Map<Date, Double> paymentsForEachDate = new HashMap<Date, Double>();

		ResultSet rs = bq.query("select customerNumber, checkNumber, paymentDate, amount from payments");
		ResultSetMetaData rsmd = rs.getMetaData();
		while (rs.next()) {

			Date d = null;
			BigDecimal amount = null;

			for (int i = 1; i <= rsmd.getColumnCount(); i++) {

				d = rs.getDate(3);
				amount = rs.getBigDecimal(4);

			}

			if (!(paymentsForEachDate.containsKey(d))) {
				paymentsForEachDate.put(d, amount.doubleValue());
			} else {
				paymentsForEachDate.put(d, paymentsForEachDate.get(d) + amount.doubleValue());
			}
		}

		assertEquals(req.returnPaymentsForEachDate(), paymentsForEachDate);

	}

}
