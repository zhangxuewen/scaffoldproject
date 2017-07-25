package com.company.project.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.project.core.dal.dao.UserMapper;
import com.company.project.core.dal.model.User;
import com.company.project.core.service.UserService;
import com.company.project.core.service.base.AbstractService;


/**
 * Created by CodeGenerator on 2017/07/03.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

	@Override
	public List<User> test() {
		return userMapper.test();
		
		
	}

}
