package cn.kunli.una.service.oa;

import cn.kunli.una.pojo.oa.OaTravel;
import cn.kunli.una.mapper.OaTravelMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 办公-出差(OaTravel)表服务类
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:25
 */
@Service
public class OaTravelService extends BasicService<OaTravelMapper, OaTravel> {
    @Autowired
    private OaTravelService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
