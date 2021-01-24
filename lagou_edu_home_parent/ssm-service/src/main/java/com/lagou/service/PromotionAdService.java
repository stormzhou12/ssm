package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;

import java.util.List;

public interface PromotionAdService {
    /**
     * 分页获取广告列表数据
     */
    public PageInfo<PromotionAd> findAllAdByPage(PromotionAdVo promotionAdVo);
    /**
     * 修改广告上下线状态
     */

    public void updatePromotionAdStatus(int id,int status);
}
