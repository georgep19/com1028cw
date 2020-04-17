package com.com1028.assignment;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetH_req2 {

	public Map<Date, Integer> Req2() throws SQLException {

		BaseQuery bq = new BaseQuery("root", "georgespc");

		List<String> columns = new ArrayList<String>();
		columns.add("customerNumber");
		columns.add("checkNumber");
		columns.add("paymentDate");
		columns.add("amount");

		ArrayList<ArrayList<Object>> tableOfPayments = bq.select(columns, "payments");

		Map<Date, Integer> paymentsForEachDay = new HashMap<Date, Integer>();

		// iterate through all payments
		for (int i = 0; i < tableOfPayments.size(); i++) {

			ArrayList<Object> row = tableOfPayments.get(i);
			Date d = (Date) row.get(2);

			if (!(paymentsForEachDay.containsKey(d))) {

				paymentsForEachDay.put(d, 1);

			} else {
				paymentsForEachDay.put(d, paymentsForEachDay.get(d) + 1);
			}

		}

		return paymentsForEachDay;

	}
}