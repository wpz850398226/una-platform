package cn.kunli.una.controller.app;


import cn.hutool.core.map.MapUtil;
import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.app.AppLotteryWelfare;
import cn.kunli.una.service.app.AppLotteryWelfareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>
 * 福利彩票记录 前端控制器
 * </p>
 *
 * @author Ponzio
 * @since 2022-01-14
 */
@Controller
@RequestMapping("/app/lotteryWelfare")
public class AppLotteryWelfareController extends BaseController<AppLotteryWelfareService, AppLotteryWelfare> {

    @Autowired
    private AppLotteryWelfareService service;

    //
    public void operateLottery(){
        //查询所有福彩记录，正序查
        List<AppLotteryWelfare> appLotteryWelfares = service.selectList(MapUtil.of("orderByAsc","name"));
        //遍历处理
        appLotteryWelfares.stream().forEach( record ->{

            Integer id = record.getId();


        });

    }

}
