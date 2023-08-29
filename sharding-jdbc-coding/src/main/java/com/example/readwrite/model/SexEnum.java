package com.example.readwrite.model;

/**
 * @author liming.wang
 * 性别枚举
 */
public enum SexEnum {
    /**
     *  女
     */
    WOMAN(0, "女"),
    /**
     * 男
     */
    MAN(1, "男"),
    /**
     * 其他
     */
    OTHER(2, "其他");

    private final int code;

    private final String desc;

    SexEnum(int code, String desc) {
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
        for (SexEnum ele : values()) {
            if(null != code && ele.getCode()== code) {
                return ele.getDesc();
            }
        }
        return null;
    }

    public static Integer getCodeByDesc(String desc) {
        for (SexEnum ele : values()) {
            if(ele.getDesc().equals(desc)) {
                return ele.getCode();
            }
        }
        return null;
    }

}
