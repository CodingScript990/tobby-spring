package com.tobbyspringboot.tobbyspringboot.dao;

import com.tobbyspringboot.tobbyspringboot.domain.User;

import java.sql.*;

public class UserDao {
		// Connection db method
		ConnectionMaker connectionMaker;

		// UserDao 생성자 => ConnectionMaker 참조 타입 connectionMaker 참조변수
		public UserDao(ConnectionMaker connectionMaker) {
				this.connectionMaker = connectionMaker;
		}

		// Connection user data add method
		public void add(User user) throws ClassNotFoundException, SQLException {
				
				// DB 연결
				Connection conn = connectionMaker.makeConnection();

				// User Data 생성
				PreparedStatement pstmt = conn.prepareStatement("insert into user(id, name, password) values(?, ?, ?)");

				// column values
				pstmt.setString(1, user.getId()); // id
				pstmt.setString(2, user.getName()); // name
				pstmt.setString(3, user.getPassword()); // password

				// set data => db 에 update
				pstmt.executeUpdate();

				// query 문 종료
				pstmt.close();
				// db 연결 종료
				conn.close();
		}

		// Connection user table data select method
		public User get(String id) throws ClassNotFoundException, SQLException {

				// DB 연결
				Connection conn = connectionMaker.makeConnection();

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

}
