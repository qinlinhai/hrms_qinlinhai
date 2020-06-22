package com.qin.service.impl;

import com.qin.entity.Dept;
import com.qin.mapper.DeptMapper;
import com.qin.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<Dept> selectAll() {
        return deptMapper.selectAll();
    }
}
