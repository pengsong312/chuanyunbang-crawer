package com.chuanyunbang.crawer.entity;

import com.chuanyunbang.crawer.enums.IdentityEnum;
import lombok.*;

import java.util.Set;

/**
 * @author pengsong
 * @date 2018/7/9
 * @description 微信聊天信息
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeChatData {

    /**
     * 电话号码
     **/
    private Set<String> telNums;

    /**
     * 身份
     **/
    private IdentityEnum identityEnum;

    /**
     * 发送的消息详情
     **/
    private String msg;

    @Override
    public String toString() {
        return "WeChatData{" + "telNums=" + telNums + ", identityEnum=" + identityEnum + ", msg='" + msg + '\'' + '}';
    }
}
