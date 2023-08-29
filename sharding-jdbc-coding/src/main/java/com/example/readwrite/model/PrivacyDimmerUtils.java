package com.example.readwrite.model;

import org.apache.commons.lang3.StringUtils;

/**
 * @author liming.wang
 * @描述 数据隐私显示 手机号，身份证号和银行卡号等
 * @创建时间 2022/3/20
 */
public class PrivacyDimmerUtils {
    private static final String IDCARDOVERLAY = "********";
    private static final String PHONEOVERLAY = "****";
    private static final String BANKOVERLAY = "********";
    private static final int PHONESTART = 3;
    private static final int PHONEEND = 7;
    private static final int IDCARDSTART = 4;
    private static final int IDCARDEND = 14;
    private static final int BANKCARDSTART = 4;
    private static final int EMAILSTART = 2;
    private static final int PASSPORTSTART = 2;

    /**
     * 139****0504
     *
     * @param content 手机号
     * @return 脱敏数据
     */
    public static String maskMobile(String content) {
        if (StringUtils.isEmpty(content)) {
            return "";
        }
        return StringUtils.overlay(content, PHONEOVERLAY, PHONESTART, PHONEEND);
    }

    /**
     * 身份证打码操作 前6后4，只有18位
     *
     * @param idCard 身份证号
     * @return 脱敏数据
     */
    public static String maskIdCard(String idCard) {
        if (StringUtils.isEmpty(idCard)) {
            return "";
        }
        return StringUtils.overlay(idCard, IDCARDOVERLAY, IDCARDSTART, IDCARDEND);
    }

    /**
     * 护照或其他类型
     *
     * @param passport 护照或其他类型
     * @return 脱敏数据
     */
    public static String maskPassport(String passport) {
        if (StringUtils.isEmpty(passport)) {
            return "";
        }
        int length = passport.length();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<length-4;i++){
            stringBuilder.append("*");
        }
        return StringUtils.overlay(passport, stringBuilder.toString(), PASSPORTSTART, length-PASSPORTSTART);
    }

    /**
     * 过滤邮箱账号
     * 132****99308084911
     *
     * @param email
     * @return
     */
    public static String maskEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return "";
        }
        String at = "@";
        if (!email.contains(at)) {
            return email;
        }
        /**
         * 这里主要逻辑是需要保留邮箱的注册商 比如@qq.com
         */
        int length = StringUtils.indexOf(email, at);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<length-4;i++){
            stringBuilder.append("*");
        }
        String content = StringUtils.substring(email, 0, length);
        String mask = StringUtils.overlay(content, stringBuilder.toString(), EMAILSTART, length-EMAILSTART);
        return mask + StringUtils.substring(email, length);
    }


    /**
     * 银行卡打码操作
     * 6666********7041
     *
     * @param bankCard 身份证号
     * @return 脱敏数据
     */
    public static String maskBankCard(String bankCard) {
        if (StringUtils.isEmpty(bankCard)) {
            return "";
        }
        return StringUtils.overlay(bankCard, BANKOVERLAY, BANKCARDSTART, 12);
    }

}
