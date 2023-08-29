package com.example.readwrite.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 主数据追踪日志记录表
 * </p>
 *
 * @author ming
 * @since 2022-10-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("mdm_trace_log")
public class MdmTraceLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableField("trace_id")
    private Long traceId;

    @TableField("target_code")
    private String targetCode;

    @TableField("md_data_id")
    private String mdDataId;


    @TableField("md_data_code")
    private String mdDataCode;

    @TableField("md_model")
    private String mdModel;

    @TableField("param")
    private String param;

    @TableField("trace_status")
    private Integer traceStatus;

    @TableField("detail_json")
    private String detailJson;

    @TableField("trace_batch")
    private String traceBatch;

    @TableField("trace_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date traceTime;

    @TableField("dispense_type")
    private String dispenseType;

    /**
     * 创建人
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新人
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


}
