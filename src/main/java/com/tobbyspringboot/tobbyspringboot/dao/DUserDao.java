package com.tobbyspringboot.tobbyspringboot.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DUserDao  {
//		@Override
		public Connection getConnection() throws ClassNotFoundException, SQLException {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(
								"", "", ""
				);
				return conn;
		}
}
