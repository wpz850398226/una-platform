package cn.kunli.una.service.geneticAlgorithm;

import cn.kunli.una.mapper.GaEvolverMapper;
import cn.kunli.una.pojo.geneticAlgorithm.GaEvolver;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 遗传算法-进化者/最优解 服务实现类
 * </p>
 *
 * @author Ponzio
 * @since 2022-01-14
 */
@Service
public class GaEvolverService extends BasicService<GaEvolverMapper, GaEvolver> {

    @Autowired
    private GaEvolverService thisProxy;

    @Override
    public BasicService<GaEvolverMapper, GaEvolver> getThisProxy() {
        return thisProxy;
    }
}
