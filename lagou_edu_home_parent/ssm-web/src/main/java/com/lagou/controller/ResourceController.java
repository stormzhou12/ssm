package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResourseVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    /**
     * 资源信息分页&条件查询
     * @param resourseVo
     * @return
     */
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourseVo resourseVo){
        PageInfo<Resource> pageInfo = resourceService.findAllResourceByPage(resourseVo);
        ResponseResult result = new ResponseResult(true, 200, "资源信息分页&条件查询成功", pageInfo);
        return result;
    }

}
