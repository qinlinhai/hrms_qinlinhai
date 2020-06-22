package com.qin.controller;

import com.qin.entity.Dept;
import com.qin.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;
    @GetMapping("/selectAll")
    public String selectAll(Map map){
        List<Dept> depts = deptService.selectAll();
        map.put("deptList",depts);
        return "userUpdate";
    }

}
