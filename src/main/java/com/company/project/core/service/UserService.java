package com.company.project.core.service;
import java.util.List;

import com.company.project.core.dal.model.User;
import com.company.project.core.service.base.Service;


/**
 * Created by CodeGenerator on 2017/07/03.
 */
public interface UserService extends Service<User> {
    public  List<User> test ();
}
