package com.com1028.assignment;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetH_req2 {

	private Map<Date, Double> paymentsForEachDay = new HashMap<Date, Double>();

	public ArrayList<ArrayList<Object>> getDataFromDatabase(String table) throws SQLException {

		BaseQuery bq = new BaseQuery("root", "georgespc");

		List<String> columns = new ArrayList<String>();
		columns.add("customerNumber");
		columns.add("checkNumber");
		columns.add("paymentDate");
		columns.add("amount");

		ArrayList<ArrayList<Object>> data = bq.select(columns, table);

		return data;

	}

	public void createAndloopPayments(ArrayList<ArrayList<Object>> tableOfPayments) throws SQLException {

		for (int i = 0; i < tableOfPayments.size(); i++) {

			ArrayList<Object> row = tableOfPayments.get(i);
			
			int cNum = (int) row.get(0);
			String s = row.get(1).toString();
			Date d = (Date)row.get(2);
			BigDecimal amount = (BigDecimal)row.get(3);

			
			Payments p = new Payments(cNum, s, d, amount);
			
			checkPaymentDate(p);

		}

	}

	private void checkPaymentDate(Payments p) {
		if (!(paymentsForEachDay.containsKey(p.getPaymentDate()))) {

			paymentsForEachDay.put(p.getPaymentDate(), p.getAmount().doubleValue());

		} else {
			paymentsForEachDay.put(p.getPaymentDate(), paymentsForEachDay.get(p.getPaymentDate()) + p.getAmount().doubleValue());
		}
	}
	
	
	public Map<Date, Double> returnPaymentsForEachDate () {
		return paymentsForEachDay;
	}
	
}