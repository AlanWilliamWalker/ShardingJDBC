package com.example.readwrite.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 主数据分发系统表
 * </p>
 *
 * @author ming
 * @since 2022-08-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("mdm_dispense_info")
public class MdmDispenseInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分发下游系统编码
     */
    private String targetCode;

    /**
     * 分发下游系统名称
     */
    private String targetName;

    /**
     * 事业部编码
     */
    private String companyCode;

    /**
     * 事业部名称
     */
    private String companyName;

    /**
     * 主数据模型
     */
    private String mdModel;

    /**
     * 主数据类型
     */
    private String mdType;

    /**
     * 分发下游系统生成appId
     */
    private String appId;

    /**
     * 分配的签名密钥secret_key,作用是分发请求时验证签名
     */
    private String secretKey;

    /**
     * 状态：0禁用1启用
     */
    private Integer status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 事业群id
     */
    private Integer fatherComId;
}
