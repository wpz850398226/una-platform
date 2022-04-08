package cn.kunli.una.service.sys;

import cn.kunli.una.pojo.sys.SysKnowledge;
import cn.kunli.una.mapper.SysKnowledgeMapper;
import org.springframework.stereotype.Service;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * <p>
 * 知识 服务类
 * </p>
 *
 * @author wangpz
 * @since 2022-04-08
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
