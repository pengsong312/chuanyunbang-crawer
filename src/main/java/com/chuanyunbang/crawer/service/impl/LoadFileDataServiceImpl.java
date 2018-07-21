package com.chuanyunbang.crawer.service.impl;

import com.chuanyunbang.crawer.constant.BaseConstants;
import com.chuanyunbang.crawer.dao.WeChatDataDao;
import com.chuanyunbang.crawer.entity.WeChatData;
import com.chuanyunbang.crawer.service.LoadFileDataService;
import com.chuanyunbang.crawer.util.DataAnalyzeUtils;
import com.chuanyunbang.crawer.util.ParamUtils;
import com.chuanyunbang.crawer.util.ThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Objects;

/**
 * @author Luffy
 * @date 2018/7/12
 * @description 数据加载
 */
@Service("loadFileDataService")
public class LoadFileDataServiceImpl implements LoadFileDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoadFileDataServiceImpl.class);

    @Autowired
    private WeChatDataDao weChatDataDao;

    @Override
    public void loadData(String filePath, String fileName) {
        String fileLocation = filePath + fileName;
        FileInputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = new FileInputStream(fileLocation);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"),1024);

            StringBuilder msg = new StringBuilder();
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                if (StringUtils.isEmpty(str)) {
                    continue;
                }
                // 取出单条消息
                if (BaseConstants.MSG_DELIMITER.equals(str)) {
                    if (StringUtils.hasLength(msg)) {
                        WeChatData weChatData = DataAnalyzeUtils.analyze(msg.toString());
                        if (!Objects.isNull(weChatData)) {
                            ThreadFactory.execute(new Runnable() {
                                @Override
                                public void run() {
                                    int id = weChatDataDao.insertAnalyzeResult(ParamUtils.covertWeChatDataToWeChatPo(weChatData));
                                }
                            });


                        }
                        msg = new StringBuilder();
                    }
                    continue;
                }
                msg.append(str);
                msg.append(BaseConstants.MSG_LINE_CHARACTOR);
            }
        } catch (IOException e) {
            LOGGER.error("LoadFileDataServiceImpl loadData error : {}", e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    LOGGER.error("LoadFileDataServiceImpl loadData error : {}", e);
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error("LoadFileDataServiceImpl loadData error : {}", e);
                }
            }
        }
    }

    public static void main(String[] args) {
        new LoadFileDataServiceImpl().loadData("/Users/luffy/chuanyunbang/weixin/", "20180704_group_msg.log");
    }
}
