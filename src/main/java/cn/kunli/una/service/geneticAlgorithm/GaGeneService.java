package cn.kunli.una.service.geneticAlgorithm;

import cn.kunli.una.mapper.GaGeneMapper;
import cn.kunli.una.pojo.geneticAlgorithm.GaGene;
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
public class GaGeneService extends BasicService<GaGeneMapper, GaGene> {

    @Autowired
    private GaGeneService thisProxy;

    @Override
    public BasicService<GaGeneMapper, GaGene> getThisProxy() {
        return thisProxy;
    }
}
