package com.example.readwrite.model;

/**
 * @author liming.wang
 * 手机是否常用
 */
public enum PhonePrimaryEnum {
    /**
     *  不常用
     */
    NO(0, "不常用"),
    /**
     * 常用
     */
    YES(1, "常用");

    private final int code;

    private final String desc;

    PhonePrimaryEnum(int code, String desc) {
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
        for (PhonePrimaryEnum ele : values()) {
            if(null != code && ele.getCode()== code) {
                return ele.getDesc();
            }
        }
        return null;
    }

    public static Integer getCodeByDesc(String desc) {
        for (PhonePrimaryEnum ele : values()) {
            if(ele.getDesc().equals(desc)) {
                return ele.getCode();
            }
        }
        return null;
    }

}
