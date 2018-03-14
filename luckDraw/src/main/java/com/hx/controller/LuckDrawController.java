package com.hx.controller;

import com.hx.model.DrawJournal;
import com.hx.model.PrizeInfo;
import com.hx.model.StaffInfo;
import com.hx.service.DrawJournalService;
import com.hx.service.PrizeService;
import com.hx.service.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class LuckDrawController {
    protected static final Logger log = LoggerFactory.getLogger(LuckDrawController.class);

    @Autowired
    DrawJournalService drawJournalService;
    @Autowired
    StaffService staffService;
    @Autowired
    PrizeService prizeService;

    /**
     * 整批抽奖
     * @return
     */
    @RequestMapping(value = "/batchDraw" ,method = RequestMethod.POST)
    public String batchDraw() {
        List<PrizeInfo> prizeInfoList=prizeService.getUnDrawPrizeList();
        StringBuffer prizeNames=new StringBuffer();
        for(int i=0;i<prizeInfoList.size();i++){
            prizeNames.append(prizeInfoList.get(i).getName()+"、");
        }
        log.info("当前奖品池还有:[{}]",prizeNames);
        for(PrizeInfo prizeInfo:prizeInfoList){
            log.info("现在开始抽取奖品:[{}]",prizeInfo.getName());
            StaffInfo luckBoy = genLuckBoy();
            DrawJournal drawJournal = new DrawJournal();
            drawJournal.setPrizeId(prizeInfo.getId());
            drawJournal.setPrizeName(prizeInfo.getName());
            drawJournal.setStaffId(luckBoy.getId());
            drawJournal.setStaffName(luckBoy.getName());
            drawJournal.setDrawTime(new Date());
            drawJournal.setRemark("员工:[" + luckBoy.getName() + "]抽中奖品:[" + prizeInfo.getName() + "]");
            log.info("恭喜员工:[{}]抽中奖品:[{}]",luckBoy.getName(),prizeInfo.getName());

            drawJournalService.addDrawJournal(drawJournal);

            luckBoy.setStatus("1");
            staffService.updateStaffInfo(luckBoy);

            prizeInfo.setStatus("1");
            prizeService.updatePrizeInfo(prizeInfo);
        }

        getDrawJournalList();
        return null;
    }

    /**
     * 获取最终获奖名单
     */
    public void getDrawJournalList(){
        List<DrawJournal> drawJournalList=drawJournalService.getDrawJournalList();
        log.info("最终获奖名单:");
        for(DrawJournal drawJournal:drawJournalList){
            log.info(drawJournal.getRemark());
        }
    }

    /**
     * 单次抽奖
     * @return
     */
    @RequestMapping(value = "/draw" ,method = RequestMethod.POST)
    public String draw(@RequestParam int prizeId) {
        PrizeInfo prizeInfo=prizeService.getPrizeInfo(prizeId);
        log.info("现在开始抽取奖品:[{}]",prizeInfo.getName());
        StaffInfo luckBoy = genLuckBoy();
        DrawJournal drawJournal = new DrawJournal();
        drawJournal.setPrizeId(prizeInfo.getId());
        drawJournal.setPrizeName(prizeInfo.getName());
        drawJournal.setStaffId(luckBoy.getId());
        drawJournal.setStaffName(luckBoy.getName());
        drawJournal.setDrawTime(new Date());
        drawJournal.setRemark("员工:[" + luckBoy.getName() + "]抽中奖品:[" + prizeInfo.getName() + "]");
        log.info("******恭喜员工:[{}]抽中奖品:[{}]******",luckBoy.getName(),prizeInfo.getName());

        luckBoy.setStatus("1");
        staffService.updateStaffInfo(luckBoy);

        prizeInfo.setStatus("1");
        prizeService.updatePrizeInfo(prizeInfo);

        return null;
    }

    /**
     * 抽取幸运观众
     * @return
     */
    public StaffInfo genLuckBoy(){
        List<StaffInfo> staffList = staffService.getUnDrawStaffList();
        StringBuffer names=new StringBuffer();
        for(int i=0;i<staffList.size();i++){
            names.append(staffList.get(i).getName()+"、");
        }
        log.info("当前未中奖的员工还有:[{}]",names);
        int index = (int) (Math.random() * staffList.size());
        int luckBoy = staffList.get(index).getId();
        StaffInfo staffInfo = staffService.getStaffInfo(luckBoy);
        return staffInfo;
    }

    /**
     * 抽奖结果列表展示
     */
    @RequestMapping(value = "/drawJournalList" ,method = RequestMethod.POST)
    public String drawJournalList() {
        return null;
    }

    /**
     * 重设
     */
    @RequestMapping(value = "/resetDraw" ,method = RequestMethod.POST)
    public String resetDraw() {
        log.info("重新开始整个抽奖流程");
        prizeService.resetPrizeInfoList();
        staffService.resetStaffInfoList();
        drawJournalService.resetDrawJournalList();
        return null;
    }
}
