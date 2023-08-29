package com.example.readwrite.model;

/**
 * @author liming.wang
 * 合作类别
 */
public enum CooperationCategoryEnum {

    /**
     *  对外付款类-采购流程付款
     */
    CGLCFK(0, "对外付款类-采购流程付款"),
    /**
     *  对外付款类-其他流程付款
     */
    QTLCFK(1, "对外付款类-其他流程付款"),
    /**
     *  无资金往来类
     */
    NO(2, "无资金往来类"),
    /**
     *  对外收款类
     */
    SK(3, "对外收款类"),
    /**
     *  其他类
     */
    QT(4, "其他类");

    private final int code;

    private final String desc;

    CooperationCategoryEnum(int code, String desc) {
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
        for (CooperationCategoryEnum ele : values()) {
            if(null != code && ele.getCode()== code) {
                return ele.getDesc();
            }
        }
        return null;
    }

    public static Integer getCodeByDesc(String desc) {
        for (CooperationCategoryEnum ele : values()) {
            if(ele.getDesc().equals(desc)) {
                return ele.getCode();
            }
        }
        return null;
    }

}
