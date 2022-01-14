package cn.kunli.una.service.geneticAlgorithm;

import cn.kunli.una.mapper.GaEvaluationMapper;
import cn.kunli.una.pojo.geneticAlgorithm.GaEvaluation;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 遗传算法-适应度/评价函数 服务实现类
 * </p>
 *
 * @author Ponzio
 * @since 2022-01-14
 */
@Service
public class GaEvaluationService extends BasicService<GaEvaluationMapper, GaEvaluation> {

    @Autowired
    private GaEvaluationService thisProxy;

    @Override
    public BasicService<GaEvaluationMapper, GaEvaluation> getThisProxy() {
        return thisProxy;
    }
}
