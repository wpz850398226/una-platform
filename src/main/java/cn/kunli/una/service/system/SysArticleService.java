package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysArticleMapper;
import cn.kunli.una.pojo.system.SysArticle;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (SysArticle)表服务实现类
 *
 * @author Ponzio
 * @since 2020-05-06 17:13:09
 */
@Service
public class SysArticleService extends BasicService<SysArticleMapper, SysArticle> {
    @Autowired
    private SysArticleService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
