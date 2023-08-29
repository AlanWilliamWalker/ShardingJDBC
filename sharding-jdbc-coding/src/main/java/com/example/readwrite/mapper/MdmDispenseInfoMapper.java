package com.example.readwrite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.readwrite.model.MdmDispenseInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 主数据分发系统表 Mapper 接口
 * </p>
 *
 * @author ming
 * @since 2022-08-08
 */
@Mapper
public interface MdmDispenseInfoMapper extends BaseMapper<MdmDispenseInfoEntity> {

    MdmDispenseInfoEntity findMdmDispenseInfoByTargetCodeAndMdModel(@Param("targetCode") String targetCode, @Param(
            "mdModel") String mdModel) throws Exception;
}
