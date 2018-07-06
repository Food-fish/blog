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

import java.util.List;

@Controller
public class SysController {
    @Autowired
    private UserService userService;
    @Autowired
    UserJPA userJPA;

    /*@RequestMapping(value="/login",method=RequestMethod.GET)
    public ModelAndView login(ModelMap map){
        return new ModelAndView("html/login",map);
    }*/

    @RequestMapping(value="/home",method=RequestMethod.GET)
    public ModelAndView root(ModelMap map) {
        map.put("msg", "登录页面");
        System.out.println("登录成功");
        return new ModelAndView("html/home",map);
    }

    @ResponseBody
    @RequestMapping(value="/getAllUser",method=RequestMethod.GET)
    public List<User> getUser(Integer page, Integer rows) {
        userJPA.findAll();
        return userService.getAllUser();
    }
}
