package com.example.readwrite.model;

import org.apache.commons.lang3.StringUtils;

/**
 * @author liming.wang
 * 分发类型枚举  主数据分发类型：TB同步；YBQL异步全量；YBZL异步增量
 */
public enum TraceTypeEnum {

    /**
     *  同步
     */
    TB("TB", "同步"),
    /**
     *  异步全量
     */
    YBQL("YBQL", "异步全量"),
    /**
     *  YBZL
     */
    YBZL("YBZL", "异步增量");

    private final String code;

    private final String desc;

    TraceTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescByCode(String code) {
        for (TraceTypeEnum ele : values()) {
            if(StringUtils.isNotBlank(code) && code.equals(ele.getCode())) {
                return ele.getDesc();
            }
        }
        return null;
    }

    public static String getCodeByDesc(String desc) {
        for (TraceTypeEnum ele : values()) {
            if(ele.getDesc().equals(desc)) {
                return ele.getCode();
            }
        }
        return null;
    }

}
