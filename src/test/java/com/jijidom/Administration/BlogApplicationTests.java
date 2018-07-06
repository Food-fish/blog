package com.jijidom.Administration;

import com.jijidom.Administration.annotation.TargetDataSource;
import com.jijidom.Administration.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {
    @Autowired
    private UserService userService;
    @Test
    @Transactional
    @TargetDataSource(dataSource = "userDataSource")
    public void contextLoads() {
        userService.getAllUser();
    }

}
