package com.spring.boot.transaction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.transaction.model.User;

@Service
public class UserService 
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional
	public void insert(List<User> users)
	{
		users.stream().forEach(user ->{
			System.out.println("inserting data for user name "+ user.getName());
			jdbcTemplate.update("insert into USER(Name,Dept,Salary) values(?,?,?)",
						ps ->
						{
							ps.setString(1,user.getName());
							ps.setString(2,user.getDept());
							ps.setLong(3,user.getSalary());
						});
			});
	}
	
	public List<User> getUsers()
	{
		System.out.println("Retrieve All User List");
		List<User> userList = jdbcTemplate.query("select Name,Dept,Salary from USER",
			(rs, i) ->new User(rs.getString("Name"),rs.getString("Dept"),
					rs.getLong("Salary")));
		return userList;
	}
	
}
