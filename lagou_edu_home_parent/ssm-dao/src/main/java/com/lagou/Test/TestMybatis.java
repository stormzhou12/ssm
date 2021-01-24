package com.lagou.Test;

import com.lagou.dao.Testdao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {
    @Test
    public void test01() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Testdao testdao = sqlSession.getMapper(Testdao.class);
        List<com.lagou.domain.Test> testList = testdao.findAll();
        for (com.lagou.domain.Test test : testList) {
            System.out.println(test);
        }
    }
}
