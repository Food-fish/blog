package com.jijidom.Administration.service;

import com.jijidom.Administration.dao.read.UserMapper;
import com.jijidom.Administration.entity.User;
import com.jijidom.Administration.annotation.TargetDataSource;
import com.jijidom.Administration.jpa.PermissionJPA;
import com.jijidom.Administration.jpa.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserJPA userJPA;
    @Autowired
    private PermissionJPA permissionJPA;

    @Transactional
    @TargetDataSource(dataSource = "userDataSource")
    public boolean createUser(User user) {
        userMapper.insert(user);
        return true;
    }
    @Transactional
    @TargetDataSource(dataSource = "userDataSource")
    public List<User> getAllUser() {
        return userMapper.getAll();
    }

    /*@TargetDataSource(dataSource = "read1DataSource")
    public List<Student> getByPage(int page, int rows) {
        Page<Student> studentPage = PageHelper.startPage(page, rows, true);
        List<Student> students = studentMapper.getBypage();
        System.out.println("-------------------" + studentPage.toString() + "-----------");
        return students;
    }*/
}
