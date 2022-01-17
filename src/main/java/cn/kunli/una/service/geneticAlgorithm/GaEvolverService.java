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

        //初代个体数量
        Integer count = target.getInitializerCount();
        //入参基因
        Integer paramGeneId = target.getParamGeneId();

        //如果没有指定初代数量，则随机20-200
        if(count==null) {
            Double v = 180 * Math.random() + 20;
            count = v.intValue();
        }

        //如果没有指定初代数量，随机取
        if (paramGeneId == null) {

        }


        return SysResult.success();
    }

    @Override
    public SysResult validate(GaEvolver obj) {
        SysResult validate = super.validate(obj);
        if(!validate.getIsSuccess())return validate;

        if(obj.getIterationThreshold()==null && obj.getScoreThreshold()==null){
            return SysResult.fail("迭代阈值与得分阈值不能同时为空，保存失败");
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
