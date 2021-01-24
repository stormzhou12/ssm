package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourseVo;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 资源信息分页&条件查询
     * @param resourseVo
     * @return
     */
    @Override
    public PageInfo<Resource> findAllResourceByPage(ResourseVo resourseVo) {
        PageHelper.startPage(resourseVo.getCurrentPage(),resourseVo.getPageSize());
        List<Resource> allResourceByPage = resourceMapper.findAllResourceByPage(resourseVo);
        PageInfo<Resource> pageInfo = new PageInfo<>(allResourceByPage);
        return pageInfo;
    }


}
