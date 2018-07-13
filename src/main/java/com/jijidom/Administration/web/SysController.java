package com.jijidom.Administration.web;

import com.jijidom.Administration.entity.User;
import com.jijidom.Administration.jpa.UserJPA;
import com.jijidom.Administration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SysController {
    @Autowired
    private UserService userService;
    @Autowired
    UserJPA userJPA;

    @RequestMapping(value="/login")
    public ModelAndView login(ModelMap map){
        return new ModelAndView("html/login",map);
    }

    @RequestMapping(value="/home")
    public ModelAndView root(ModelMap map) {
        map.put("title", "登录页面");
        map.put("content", "123");
        map.put("etraInfo", "456");
        return new ModelAndView("html/home",map);
    }

    @ResponseBody
    @RequestMapping(value="/getAllUser",method=RequestMethod.GET)
    public List<User> getUser(Integer page, Integer rows) {
        userJPA.findAll();
        return userService.getAllUser();
    }
}
