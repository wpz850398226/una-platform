package cn.kunli.una.service.oa;

import cn.kunli.una.pojo.oa.OaReissue;
import cn.kunli.una.mapper.OaReissueMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 办公-补卡(OaReissue)表服务类
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:23
 */
@Service
public class OaReissueService extends BasicService<OaReissueMapper, OaReissue> {
    @Autowired
    private OaReissueService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
