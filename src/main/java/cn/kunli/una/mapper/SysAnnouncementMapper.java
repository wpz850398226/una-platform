package cn.kunli.una.mapper;

import cn.kunli.una.handler.BasicMapper;
import cn.kunli.una.pojo.system.SysAnnouncement;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * (SysAnnouncement)表数据库访问层
 *
 * @author Ponzio
 * @since 2020-05-06 14:14:15
 */

public interface SysAnnouncementMapper extends BasicMapper<SysAnnouncement> {

    /**
     * 删除已读记录
     *
     * @param userId 用户id
     * @return 对象列表
     */
    @Delete("delete from sys_user_announcement where user_id = #{userId}")
    int deleteRead(Integer userId);
    /**
     * 转为已读
     *
     * @param record 实例对象
     * @return 对象列表
     */
    int read(@Param("record")SysAnnouncement record);

}
