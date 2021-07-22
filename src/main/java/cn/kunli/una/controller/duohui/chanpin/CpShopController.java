package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.chanpin.CpShop;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.duohui.chanpin.CpShopService;
import cn.kunli.una.utils.common.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

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
        if(cpShop.getStickDeadline()==null||DateUtil.compareDate(new Date(),cpShop.getStickDeadline())){
            stickDeadline = DateUtil.getNextDay(new Date(),1);
        }else{
            stickDeadline = DateUtil.getNextDay(cpShop.getStickDeadline(),1);
        }
        return service.updateRecordById((CpShop) new CpShop().setStickDeadline(stickDeadline).setId(id));
    }
}
