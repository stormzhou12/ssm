package com.lagou.dao;

import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceMapper {
    /*
    获取广告位列表信息
     */
    public List<PromotionSpace> findAllPromotionSpace();
    /*
    添加广告位
     */
    public void savePromotionSpace(PromotionSpace promotionSpace);
    /*
    回显广告位名称
     */
    public PromotionSpace findPromotionSpaceById(int id);
    /*
    修改广告位
     */
    public void updatePromotionSpace(PromotionSpace promotionSpace);
}
