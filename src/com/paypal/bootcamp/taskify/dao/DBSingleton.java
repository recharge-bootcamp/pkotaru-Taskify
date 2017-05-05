package com.paypal.bootcamp.taskify.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBSingleton {

	private static DBSingleton dbIsntance;

	private DBSingleton() {

	}

	public static synchronized DBSingleton getInstance() {
		if (dbIsntance == null) {
			dbIsntance = new DBSingleton();
		}
		return dbIsntance;
	}

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection con = null;
		if (con == null) {
			String host = "jdbc:mysql://localhost:3306/task_schema";
			Class.forName("com.mysql.jdbc.Driver");
			String username = "root";
			String password = "SqlRoot3!";
			con = DriverManager.getConnection(host, username, password);

		}

		return con;
	}
}
