package com.qin.service;

import com.qin.entity.User;
import org.apache.ibatis.annotations.Insert;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface UserService {

    List<User> queryAll();

    List<User> queryByName(String username) throws Exception;

    User Login(String username,String password);
    User findByName(String username);

    int delete(String userid);

    int insert(User user);

    int update(User user);
    List<User> caidan(Integer userid);

}
