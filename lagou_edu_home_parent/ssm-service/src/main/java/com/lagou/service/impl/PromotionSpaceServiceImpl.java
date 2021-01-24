package com.lagou.service.impl;

import com.lagou.dao.PromotionSpaceMapper;
import com.lagou.domain.PromotionSpace;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {
    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;
    /**
     * 获取广告位列表数据
     * @return
     */
    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        return promotionSpaceMapper.findAllPromotionSpace();
    }

    /**
     * 添加广告位
     * @param promotionSpace
     */

    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {
        //封装PromotionSpace
        UUID uuid = UUID.randomUUID();
        promotionSpace.setSpaceKey(uuid.toString());
        promotionSpace.setCreateTime(new Date());
        promotionSpace.setUpdateTime(new Date());
        promotionSpace.setIsDel(0);
        promotionSpaceMapper.savePromotionSpace(promotionSpace);

    }

    /**
     * 根据ID回显广告位名称
     * @param id
     * @return
     */

    @Override
    public PromotionSpace findPromotionSpaceById(int id) {
        return promotionSpaceMapper.findPromotionSpaceById(id);
    }

    /**
     * 修改广告位
     * @param promotionSpace
     */

    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {
        //封装PromotionSpace
        promotionSpace.setUpdateTime(new Date());
        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }
}
