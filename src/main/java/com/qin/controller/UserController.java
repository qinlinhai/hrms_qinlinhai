package com.qin.controller;

import com.qin.core.ResultData;
import com.qin.entity.Dept;
import com.qin.entity.User;
import com.qin.service.DeptService;
import com.qin.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;
    @GetMapping("/queryAll")
    public String queryAll(Map map){
        List<User> users = userService.queryAll();
        map.put("list",users);
        return "userList";

    }
    @GetMapping("/queryByName")
    public String queryByName(@RequestParam("username") String username,Map map){
        try {
            List<User> users = userService.queryByName(username);
            map.put("list",users);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "userList";
    }

    @GetMapping("/update")
    public String update(){
        return "userUpdate";
    }
    @GetMapping("login")
    public String login(User user, HttpSession session) {
        //获取Subject
        Subject currentUser= SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            //用户名密码的令牌信息
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
            User byName = userService.findByName(user.getUsername());
            Integer userid = byName.getUserid();

            List<User> caidan = userService.caidan(userid);
            for (User u:caidan
                 ) {
                System.out.println(u.getRname());
                System.out.println(u.getMname());
            }
            session.setAttribute("roleList",caidan);

            //记住我
            token.setRememberMe(true);
            try {
                //调用登录方法：将token ------------->委托给安全管理器进行认证--------------------->进入认证器，调用Realm获取用户信息进行匹配
                currentUser.login(token);

                //该位置手动的设置session
                Session userSession = currentUser.getSession();
                userSession.setAttribute("user",user);

                         /*   Collection<Object> keys = userSession.getAttributeKeys();

//                            DefaultSubjectContext

                            for (Object key : keys) {
                                System.out.println(key);
                            }*/

//                return "forward:/user/queryAll";
                return "main";
            } catch (UnknownAccountException uae) { //账号不存在
                throw new UnknownAccountException("账号不存在");
            } catch (IncorrectCredentialsException ice) {//密码不匹配
                throw new IncorrectCredentialsException("密码不匹配");
            } catch (LockedAccountException lae) {//账户锁定
                throw new LockedAccountException("账户锁定异常");
            } catch (ExcessiveAttemptsException ece) {
                throw new ExcessiveAttemptsException("多次登录锁定账号异常");
            } catch (AuthenticationException ae) { //认证异常
                throw new AuthenticationException("认证异常");
            }
        }
        return "error";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("userid") String userid){
        System.out.println(userid);
        int delete = userService.delete(userid);
        if(delete>0){
            return "forward:/user/queryAll";
        }
        return userid;
    }

    @GetMapping("/insert")
    public String insert(User user){

        int insert = userService.insert(user);
        if(insert>0){
            return "redirect:/user/queryAll";
        }
        return null;
    }

    @GetMapping("/add")
    public String add(){
        return "userUpdate";
    }

    @GetMapping("/findById")
    public String findById(@RequestParam("username") String username,Map map){
        User byName = userService.findByName(username);
        List<Dept> depts = deptService.selectAll();
        List<User> userList=new ArrayList<>();
        userList.add(byName);
        map.put("userList",userList);
        map.put("dList",depts);
        return "update";


    }

    @GetMapping("/updateUser")
    public String update(User user){
        int update = userService.update(user);
        if(update>0){
            return "redirect:/user/queryAll";
        }
        return null;
    }

}
