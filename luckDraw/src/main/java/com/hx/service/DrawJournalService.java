package com.hx.service;

import com.hx.dao.DrawJournalMapper;
import com.hx.model.DrawJournal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrawJournalService {
    @Autowired
    DrawJournalMapper drawJournalMapper;
    /**
     * 新增获奖流水
     * @return
     */
    public int addDrawJournal(DrawJournal drawJournal){
        return drawJournalMapper.insertDrawJournal(drawJournal);
    }

    /**
     * 获取获奖流水列表
     * @return
     */
    public List<DrawJournal> getDrawJournalList(){
        return drawJournalMapper.getDrawJournalList();
    }

    /**
     * 重设
     */
    public void resetDrawJournalList(){
        drawJournalMapper.resetDrawJournalList();
    }
}
