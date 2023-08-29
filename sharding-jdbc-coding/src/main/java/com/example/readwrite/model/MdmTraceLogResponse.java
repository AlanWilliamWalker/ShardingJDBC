package com.example.readwrite.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 主数据分发日志列表响应结果
 * </p>
 *
 * @author ming
 * @since 2022-07-29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MdmTraceLogResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String mdDataId;

    private String mdType;

    private String mdModel;

    private String detailJson;

    private String targetName;

    private String targetCode;

    private String orgName;

    private String traceBatch;

    private String dispenseType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date traceTime;

    private String mdDataCode;

    private String param;

    private Integer traceStatus;

    private String traceStatusDesc;

}
