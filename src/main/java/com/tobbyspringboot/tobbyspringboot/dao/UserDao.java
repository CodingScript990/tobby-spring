package com.tobbyspringboot.tobbyspringboot.dao;

import com.tobbyspringboot.tobbyspringboot.domain.User;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class UserDao {
		// Connection db method
		public Connection getConnection() throws ClassNotFoundException, SQLException {
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

		// Connection user data add method
		public void add(User user) throws ClassNotFoundException, SQLException {
				
				// DB 연결
				Connection conn = getConnection();
				
				PreparedStatement pstmt = conn.prepareStatement("insert into user(id, name, password) values(?, ?, ?)");
				pstmt.setString(1, user.getId());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getPassword());

				pstmt.executeUpdate();

				pstmt.close();
				conn.close();
		}

		// Connection user table data select method
		public User get(String id) throws ClassNotFoundException, SQLException {

				// DB 연결
				Connection conn = getConnection();

				// db 와 연결하여 table 의 data 들을 불러오는 query 문을 작성
				PreparedStatement pst = conn.prepareStatement("select id, name, password from user where id = ?");

				pst.setString(1, id); // pk 값을 추출하여 나머지 컬럼의 정보를 보기 위한 작업

				ResultSet rs = pst.executeQuery(); // query 문을 실행시켜 준다
				rs.next(); // ctrl + enter 역할

				User user = new User();
				// user id, name, password => db 랑 같아야함
				user.setId(rs.getString("id")); // db 에서 id 값을 불러오고
				user.setName(rs.getString("name")); // db 에서 name 값을 불러오고
				user.setPassword(rs.getString("password")); // db 에서 password 값을 불러온다

				// 닫아주는 것은 무조건 해줘야함!
				rs.close(); // Query 문 실행이 끝나면 종료
				pst.close(); // query 문 호출되면 종료
				conn.close(); // db와 연결을 종료

				return user; // user 의 값을 리턴해줌 => id, name, password
		}

		// main method
		public static void main(String[] args) throws SQLException, ClassNotFoundException {

				UserDao userDao = new UserDao();

				User user = new User();

				user.setId("2");
				user.setName("test2");
				user.setPassword("1234");

				userDao.add(user);

				User selectedUser = userDao.get("2");

				System.out.println(selectedUser.getId());
				System.out.println(selectedUser.getName());
				System.out.println(selectedUser.getPassword());
		}
}
