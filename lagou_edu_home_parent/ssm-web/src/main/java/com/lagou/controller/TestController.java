package com.lagou.controller;

import com.lagou.domain.Test;
import com.lagou.service.TestdaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController//@controller+@ResponseBody
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestdaoService testdaoService;
    @RequestMapping("/findAll")
    public List<Test> findAll(){
        List<Test> testList = testdaoService.findAll();
        return testList;
    }
}
