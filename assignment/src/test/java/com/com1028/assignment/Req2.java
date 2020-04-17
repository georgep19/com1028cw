package com.com1028.assignment;

import static org.junit.Assert.assertEquals;

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
		SetH_req2 requirements = new SetH_req2();

		Map<Date, Integer> paymentsForEachDate = new HashMap<Date, Integer>();

		ResultSet rs = bq.query("select paymentDate from payments");
		ResultSetMetaData rsmd = rs.getMetaData();
		while (rs.next()) {
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {

				if (!(paymentsForEachDate.containsKey(rs.getDate(1)))) {
					paymentsForEachDate.put(rs.getDate(1), 1);
				} else {
					paymentsForEachDate.put(rs.getDate(1), paymentsForEachDate.get(rs.getDate(1)) + 1);
				}

			}
		}

		assertEquals(requirements.Req2(), paymentsForEachDate);

	}

}
