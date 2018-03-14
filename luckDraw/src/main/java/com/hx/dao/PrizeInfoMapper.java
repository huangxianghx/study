package com.hx.dao;

import com.hx.model.PrizeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PrizeInfoMapper {
    @Select("SELECT * FROM prize_info WHERE id = #{id}")
    PrizeInfo getPrizeInfo(@Param("id") int id);

    @Select("SELECT * FROM prize_info")
    List<PrizeInfo> getPrizeList();


    @Select("SELECT * FROM prize_info WHERE status = 0")
    List<PrizeInfo> getUnDrawPrizeList();

    @Select("SELECT * FROM prize_info WHERE status = 1")
    List<PrizeInfo> getDrawedPrizeList();

    @Update("update prize_info set status=#{status} WHERE id = #{id}")
    int updatePrizeInfo(PrizeInfo prizeInfo);

    @Update("update prize_info set status=0")
    int resetPrizeInfoList();
}
