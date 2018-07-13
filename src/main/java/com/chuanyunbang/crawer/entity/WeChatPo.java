package com.chuanyunbang.crawer.entity;

import lombok.*;

/**
 * @author Luffy
 * @date 2018/7/13
 * @description 持久层对象
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeChatPo {

    private int id;
    private String telNums;
    private int identity;
    private String msg;
    private long updateTime;
    private long createTime;

    @Override
    public String toString() {
        return "WeChatPo{" + "id=" + id + ", telNums='" + telNums + '\'' + ", identity=" + identity + ", msg='" + msg + '\'' + ", updateTime="
                + updateTime + ", createTime=" + createTime + '}';
    }
}
