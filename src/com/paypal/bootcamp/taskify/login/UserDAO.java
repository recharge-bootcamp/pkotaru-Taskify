package com.paypal.bootcamp.taskify.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.paypal.bootcamp.taskify.dao.TaskDAO;

public class UserDAO {

	public static UserBean login(UserBean bean) {
		PreparedStatement pstmnt = null;
		Connection conn = null;
		ResultSet rs = null;
		TaskDAO dao = new TaskDAO();

		String username = bean.getUsername();
		String password = bean.getPassword();

		String searchQuery = "select * from login where username='" + username + "' AND password='" + password + "';";

		System.out.println("Your user name is " + username);
		System.out.println("Your password is " + password);
		System.out.println("Query: " + searchQuery);
		conn = dao.getDBConnection();
		try {
			pstmnt = conn.prepareStatement(searchQuery);
			rs = pstmnt.executeQuery();
			while (rs.next()) {
				bean.setFirstname(rs.getString("firstname"));
				bean.setLastname(rs.getString("lastname"));
			}
			bean.setValid(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				System.out.println("closing connections");
				if (rs != null) {
					rs.close();
				}
				if (pstmnt != null) {
					pstmnt.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return bean;

	}
}
