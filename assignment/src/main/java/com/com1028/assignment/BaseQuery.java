package com.com1028.assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BaseQuery {

	protected Connection con;
	private final String db = "jdbc:mysql://localhost:3306/classicmodels";

	public BaseQuery(String uname, String pwd) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(db, uname, pwd);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public ResultSet useTable(String tableName) throws SQLException {
		String query = "select * from " + tableName;
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(query);
		System.out.println("Successfully selected " + tableName);
		return rs;
	}

	public ResultSet query(String query) throws SQLException {
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(query);
		return rs;
	}

	public ArrayList<ArrayList<Object>> select(List<String> columns, String table) throws SQLException {

		// arraylist of rows
		// ---->arraylist of column data objects

		ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();

		ArrayList<Object> rowData;

		ResultSet rs = null;

		try {

			rs = useTable(table);

			while (rs.next()) {

				rowData = new ArrayList<Object>();

				for (String c : columns) {
					rowData.add(rs.getObject(c));
				}

				data.add(rowData);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
		}

		return data;
	}

}
