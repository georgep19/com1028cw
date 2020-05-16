package com.com1028.assignment;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class Req1 {

	@Test
	public void test() throws SQLException {

		BaseQuery bq = new BaseQuery("root", "georgespc");
		SetH_req1 requirement = new SetH_req1();
		requirement.loopProducts(requirement.getDataFromDatabase("products"));

		Map<String, List<Products>> lineWithProducts = new HashMap<String, List<Products>>();

		ResultSet rs = bq.query(
				"SELECT products.productcode, products.productName, products.productline, products.productscale, products.productvendor, "
						+ "products.productdescription, products.quantityinstock, products.buyprice, products.msrp \n" +

						"FROM products \n" +

						"INNER JOIN productlines ON productlines.productline=products.productline" +

						"");
		ResultSetMetaData rsmd = rs.getMetaData();

		while (rs.next()) {
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				String code = rs.getString(1);
				String name = rs.getString(2);
				String line = rs.getString(3);
				String scale = rs.getString(4);
				String vendor = rs.getString(5);
				String desc = rs.getString(6);
				int quantity = rs.getInt(7);
				BigDecimal price = rs.getBigDecimal(8);
				BigDecimal msrp = rs.getBigDecimal(9);
				Products p = new Products(code, name, line, scale, vendor, desc, quantity, price, msrp);
				if (!(lineWithProducts.containsKey(line))) {
					lineWithProducts.put(line, new ArrayList<Products>());
					lineWithProducts.get(line).add(p);
				} else if (!(lineWithProducts.get(line).contains(p))) {
					lineWithProducts.get(line).add(p);
				}

			}
		}

		assertEquals(requirement.returnLineWithProducts(), lineWithProducts);

	}

}
