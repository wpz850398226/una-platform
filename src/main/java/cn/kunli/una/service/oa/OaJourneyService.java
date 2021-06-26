package cn.kunli.una.service.oa;

import cn.kunli.una.pojo.oa.OaJourney;
import cn.kunli.una.mapper.OaJourneyMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 办公-出差行程(OaJourney)表服务类
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:22
 */
@Service
public class OaJourneyService extends BasicService<OaJourneyMapper, OaJourney> {
    @Autowired
    private OaJourneyService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
