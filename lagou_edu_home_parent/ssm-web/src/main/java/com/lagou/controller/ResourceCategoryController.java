package com.lagou.controller;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {
    @Autowired
    private ResourceCategoryService resourceCategoryService;

    /**
     * 查询资源分类信息列表
     * @return
     */
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){
        List<ResourceCategory> allResourceCategory = resourceCategoryService.findAllResourceCategory();
        return new ResponseResult(true,200,"查询资源分类信息列表成功",allResourceCategory);
    }
    /**
     * 添加&修改资源分类
     */
    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory){
        if(resourceCategory.getId()==null){
            //添加资源分类
            resourceCategoryService.saveResourceCategory(resourceCategory);
            return new ResponseResult(true,200,"添加资源分类成功",null);
        }else {
            //修改资源分类
            resourceCategoryService.updateResourceCategory(resourceCategory);
            return new ResponseResult(true,200,"修改资源分类成功",null);
        }
    }

    /**
     * 根据id查询资源分类信息
     * @param id
     * @return
     */
    @RequestMapping("/findResourceCategoryById")
    public ResponseResult findResourceCategoryById(Integer id){
        ResourceCategory resourceCategory = resourceCategoryService.findResourceCategoryById(id);
       return new ResponseResult(true,200,"根据id回显资源分类信息成功",resourceCategory);
    }

    /**
     * 根据id删除资源分类
     * @param id
     * @return
     */
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id){
        resourceCategoryService.deleteResourceCategory(id);
       return new ResponseResult(true,200,"删除资源分类成功",null);
    }
}
