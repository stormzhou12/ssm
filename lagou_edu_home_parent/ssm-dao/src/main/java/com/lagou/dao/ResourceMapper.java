package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourseVo;

import java.util.List;

public interface ResourceMapper {
    /*
    资源信息分页&条件查询
     */
    public List<Resource> findAllResourceByPage(ResourseVo resourseVo);
   /*
   根据资源分类id删除资源信息
    */
   public void deleteResourceByCategoryId(Integer id);
   /*
   根据资源分类id查询资源信息
    */
   public List<Resource> findResourceByCategoryId(Integer categoryId);

}
