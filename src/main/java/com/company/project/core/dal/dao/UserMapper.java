package com.company.project.core.dal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.company.project.core.dal.model.User;
import com.company.project.core.dal.util.Mapper;

public interface UserMapper extends Mapper<User> {
	@Select("select * from user")
	public  List<User> test();
	
	@Select("select * from user")
	public  List<User> test1();
}