package com.lagou.service.impl;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceCategory;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {
    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;
    /**
     * 查询资源分类信息列表
     * @return
     */
    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        return resourceCategoryMapper.findAllResourceCategory();
    }

    /**
     * 添加资源分类
     * @param resourceCategory
     */

    @Override
    public void saveResourceCategory(ResourceCategory resourceCategory) {
        //封装资源分类信息
        Date date = new Date();
        resourceCategory.setCreatedTime(date);
        resourceCategory.setUpdatedTime(date);
        resourceCategory.setCreatedBy("system");
        resourceCategory.setUpdatedBy("system");
        resourceCategoryMapper.saveResourceCategory(resourceCategory);
    }

    /**
     * 修改资源分类
     * @param resourceCategory
     */

    @Override
    public void updateResourceCategory(ResourceCategory resourceCategory) {
        //封装信息
        resourceCategory.setUpdatedTime(new Date());
        resourceCategoryMapper.updateResourceCategory(resourceCategory);
    }

    /**
     * 根据id回显资源分类信息
     * @param id
     * @return
     */

    @Override
    public ResourceCategory findResourceCategoryById(Integer id) {
        return resourceCategoryMapper.findResourceCategoryById(id);

    }

    /**
     * 根据资源分类id删除资源分类信息
     * @param id
     */
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public void deleteResourceCategory(Integer id) {
        //先根据id删除资源信息
        resourceMapper.deleteResourceByCategoryId(id);
        //再删除资源分类信息
        resourceCategoryMapper.deleteResourceCategory(id);
    }

    /**
     * 查询所有资源分类及每个资源分类下的资源信息
     * @return
     */

    @Override
    public List<ResourceCategory> findSubResourceByCategoryId() {
        List<ResourceCategory> allResourceCategory = resourceCategoryMapper.findAllResourceCategory();
        //获取所有资源分类下的资源集合

        for (ResourceCategory resourceCategory : allResourceCategory) {
            List<Resource> resourceList = resourceMapper.findResourceByCategoryId(resourceCategory.getId());
            resourceCategory.setResourceList(resourceList);
        }
        return allResourceCategory;
    }

}
