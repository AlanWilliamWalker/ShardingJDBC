package com.example.readwrite.model;

/**
 * @author liming.wang
 * 婚姻状况
 */
public enum MaritalEnum {
    /**
     *  未婚
     */
    UNMARRIED(0, "未婚"),
    /**
     * 已婚
     */
    MARRIED(1, "已婚"),
    /**
     *  未知
     */
    OTHER(2, "未知");

    private final int code;

    private final String desc;

    MaritalEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescByCode(Integer code) {
        for (MaritalEnum ele : values()) {
            if(null != code && ele.getCode()== code) {
                return ele.getDesc();
            }
        }
        return null;
    }

    public static Integer getCodeByDesc(String desc) {
        for (MaritalEnum ele : values()) {
            if(ele.getDesc().equals(desc)) {
                return ele.getCode();
            }
        }
        return null;
    }

}
