package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class PromotionAdServiceImpl implements PromotionAdService {
    @Autowired
    private PromotionAdMapper promotionAdMapper;

    /**
     * 分页获取广告列表数据
     * @param promotionAdVo
     * @return
     */
    @Override
    public PageInfo<PromotionAd> findAllAdByPage(PromotionAdVo promotionAdVo) {
        PageHelper.startPage(promotionAdVo.getCurrentPage(),promotionAdVo.getPageSize());
        List<PromotionAd> promotionAds = promotionAdMapper.findAllAdByPage();
        PageInfo<PromotionAd> pageInfo = new PageInfo<>(promotionAds);
        return pageInfo;
    }

    /**
     * 修改广告上下线状态
     * @param id
     * @param status
     */


    @Override
    public void updatePromotionAdStatus(int id,int status) {
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAd.setUpdateTime(new Date());
        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }
}
