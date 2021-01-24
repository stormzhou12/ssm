package com.lagou.service;

import com.lagou.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryService {
    /*
    查询资源分类信息列表
     */
    public List<ResourceCategory> findAllResourceCategory();
    /*
    添加资源分类
     */
    public void saveResourceCategory(ResourceCategory resourceCategory);
    /*
    修改资源分类
     */
    public void updateResourceCategory(ResourceCategory resourceCategory);
    /*
    根据id回显资源分类信息
     */
    public ResourceCategory findResourceCategoryById(Integer id);
    /*
    根据资源分类id删除资源分类信息
     */
    public void deleteResourceCategory(Integer id);
    /*
    查询所有资源分类及每个资源分类下的资源信息
     */
    public List<ResourceCategory> findSubResourceByCategoryId();
}
