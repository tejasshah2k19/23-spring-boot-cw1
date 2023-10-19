package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.UserBean;

@Repository
public class UserDao {

	@Autowired // provide instance - create new or existing
	JdbcTemplate stmt; // spring -> execute query

	public void addUser(UserBean user) {
		stmt.update("insert into users (firstName,email,password,deleted) values (?,?,?,0)", user.getFirstName(), user.getEmail(),
				user.getPassword());
		// executeUpdate() -> update()
		// executeQuery() -> query()
	}

	public List<UserBean> getAllUsers() {
		return stmt.query("select * from users where deleted != 1", new BeanPropertyRowMapper<UserBean>(UserBean.class));
	}

	//soft delete 
	public void deleteUserSoft(Integer userId) {
		stmt.update("update users set deleted = 1 where userId = ?",userId);
	}

	public void deleteUser(Integer userId) {
		stmt.update("delete from users where userId = ?",userId);//hard delete
	}

	public List<UserBean> getAllUsers2() {
		// TODO Auto-generated method stub
		return stmt.query("select * from users where deleted = 1", new BeanPropertyRowMapper<UserBean>(UserBean.class));

	}
}
