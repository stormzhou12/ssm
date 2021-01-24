package com.lagou.service.impl;

import com.lagou.dao.Testdao;
import com.lagou.domain.Test;
import com.lagou.service.TestdaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestdaoServiceImpl implements TestdaoService {
    @Autowired
    private Testdao testdao;
    @Override
    public List<Test> findAll() {
        return testdao.findAll();
    }
}
