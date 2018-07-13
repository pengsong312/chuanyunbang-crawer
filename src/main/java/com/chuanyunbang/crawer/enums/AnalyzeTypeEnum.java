package com.chuanyunbang.crawer.enums;

/**
 * @author Luffy
 * @date 2018/7/9
 * @description 判断是船主还是货主分析开关
 */
public enum AnalyzeTypeEnum {

    SHIP_OWNER_TYPE(0, "船主分析法"), GOODS_OWNER_TYPE(1, "货主分析法");


    /**
     * 0-船主 1-货主
     **/
    private int id;
    private String desc;

    AnalyzeTypeEnum(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public AnalyzeTypeEnum parse(int id) {
        for (AnalyzeTypeEnum identityEnum : AnalyzeTypeEnum.values()) {
            if (identityEnum.getId() == id) {
                return identityEnum;
            }
        }
        return null;
    }
}
