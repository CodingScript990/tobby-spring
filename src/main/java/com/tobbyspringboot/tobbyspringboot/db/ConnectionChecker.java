package com.tobbyspringboot.tobbyspringboot.db;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class ConnectionChecker {
		// check function
		public void check() throws ClassNotFoundException, SQLException {
				// mysql DI[Driver]
				Map<String, String> env = getenv();
				String dbHost = env.get("DB_HOST");
				String dbUser = env.get("DB_USER");
				String dbPassword = env.get("DB_PASSWORD");

				Class.forName("com.mysql.cj.jdbc.Driver");

				Connection conn = DriverManager.getConnection(
								dbHost, dbUser,dbPassword
				);

				// 쿼리문을 전달해주는 역할 => connection db
				Statement stmt = conn.createStatement();
				// 쿼리문을 실행해줌
				ResultSet rs =  stmt.executeQuery("show databases");

				// rs 결과가 나올때까지 실행
				while (rs.next()) {
						// 결과가 잘돌아 간다면 값이 1
						String line = rs.getString(1);
						// 결과물을 output 에 출력하기
						System.out.println(line);
				}
		}

		// add method
		public void add() throws ClassNotFoundException, SQLException {
				// mysql DI[Driver]
				Map<String, String> env = getenv();
				// aws ec2[MySQL] - Connection DB
				Connection conn = DriverManager.getConnection(
								env.get("DB_HOST"), env.get("DB_USER"),env.get("DB_PASSWORD")
				);

				Class.forName("com.mysql.cj.jdbc.Driver");

				// Query 문 작성
				PreparedStatement pst = conn.prepareStatement(
								"insert into users(id, name, password) values(?, ?, ?)"
				);

				// Column add
				pst.setInt(1, 2);
				pst.setString(2, "test2");
				pst.setString(3, "test1234");
				// set 한 values 들을 db에 update!
				pst.executeUpdate();
		}

		// select method
		public void select() throws SQLException, ClassNotFoundException {
				// mysql DI[Driver]
				Map<String, String> env = getenv();
				// aws ec2[MySQL] - Connection DB
				Connection conn = DriverManager.getConnection(
								env.get("DB_HOST"), env.get("DB_USER"),env.get("DB_PASSWORD")
				);

				// mysql DI[Driver]
				Class.forName("com.mysql.cj.jdbc.Driver");

				// DB Connection
				Statement st = conn.createStatement();
				// Query 문
				ResultSet rs = st.executeQuery("select * from users");
				// rs 에 Query 문 작성한 것 담아서 while 문에 id, name, password column 값을 전달해주기 위함임
				rs = st.getResultSet();

				// while 문
				while (rs.next()) {
						// Column values
						String str = rs.getString(1); // id column
						String str2 = rs.getString(2); // name column
						String str3 = rs.getString(3); // password column
						// output values
						System.out.printf("id:%s name:%s password:%s\n",str, str2, str3); // id, name, password
				}
		}

		public static void main(String[] args) throws SQLException, ClassNotFoundException {
				// ConnectionChecker 인스턴스화
				ConnectionChecker checker = new ConnectionChecker();

				// call checker part
				checker.check(); // db check[DB와 잘 연동되었는가 확인]
//				checker.add(); // db users column add[Data 생성]
				checker.select(); // db users table select[Data 값 보기]
		}
}
