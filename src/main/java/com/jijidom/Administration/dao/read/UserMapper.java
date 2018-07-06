package com.jijidom.Administration.dao.read;

import com.jijidom.Administration.entity.User;

import java.util.List;

public interface UserMapper {
    List<User> getAll();
    User insert(User user);
    User findByUserName(String username);
}
