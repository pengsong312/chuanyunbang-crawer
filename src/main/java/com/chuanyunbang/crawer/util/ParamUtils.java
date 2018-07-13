package com.chuanyunbang.crawer.util;

import com.chuanyunbang.crawer.entity.WeChatData;
import com.chuanyunbang.crawer.entity.WeChatPo;
import com.google.gson.Gson;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.UUID;

/**
 * @author pengsong
 * @date 2018/4/8
 * @description 参数工具类
 */
public class ParamUtils {

    public static final boolean paramIsEmpty(String... params) {
        for (String param : params) {
            if (StringUtils.isEmpty(param)) {
                return true;
            }
        }
        return false;
    }

    public static String logId() {
        String uuid = UUID.randomUUID().toString();
        StringBuilder sb = new StringBuilder();
        sb.append(uuid, 0, 8);
        sb.append(uuid, 9, 13);
        sb.append(uuid, 14, 18);
        sb.append(uuid, 19, 23);
        sb.append(uuid, 24, uuid.length());
        return sb.toString();
    }

    public static WeChatPo covertWeChatDataToWeChatPo(WeChatData weChatData) {
        if (Objects.isNull(weChatData)) {
            return null;
        }
        long covertTime = System.currentTimeMillis() / 1000L;
        return WeChatPo.builder().telNums(new Gson().toJson(weChatData.getTelNums())).identity(weChatData.getIdentityEnum().getId())
                .msg(weChatData.getMsg()).createTime(covertTime).updateTime(covertTime).build();
    }
}
