package com.hx.service;

import com.hx.dao.PrizeInfoMapper;
import com.hx.model.PrizeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrizeService {
    @Autowired
    PrizeInfoMapper prizeInfoMapper;

    /**
     * 获奖品列表
     * @return
     */
    public List<PrizeInfo> getPrizeList(){
        return prizeInfoMapper.getPrizeList();
    }

    /**
     * 获未抽中奖品列表
     * @return
     */
    public List<PrizeInfo> getUnDrawPrizeList(){
        return prizeInfoMapper.getUnDrawPrizeList();
    }

    /**
     * 获已抽中奖品列表
     * @return
     */
    public List<PrizeInfo> getDrawedPrizeList(){
        return prizeInfoMapper.getDrawedPrizeList();
    }

    /**
     * 获取奖品
     * @return
     */
    public PrizeInfo getPrizeInfo(int prizeId){
        return prizeInfoMapper.getPrizeInfo(prizeId);
    }

    /**
     * 更新奖品状态
     * @return
     */
    public int updatePrizeInfo(PrizeInfo prizeInfo){
        return prizeInfoMapper.updatePrizeInfo(prizeInfo);
    }

    /**
     * 重设
     * @return
     */
    public void resetPrizeInfoList(){
        prizeInfoMapper.resetPrizeInfoList();
    }
}
