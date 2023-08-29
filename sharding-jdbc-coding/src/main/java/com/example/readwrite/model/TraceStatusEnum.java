package com.example.readwrite.model;

/**
 * @author liming.wang
 * 分发状态
 */
public enum TraceStatusEnum {

    /**
     *  成功
     */
    SUCCESS(1, "成功"),
    /**
     *  失败
     */
    FAILED(2, "失败");

    private final int code;

    private final String desc;

    TraceStatusEnum(int code, String desc) {
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
        for (TraceStatusEnum ele : values()) {
            if(null != code && ele.getCode()== code) {
                return ele.getDesc();
            }
        }
        return null;
    }

    public static Integer getCodeByDesc(String desc) {
        for (TraceStatusEnum ele : values()) {
            if(ele.getDesc().equals(desc)) {
                return ele.getCode();
            }
        }
        return null;
    }

}
