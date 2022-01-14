package cn.kunli.una.service.geneticAlgorithm;

import cn.kunli.una.mapper.GaRepresentationMapper;
import cn.kunli.una.pojo.geneticAlgorithm.GaRepresentation;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 遗传算法-表示/染色体 服务实现类
 * </p>
 *
 * @author Ponzio
 * @since 2022-01-14
 */
@Service
public class GaRepresentationService extends BasicService<GaRepresentationMapper, GaRepresentation> {

    @Autowired
    private GaRepresentationService thisProxy;

    @Override
    public BasicService<GaRepresentationMapper, GaRepresentation> getThisProxy() {
        return thisProxy;
    }
}
