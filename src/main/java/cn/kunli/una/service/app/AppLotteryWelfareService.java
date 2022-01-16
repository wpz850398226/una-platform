package cn.kunli.una.service.app;

import cn.hutool.core.map.MapUtil;
import cn.kunli.una.mapper.AppLotteryWelfareMapper;
import cn.kunli.una.pojo.app.AppLotteryWelfare;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.DateUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 福利彩票记录 服务实现类
 * </p>
 *
 * @author Ponzio
 * @since 2022-01-14
 */
@Service
public class AppLotteryWelfareService extends BasicService<AppLotteryWelfareMapper, AppLotteryWelfare> {

    @Autowired
    private AppLotteryWelfareService thisProxy;

    @Override
    public BasicService<AppLotteryWelfareMapper, AppLotteryWelfare> getThisProxy() {
        return thisProxy;
    }

    @Override
    public AppLotteryWelfare initialize(AppLotteryWelfare obj) {
        obj = super.initialize(obj);

        if(obj.getNumber()!=null){
            Integer number = obj.getNumber();

            //单体计算
            Integer single = (number)%10;
            Integer decade = (number/10)%10;
            Integer hundred = (number/100)%10;
            Integer sum = single + decade + hundred;
            Float average = Float.valueOf(sum)/3;
            Double variance = ((Math.pow(hundred,2)+Math.pow(decade,2)+Math.pow(single,2))/3)-Math.pow(average,2);    //方差
            Double standardDeviation = Math.pow(variance,0.5);
            Integer product = (hundred==0?1:hundred)*(decade==0?1:decade)*(single==0?1:single);
            if(single==0&&decade==0&&hundred==0)product = 0;
            //单体计算赋值
            obj.setHundred(hundred);
            obj.setDecade(decade);
            obj.setSingle(single);
            obj.setSum(sum);
            obj.setAverage(average);
            obj.setVariance(variance);
            obj.setStandardDeviation(standardDeviation);
            obj.setProduct(product);

            //查询上一期
            AppLotteryWelfare lastLottery = selectOne(MapUtil.of("lotteryDate", DateUtil.getNextDay(obj.getLotteryDate(), -1)));
            if(lastLottery!=null){
                Integer lastHundredDef = hundred - lastLottery.getHundred();
                Integer lastDecadeDef = decade - lastLottery.getDecade();
                Integer lastSingleDef = single - lastLottery.getSingle();
                obj.setLastHundredDef(lastHundredDef);
                obj.setLastDecadeDef(lastDecadeDef);
                obj.setLastSingleDef(lastSingleDef);
                obj.setIsHundredDefPositive(lastHundredDef>0);
                obj.setIsDecadeDefPositive(lastDecadeDef>0);
                obj.setIsSingleDefPositive(lastSingleDef>0);
            }

            //查询往期
            List<AppLotteryWelfare> sameNumberLotteryList = selectList(cn.kunli.una.utils.common.MapUtil.buildHashMap()
                    .put("number", number).put("orderByDesc","name").put("lt:lotteryDate",obj.getLotteryDate()).build());
            if(CollectionUtils.isNotEmpty(sameNumberLotteryList)){
                AppLotteryWelfare appLotteryWelfare = sameNumberLotteryList.get(0);     //上一次同号
                Integer intervalDays = DateUtil.getDiffDays(appLotteryWelfare.getLotteryDate(),obj.getLotteryDate());
                obj.setIntervalDays(intervalDays);
                obj.setHistoryCount(sameNumberLotteryList.size());
            }else{
                obj.setIntervalDays(0);
                obj.setHistoryCount(0);
            }

        }

        return obj;
    }
}
