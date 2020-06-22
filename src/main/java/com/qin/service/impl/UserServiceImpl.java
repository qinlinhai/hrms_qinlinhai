package com.qin.service.impl;

import com.qin.entity.User;
import com.qin.mapper.UserMapper;
import com.qin.service.UserService;
import com.qin.util.PassWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> queryAll() {
        return userMapper.queryAll();
    }

    @Override
    public List<User> queryByName(String username) throws Exception {
        if(username==null && username == ""){
            throw new Exception("姓名不能为空");
        }
        return userMapper.queryByName(username);
    }

    @Override
    public User Login(String username, String password) {
        return userMapper.login(username,password);
    }

    @Override
    public User findByName(String username) {
        return userMapper.findByName(username);
    }

    @Override
    public int delete(String userid) {
        Integer id=Integer.parseInt(userid);
        return userMapper.delete(id);
    }

    @Override
    public int insert(User user) {
        user.setStatus(1);
        user.setPassword(PassWord.encPassword("123456",user.getUsername()));
        user.setSalt(user.getUsername());
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public List<User> caidan(Integer userid) {
        return userMapper.caidan(userid);
    }
}
