package com.qin.realm;

import com.qin.entity.User;
import com.qin.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;


public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     *认证：登录
     * @param authenticationToken 从控制层传过来的token:身份信息（用户名，密码）
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获取前端传过来的token信息
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        //从token中获取的用户名
        String username = token.getUsername();

        //涉及到UserService --->UserMapper(根据用户名查询用户对象）
        User dbUser=userService.findByName(username);
        //暂时不提供

        //模拟从数据库中拿到的用户对象可能有也可能没有
//        User dbUser=new User(101,"admin","123456"); //可能是：null
        if (dbUser == null) {
            throw new UnknownAccountException("该用户:"+dbUser.getUsername()+"不存在");
        }

    /*    Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("user","用户信息");*/


        /**
         *          * dbUser.getUserName(), :从数据库中拿到的用户名
         *          * dbUser.getPassword(), 从数据库中拿到的密码(加密后的密码）
         *          ByteSource.Util.bytes(dbUser.getUserName())：当初加密用的盐值
         *          * getName() 获取当前Realm的名字
         */
        //返回一个认证信息的一个实例
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(dbUser.getUsername(),dbUser.getPassword(), ByteSource.Util.bytes(dbUser.getUsername()) ,this.getName());
        return info;
        //认证的过程：交给Shiro的认证器去完成
    }

    /**
     * 获取用户对应的权限信息
     * @param principalCollection 身份集合：可以通过该对象获取认证后的用户信息
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //getPrimaryPrincipal:获取主身份信息，获取用户名
        String userName = principalCollection.getPrimaryPrincipal().toString();

        //根据用户名查询得到该用户对应的角色列表
        // //根据用户名查询得到该用户对应的资源列表
        //UserService ------>UserMapper --->
        //模拟一个集合
     /*   Set<String> dbRoles = new HashSet<>();
        dbRoles.add("人事主管");
        dbRoles.add("库管");*/
        //权限集合：绑定菜单列表

        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

//        Set<String> rolesList = userService.queryRolesByUserName(userName);
        Set<String> rolesList=new HashSet<String>();
        rolesList.add("管理员");
        if (rolesList != null) {
            //绑定用户对应的角色列表
            info.setRoles(rolesList);
        }
        //绑定用户对应的权限列表
//        info.setStringPermissions();
        return info;
    }
}
