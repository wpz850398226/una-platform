package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysAnnouncementMapper;
import cn.kunli.una.pojo.system.SysAnnouncement;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (SysAnnouncement)表服务实现类
 *
 * @author Ponzio
 * @since 2020-05-06 14:32:00
 */
@Service
public class SysAnnouncementService extends BasicService<SysAnnouncementMapper, SysAnnouncement> {

    @Autowired
    private SysAnnouncementService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    /*public SysResult read(SysAnnouncement obj) {
        //如果是全部转为已读，则删除该用户之前的已读记录
        if (obj.getIsAll() != null && obj.getIsAll().equals(1)) this.mapper.deleteRead(obj.getCreatorId());
        //插入新的已读记录
        int num = this.mapper.read(obj);
        if (num >= 0) return SysResult.success();
        return SysResult.fail();
    }*/
}
