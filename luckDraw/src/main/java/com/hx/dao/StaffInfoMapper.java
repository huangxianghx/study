package com.hx.dao;

import com.hx.model.StaffInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StaffInfoMapper {
    @Select("SELECT * FROM staff_info WHERE id = #{id}")
    StaffInfo getStaffInfo(@Param("id") int id);

    @Select("SELECT * FROM staff_info")
    List<StaffInfo> getStaffList();


    @Select("SELECT * FROM staff_info WHERE status=0")
    List<StaffInfo> getUnDrawStaffList();

    @Select("SELECT * FROM staff_info WHERE status=1")
    List<StaffInfo> getDrawedStaffList();

    @Update("update staff_info set status=#{status} WHERE id = #{id}")
    int updateStaffInfo(StaffInfo staffInfo);

    @Update("update staff_info set status=0")
    int resetStaffInfoList();
}
