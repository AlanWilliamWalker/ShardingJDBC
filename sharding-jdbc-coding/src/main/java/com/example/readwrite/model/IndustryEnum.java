package com.example.readwrite.model;

/**
 * @author liming.wang
 */
public enum IndustryEnum {
    /**
     *  商业服务业
     */
    SYFWY(1, "商业服务业"),
    /**
     *  工程师
     */
    GCS(2, "工程师"),
    /**
     *  计算机业
     */
    IT(3, "计算机业"),
    /**
     *  教育业
     */
    JYY(4, "教育业"),
    /**
     *  医务人员
     */
    YWRY(5, "医务人员"),
    /**
     *  金融业
     */
    JRY(6, "金融业"),
    /**
     *  财务人员
     */
    CWRY(7, "财务人员"),
    /**
     *  企业老板
     */
    BOSS(8, "企业老板"),
    /**
     *  普通职员
     */
    PTZY(9, "普通职员"),
    /**
     *  退休
     */
    TX(10, "退休"),
    /**
     *  销售/市场/广告
     */
    XS(11, "销售/市场/广告"),
    /**
     *  政府部门
     */
    GOV(12, "政府部门"),
    /**
     *  制造业
     */
    ZZY(13, "制造业"),
    /**
     *  军人
     */
    JR(14, "军人"),
    /**
     *  其他从业人员
     */
    OTHER(15, "其他从业人员"),
    /**
     *  公检法
     */
    GJF(16, "公检法"),
    /**
     *  传媒
     */
    CM(17, "传媒"),
    /**
     *  记者
     */
    JZ(18, "记者"),

    /**
     * 律师
     */
    LS(19, "律师");

    private final int code;

    private final String desc;

    IndustryEnum(int code, String desc) {
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
        for (IndustryEnum ele : values()) {
            if(null != code && ele.getCode()== code) {
                return ele.getDesc();
            }
        }
        return null;
    }

    public static Integer getCodeByDesc(String desc) {
        for (IndustryEnum ele : values()) {
            if(ele.getDesc().equals(desc)) {
                return ele.getCode();
            }
        }
        return null;
    }

}
