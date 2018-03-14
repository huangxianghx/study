package com.hx.service;

import com.hx.dao.StaffInfoMapper;
import com.hx.model.StaffInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {
    @Autowired
    StaffInfoMapper staffInfoMapper;

    /**
     * 获员工列表
     * @return
     */
    public List<StaffInfo> getStaffList(){
        return staffInfoMapper.getStaffList();
    }

    /**
     * 获未中奖过的员工列表
     * @return
     */
    public List<StaffInfo> getUnDrawStaffList(){
        return staffInfoMapper.getUnDrawStaffList();
    }

    /**
     * 获中奖过的员工列表
     * @return
     */
    public List<StaffInfo> getDrawedStaffList(){
        return staffInfoMapper.getDrawedStaffList();
    }

    /**
     * 获员工信息
     * @return
     */
    public StaffInfo getStaffInfo(int id){
        return staffInfoMapper.getStaffInfo(id);
    }

    /**
     * 更新员工状态
     * @return
     */
    public int updateStaffInfo(StaffInfo staffInfo){
        return staffInfoMapper.updateStaffInfo(staffInfo);
    }

    /**
     * 重设
     * @return
     */
    public void resetStaffInfoList(){
        staffInfoMapper.resetStaffInfoList();
    }
}
