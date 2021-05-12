package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysKnowledgeMapper;
import cn.kunli.una.pojo.system.SysKnowledge;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (SysKnowledge)表服务实现类
 *
 * @author Ponzio
 * @since 2020-10-16 17:48:03
 */
@Service
public class SysKnowledgeService extends BasicService<SysKnowledgeMapper, SysKnowledge> {

    @Autowired
    private SysKnowledgeService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

}
