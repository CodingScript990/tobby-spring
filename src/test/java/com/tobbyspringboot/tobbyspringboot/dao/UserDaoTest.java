package com.tobbyspringboot.tobbyspringboot.dao;

import com.tobbyspringboot.tobbyspringboot.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
		public static void main(String[] args) throws SQLException, ClassNotFoundException {
				ConnectionMaker cm = new DConnectionMaker();

				UserDao userDao = new UserDao(cm);

				User user = new User();

				user.setId("6");
				user.setName("test6");
				user.setPassword("1234");

				userDao.add(user);

				User selectedUser = userDao.get("6");

				System.out.println(selectedUser.getId());
				System.out.println(selectedUser.getName());
				System.out.println(selectedUser.getPassword());
		}
}