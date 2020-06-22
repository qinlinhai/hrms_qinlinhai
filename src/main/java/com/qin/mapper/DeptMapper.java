package com.qin.mapper;



import com.qin.entity.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DeptMapper {
    int deleteByPrimaryKey(Integer deptid);

    int insert(Dept record);

    Dept selectByPrimaryKey(Integer deptid);

    List<Dept> selectAll();

    int updateByPrimaryKey(Dept record);
}