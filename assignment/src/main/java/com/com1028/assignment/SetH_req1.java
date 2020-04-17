package com.com1028.assignment;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetH_req1 {
	
	public Map<String, List<Products>> Req1() throws SQLException {

		BaseQuery bq = new BaseQuery("root", "georgespc");

		List<String> columns = new ArrayList<String>();
		columns.add("productCode");
		columns.add("productName");
		columns.add("productLine"); // 2
		columns.add("productScale");
		columns.add("productVendor");
		columns.add("productDescription");
		columns.add("quantityInStock");
		columns.add("buyPrice");
		columns.add("MSRP");

		ArrayList<ArrayList<Object>> tableOfProducts = bq.select(columns, "products");

		List<String> productLines = new ArrayList<String>();

		Map<String, List<Products>> lineWithProducts = new HashMap<String, List<Products>>();

		for (int i = 0; i < tableOfProducts.size(); i++) {

			if (!productLines.contains(tableOfProducts.get(i).get(2))) {
				productLines.add(tableOfProducts.get(i).get(2).toString());
				lineWithProducts.put(tableOfProducts.get(i).get(2).toString(), new ArrayList<Products>());
			}

		}

		for (int i = 0; i < tableOfProducts.size(); i++) {

			String ID = tableOfProducts.get(i).get(0).toString();
			String name = tableOfProducts.get(i).get(1).toString();
			String line = tableOfProducts.get(i).get(2).toString();
			String scale = tableOfProducts.get(i).get(3).toString();
			String vendor = tableOfProducts.get(i).get(4).toString();
			String desc = tableOfProducts.get(i).get(5).toString();
			Integer quantity = (Integer) tableOfProducts.get(i).get(6);
			BigDecimal price = (BigDecimal) tableOfProducts.get(i).get(7);
			BigDecimal msrp = (BigDecimal) tableOfProducts.get(i).get(8);

			Products p = new Products(ID, name, line, scale, vendor, desc, quantity, price, msrp);

			if (!lineWithProducts.get(line).contains(p)) {
				lineWithProducts.get(line).add(p);
			}

		}

		return lineWithProducts;

	}

}
