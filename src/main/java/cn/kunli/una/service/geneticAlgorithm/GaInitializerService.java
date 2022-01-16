package cn.kunli.una.service.geneticAlgorithm;

import cn.kunli.una.mapper.GaInitializerMapper;
import cn.kunli.una.pojo.geneticAlgorithm.GaInitializer;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 遗传算法-初始群体/祖先 服务实现类
 * </p>
 *
 * @author Ponzio
 * @since 2022-01-14
 */
@Service
public class GaInitializerService extends BasicService<GaInitializerMapper, GaInitializer> {

    @Autowired
    private GaInitializerService thisProxy;

    @Override
    public BasicService<GaInitializerMapper, GaInitializer> getThisProxy() {
        return thisProxy;
    }

    @Override
    public SysResult validate(GaInitializer obj) {
        return SysResult.success();
    }
}
