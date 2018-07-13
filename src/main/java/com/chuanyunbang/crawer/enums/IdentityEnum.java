package com.chuanyunbang.crawer.enums;

/**
 * @author Luffy
 * @date 2018/7/9
 * @description 身份
 */
public enum IdentityEnum {

    SHIP_OWNER(0, "船主"), GOODS_OWNER(1, "货主"),OTHER_OWNER(2, "未知");


    /**
     * 0-船主 1-货主
     **/
    private int id;
    private String desc;

    IdentityEnum(int id, String desc) {
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

    public IdentityEnum parse(int id) {
        for (IdentityEnum identityEnum : IdentityEnum.values()) {
            if (identityEnum.getId() == id) {
                return identityEnum;
            }
        }
        return null;
    }
}
