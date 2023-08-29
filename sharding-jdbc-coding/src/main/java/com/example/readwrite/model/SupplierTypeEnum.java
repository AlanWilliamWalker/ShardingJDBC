package com.example.readwrite.model;

import org.apache.commons.lang3.StringUtils;

/**
 * @author liming.wang
 * 合作类别
 */
public enum SupplierTypeEnum {

    /**
     *  常规供应商
     */
    GYS("01", "常规供应商"),
    /**
     *  政府机关
     */
    ZF("02", "政府机关"),
    /**
     *  事业单位
     */
    SY("03", "事业单位"),
    /**
     *  个体工商户
     */
    GT("04", "个体工商户"),
    /**
     *  个人
     */
    GR("05", "个人"),
    /**
     *  其他非常规
     */
    QT("06", "其他非常规");

    private final String code;

    private final String desc;

    SupplierTypeEnum(String code, String desc) {
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
        for (SupplierTypeEnum ele : values()) {
            if(StringUtils.isNotBlank(code) && ele.getCode().equals(code)) {
                return ele.getDesc();
            }
        }
        return null;
    }

    public static String getCodeByDesc(String desc) {
        for (SupplierTypeEnum ele : values()) {
            if(ele.getDesc().equals(desc)) {
                return ele.getCode();
            }
        }
        return null;
    }

}
