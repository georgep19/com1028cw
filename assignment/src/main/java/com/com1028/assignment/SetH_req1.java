package com.com1028.assignment;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetH_req1 {

	private Map<String, List<Products>> lineWithProducts = new HashMap<String, List<Products>>();

	public ArrayList<ArrayList<Object>> getDataFromDatabase(String table) throws SQLException {

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

		ArrayList<ArrayList<Object>> data = bq.select(columns, "products");

		return data;

	}

	public void loopProducts(ArrayList<ArrayList<Object>> tableOfProducts) {

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

			checkProduct(p);

		}
	}

	private void checkProduct(Products p) {

		String line = p.getProductType();

		if (!(lineWithProducts.containsKey(line))) {
			lineWithProducts.put(line, new ArrayList<Products>());
			lineWithProducts.get(line).add(p);
		}

		else if (!lineWithProducts.get(line).contains(p)) {
			lineWithProducts.get(line).add(p);
		}
	}

	public Map<String, List<Products>> returnLineWithProducts() {
		return lineWithProducts;
	}

}
