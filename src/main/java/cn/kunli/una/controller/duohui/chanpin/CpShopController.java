package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.chanpin.CpGoods;
import cn.kunli.una.pojo.chanpin.CpShop;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.duohui.chanpin.CpGoodsService;
import cn.kunli.una.service.duohui.chanpin.CpShopService;
import cn.kunli.una.utils.common.ListUtil;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.TimeUtil;
import cn.kunli.una.utils.common.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 商城-店铺类(CpShop)表控制层
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:04
 */
@Controller
@RequestMapping("/cp/shop")
public class CpShopController extends BaseController<CpShopService, CpShop> {

    //刷新
    @PutMapping("/refresh/{id}")
    @ResponseBody
    public SysResult refresh(@PathVariable Integer id) {
        return service.updateRecordById((CpShop) new CpShop().setRefreshTime(new Date()).setId(id));
    }

    //置顶
    @PutMapping("/stick/{id}")
    @ResponseBody
    public SysResult stick(@PathVariable Integer id) {
        CpShop cpShop = service.getById(id);
        Date stickDeadline;
        if(cpShop.getStickDeadline()==null||TimeUtil.compareDate(new Date(),cpShop.getStickDeadline())){
            stickDeadline = TimeUtil.getNextDay(new Date(),1);
        }else{
            stickDeadline = TimeUtil.getNextDay(cpShop.getStickDeadline(),1);
        }
        return service.updateRecordById((CpShop) new CpShop().setStickDeadline(stickDeadline).setId(id));
    }
}
