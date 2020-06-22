package com.qin.mapper;



import com.qin.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    User selectByPrimaryKey(Integer userid);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
    @Select("select u.*,d.dname from tb_user u join tb_dept d on u.deptid=d.deptid where u.status=1")
    List<User> queryAll();
    List<User> queryByName(String username);
    @Select("select * from tb_user where username=#{username} and password=#{password} and status=1")
    User login(String username,String password);
    @Select("select * from tb_user where username=#{username} and status=1")
    User findByName(String username);
    @Update("update tb_user set status=0 where userid=#{userid}")
    int delete(Integer userid);
    @Update("update tb_user set username=#{username},phonenum=#{phonenum},email=#{email},deptid=#{deptid} where userid=#{userid}")
    int update(User user);
    @Select("select u.*,r.rname,m.mname from  tb_user u join role_user  ru on u.userid=ru.uid join role r on" +
            " ru.rid=r.roleid join role_menu rm on r.roleid=rm.rid join menu m on rm.mid=m.menuid where u.userid=#{userid}")
    List<User> caidan(Integer userid);

}