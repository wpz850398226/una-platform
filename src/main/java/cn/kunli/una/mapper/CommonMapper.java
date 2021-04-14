package cn.kunli.una.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * 通用方法
 *
 * @author Ponzio
 * @since 2021年3月28日11:49:49
 */
public interface CommonMapper {
    Integer increaseOrder(@Param("tableName") String tableName, @Param("fieldCode") String fieldCode, @Param("id") Object id);

    Integer increaseOrderBehindById(@Param("tableName") String tableName, @Param("fieldCode") String fieldCode, @Param("id") Object id);
}
