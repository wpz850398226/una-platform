package cn.kunli.una.service.system;

import cn.kunli.una.pojo.gongqiu.GqInformation;
import cn.kunli.una.mapper.GqInformationMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 供求信息(GqInformation)表服务类
 *
 * @author Ponzio
 * @since 2021-07-21 21:17:31
 */
@Service
public class GqInformationService extends BasicService<GqInformationMapper, GqInformation> {
    @Autowired
    private GqInformationService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
