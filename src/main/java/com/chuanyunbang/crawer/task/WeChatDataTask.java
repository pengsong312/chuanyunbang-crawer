package com.chuanyunbang.crawer.task;

import com.chuanyunbang.crawer.constant.BaseConstants;
import com.chuanyunbang.crawer.service.LoadFileDataService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Luffy
 * @date 2018/7/9
 * @description 分析抓取微信聊天记录的数据
 */
@Component
public class WeChatDataTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatDataTask.class);

    @Autowired
    private LoadFileDataService loadFileDataService;
    private  static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    @PostConstruct
    public void init(){
        System.out.println("当前时间：" + dateFormat.format(new Date()));
    }

    /**
     * 每天凌晨1点分析前一天的数据
     * 0/1 0 * * * ?
     */
    @Scheduled(cron = "*/5 * * * * ?")
    public void analysisWeChatData() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("WeChatDataTask analysisWeChatData 开始分析{}的数据 ...", new DateTime().minusDays(1).toLocalDate().toString("yyyyMMdd"));
        }
        String fileName = new DateTime().minusDays(1).toLocalDate().toString("yyyyMMdd") + BaseConstants.FILE_SUFFIX;
        loadFileDataService.loadData(BaseConstants.FILE_PATH, fileName);
    }
}
