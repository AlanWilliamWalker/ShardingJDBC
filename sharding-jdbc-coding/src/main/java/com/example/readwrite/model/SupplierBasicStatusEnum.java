package com.example.readwrite.model;

import org.apache.commons.lang3.StringUtils;

/**
 * @author liming.wang
 * 业务标识
 */
public enum SupplierBasicStatusEnum {

    /**
     *  否
     */
    NO("0", "否"),
    /**
     *  是
     */
    YES("1", "是");

    private final String code;

    private final String desc;

    SupplierBasicStatusEnum(String code, String desc) {
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
        for (SupplierBasicStatusEnum ele : values()) {
            if(StringUtils.isNotBlank(code) && ele.getCode().equals(code)) {
                return ele.getDesc();
            }
        }
        return null;
    }

    public static String getCodeByDesc(String desc) {
        for (SupplierBasicStatusEnum ele : values()) {
            if(ele.getDesc().equals(desc)) {
                return ele.getCode();
            }
        }
        return null;
    }

}
