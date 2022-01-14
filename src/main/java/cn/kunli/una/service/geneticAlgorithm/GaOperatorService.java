package cn.kunli.una.service.geneticAlgorithm;

import cn.kunli.una.mapper.GaOperatorMapper;
import cn.kunli.una.pojo.geneticAlgorithm.GaOperator;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 遗传算法-算子/选交变 服务实现类
 * </p>
 *
 * @author Ponzio
 * @since 2022-01-14
 */
@Service
public class GaOperatorService extends BasicService<GaOperatorMapper, GaOperator> {

    @Autowired
    private GaOperatorService thisProxy;

    @Override
    public BasicService<GaOperatorMapper, GaOperator> getThisProxy() {
        return thisProxy;
    }
}
