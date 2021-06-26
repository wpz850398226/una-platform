package cn.kunli.una.service.oa;

import cn.kunli.una.pojo.oa.OaVacate;
import cn.kunli.una.mapper.OaVacateMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 办公-请假(OaVacate)表服务类
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:25
 */
@Service
public class OaVacateService extends BasicService<OaVacateMapper, OaVacate> {
    @Autowired
    private OaVacateService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
