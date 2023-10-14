package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.UserBean;

@Repository
public class UserDao {

	@Autowired // provide instance - create new or existing
	JdbcTemplate stmt; // spring -> execute query

	public void addUser(UserBean user) {
		stmt.update("insert into users (firstName,email,password) values (?,?,?)", user.getFirstName(), user.getEmail(),
				user.getPassword());
		//executeUpdate() -> update() 
		//executeQuery()  -> query()
	}
}
