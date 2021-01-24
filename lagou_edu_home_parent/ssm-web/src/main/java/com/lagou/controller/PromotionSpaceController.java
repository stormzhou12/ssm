package com.lagou.controller;

import com.lagou.domain.PromotionSpace;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {
    @Autowired
    private PromotionSpaceService promotionSpaceService;
    /*
   获取广告位列表信息
    */
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){
        List<PromotionSpace> promotionSpaceList = promotionSpaceService.findAllPromotionSpace();
        ResponseResult result = new ResponseResult(true, 200, "响应成功", promotionSpaceList);
        return result;
    }
    /*
    根据ID回显广告位名称
     */
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(int id){
        PromotionSpace promotionSpaceById = promotionSpaceService.findPromotionSpaceById(id);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", promotionSpaceById);
        return result;
    }
    /*
    添加&修改广告位
     */
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){
        if(promotionSpace.getId()==null){
            //添加广告位
            promotionSpaceService.savePromotionSpace(promotionSpace);
            ResponseResult result = new ResponseResult(true, 200, "添加广告位成功", null);
            return result;
        }else {
            promotionSpaceService.updatePromotionSpace(promotionSpace);
            ResponseResult result = new ResponseResult(true, 200, "修改广告位成功", null);
            return result;
        }
    }
}
