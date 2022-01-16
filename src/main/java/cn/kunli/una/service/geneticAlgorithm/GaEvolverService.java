package cn.kunli.una.service.geneticAlgorithm;

import cn.hutool.core.map.MapUtil;
import cn.kunli.una.mapper.GaEvolverMapper;
import cn.kunli.una.pojo.geneticAlgorithm.GaEvolver;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public SysResult calculate(Integer id){
        GaEvolver target = getById(id);

        Integer count = target.getInitializerCount();
        //如果没有指定初代数量，则随机20-200
        if(count==null) {
            Double v = 180 * Math.random() + 20;
            count = v.intValue();
        }


        return SysResult.success();
    }

    @Override
    public GaEvolver initialize(GaEvolver obj) {
        obj = super.initialize(obj);
        if(obj.getId()==null){
            String yyyyMMdd = DateUtil.getNowDayTime("yyyyMMdd");
            List<GaEvolver> gaEvolvers = selectList(MapUtil.of(":name", yyyyMMdd));
            obj.setName(yyyyMMdd+gaEvolvers.size());
        }
        return obj;
    }
}
