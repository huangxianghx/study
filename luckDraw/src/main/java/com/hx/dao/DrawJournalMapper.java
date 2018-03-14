package com.hx.dao;

import com.hx.model.DrawJournal;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DrawJournalMapper {
    @Insert("INSERT INTO `draw_journal`(`id`,`staff_id`,`staff_name`,`prize_id`,`prize_name`,`draw_time`,`remark`)" +
            "VALUES(#{id},#{staffId},#{staffName},#{prizeId},#{prizeName},#{drawTime},#{remark})")
    int insertDrawJournal(DrawJournal drawJournal);

    @Select("Select `id`,`staff_id`,`staff_name`,`prize_id`,`prize_name`,`draw_time`,`remark` from draw_journal")
    List<DrawJournal> getDrawJournalList();

    @Delete("delete from draw_journal")
    int resetDrawJournalList();
}
