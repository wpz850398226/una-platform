package cn.kunli.una.service.duohui.toubiao;

import cn.kunli.una.pojo.bid.BidProject;
import cn.kunli.una.mapper.BidProjectMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 招投标-项目表表(BidProject)表服务类
 *
 * @author Ponzio
 * @since 2021-07-17 13:19:47
 */
@Service
public class BidProjectService extends BasicService<BidProjectMapper, BidProject> {
    @Autowired
    private BidProjectService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
