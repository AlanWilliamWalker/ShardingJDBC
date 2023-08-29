package com.example.readwrite.model;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liming.wang
 * @描述 事业部枚举
 * @创建时间 2022/3/12
 */
@Slf4j
public enum MdmEnum {

    /**
     * 客户基本主数据
     */
    MDM_CUSTOMER_INFO("mdm_customer_info", "客户基本主数据", "客户主数据", "customer", "customer_base"),
    /**
     * 客户邮箱主数据
     */
    MDM_CUSTOMER_EMAIL_INFO("mdm_customer_email_info", "客户邮箱主数据", "客户主数据", "customer", "customer_email"),
    /**
     * 客户手机主数据
     */
    MDM_CUSTOMER_PHONE_INFO("mdm_customer_phone_info", "客户手机主数据", "客户主数据", "customer", "customer_phone"),
    /**
     * 人力部门主数据
     */
    MDM_HR_DEPARTMENT_INFO("mdm_hr_department_info", "人力部门主数据", "人力主数据", "hr", "hr_dept"),


    MDM_HR_ORGANIZATION_INFO("mdm_hr_organization_info", "人力业务单元主数据", "人力主数据", "hr", "hr_org"),
    /**
     * 人力岗位主数据
     */
    MDM_HR_POST_INFO("mdm_hr_post_info", "人力岗位主数据", "人力主数据", "hr", "hr_post"),
    /**
     * 人力人员主数据
     */
    MDM_HR_STAFF_INFO("mdm_hr_staff_info", "人力人员主数据", "人力主数据", "hr", "hr_staff"),

    /**
     * 法人主体主数据
     */
    MDM_CORPORATION_INFO("mdm_corporation_info", "法人主体主数据", "法人主体主数据", "corporation", "corporation"),

    /**
     * 信息系统主数据
     */
    MDM_INFORMATION_INFO("mdm_information_info", "信息系统主数据", "信息系统主数据", "info", "info"),
    /**
     * 供应商正常流程主数据
     */
    MDM_SUPPLIER_BASIC_FLOW_INFO("mdm_supplier_basic_flow_info", "供应商正常流程主数据", "供应商主数据", "supplier", "supplier");

    private final String tableName;

    private final String mdmModel;

    private final String mdmModelCode;

    private final String mdmType;

    private final String permisCode;


    MdmEnum(String tableName, String mdmModel, String mdmType, String permisCode, String mdmModelCode) {
        this.tableName = tableName;
        this.mdmModel = mdmModel;
        this.mdmType = mdmType;
        this.permisCode = permisCode;
        this.mdmModelCode = mdmModelCode;
    }


    public final String getTableName() {
        return tableName;
    }

    public final String getMdmModel() {
        return mdmModel;
    }

    public final String getMdmType() {
        return mdmType;
    }

    public final String getMdmModelCode() {
        return mdmModelCode;
    }

    public final String getPermisCode() {
        return permisCode;
    }

    /**
     * 根据表名获取枚举
     *
     * @param tableName 表名
     * @return 枚举
     */
    public static MdmEnum getByTableName(String tableName) {
        for (MdmEnum mdmEnum : values()) {
            if (mdmEnum.getTableName().equals(tableName)) {
                //获取指定的枚举
                return mdmEnum;
            }
        }
        return null;
    }


    /**
     * 根据主数据模型编码获取枚举
     *
     * @param mdmModelCode 主数据模型
     * @return 枚举
     */
    public static MdmEnum getByMdmModelCode(String mdmModelCode) {
        for (MdmEnum mdmEnum : values()) {
            if (mdmEnum.getMdmModelCode().equals(mdmModelCode)) {
                //获取指定的枚举
                return mdmEnum;
            }
        }
        return null;
    }

    /**
     * 根据主数据类型获取枚举
     *
     * @param mdmType 主数据类型
     * @return 枚举
     */
    public static List<MdmEnum> getByMdmType(String mdmType) {
        ArrayList<MdmEnum> enums = new ArrayList<>();
        for (MdmEnum mdmEnum : values()) {
            if (mdmEnum.mdmType.equals(mdmType)) {
                //获取指定的枚举
                enums.add(mdmEnum);
            }
        }
        if (CollectionUtils.isNotEmpty(enums)) {
            return enums;
        }
        log.error("主数据类型不存在:{}", mdmType);
        return null;
    }

    /**
     * 根据主数据类型编码获取主数据类型中文
     *
     * @param permisCode 主数据类型编码
     * @return 主数据类型中文
     */
    public static String getMdmTypeByMdmTypeCode(String permisCode) {
        for (MdmEnum mdmEnum : values()) {
            if (mdmEnum.permisCode.equals(permisCode)) {
                //获取指定的主数据类型中文
                return mdmEnum.getMdmType();
            }
        }
        log.error("主数据类型不存在:{}", permisCode);
        return null;
    }

    /**
     * 根据主数据类型获取主数据类型编码
     *
     * @param mdmType 主数据类型
     * @return 枚举
     */
    public static MdmEnum getCodeByMdmType(String mdmType) {
        for (MdmEnum mdmEnum : values()) {
            if (mdmEnum.getMdmType().equals(mdmType)) {
                //获取指定的枚举
                return mdmEnum;
            }
        }
        return null;
    }

    /**
     * 根据主数据模型获取枚举
     *
     * @param mdmModel 主数据模型
     * @return 枚举
     */
    public static MdmEnum getByMdmModel(String mdmModel) {
        for (MdmEnum mdmEnum : values()) {
            if (mdmEnum.getMdmModel().equals(mdmModel)) {
                //获取指定的枚举
                return mdmEnum;
            }
        }
        return null;
    }


}
