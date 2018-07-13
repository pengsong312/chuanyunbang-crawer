package com.chuanyunbang.crawer.dao;

import com.chuanyunbang.crawer.entity.WeChatPo;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.Rename;
import org.jfaster.mango.annotation.ReturnGeneratedId;
import org.jfaster.mango.annotation.SQL;

/**
 * @author pengsong
 * @date 2018/4/9
 * @description 插入数据分析结果
 * @table wechat_data
 */
@DB(table = "wechat_data", name = "dataSource")
public interface WeChatDataDao {

    /**
     * 插入分析结果
     *
     * @param weChatPo 分析的数据结果
     * @return id
     */
    @ReturnGeneratedId
    @SQL("insert into #table(tel_nums,identity,msg,create_time,update_time) values(:wp.telNums,:wp.identity,:wp.msg,:wp.createTime,:wp.updateTime)")
    int insertAnalyzeResult(@Rename("wp") WeChatPo weChatPo);

}
