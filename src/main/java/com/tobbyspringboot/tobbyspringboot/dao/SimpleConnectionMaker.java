package com.tobbyspringboot.tobbyspringboot.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import static java.lang.System.getenv;

public class SimpleConnectionMaker {
		public Connection makeNewConnection() throws SQLException, ClassNotFoundException {
				Map<String, String> env = getenv();

				String dbHost = env.get("DB_HOST");
				String dbUser = env.get("DB_USER");
				String dbPassword = env.get("DB_PASSWORD");

				Class.forName("com.mysql.cj.jdbc.Driver");

				Connection conn = DriverManager.getConnection(
								dbHost, dbUser, dbPassword
				);
				// conn 은 db를 연결해주는 값을 반환해줌
				return conn;
		}
}
