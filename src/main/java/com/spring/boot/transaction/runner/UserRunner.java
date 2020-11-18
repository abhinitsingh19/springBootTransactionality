package com.spring.boot.transaction.runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.transaction.model.User;
import com.spring.boot.transaction.service.UserService;

@Component
public class UserRunner implements CommandLineRunner 
{
	@Autowired
	private UserService UserService;
	@Override
	public void run(String... args) throws Exception 
	{
		try 
		{
			List<User> users = Arrays.asList(
					new User("Abhi","t3000",20000),
					new User("Abhinit","daac",22000));

			UserService.insert(users);
		}
		catch(RuntimeException e)
		{
			System.out.println("Exception occured in  batch1 "+ e.getMessage());
		}
		
		try 
		{
			List<User> users = Arrays.asList(
					new User("Abhineet","em",24000),
					new User("Abhijeet","systech",25000));

			UserService.insert(users);
		}
		catch(RuntimeException e)
		{
			System.out.println("Exception occured in  batch2"+ e.getMessage());
		}
		
		System.out.println(UserService.getUsers());
		
	}

}
