package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.chanpin.CpGoods;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.duohui.chanpin.CpGoodsService;
import cn.kunli.una.utils.common.ListUtil;
import cn.kunli.una.utils.common.TimeUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 账号(CpGoods)表控制层
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:00
 */
@Controller
@RequestMapping("/cp/goods")
public class CpGoodsController extends BaseController<CpGoodsService, CpGoods> {

    @Autowired
    private CpIndexController cpIndexController;

    /**
     * 打开前端 商品详情
     * @param model
     * @return
     */
    @RequestMapping("/fDetail/{id}")
    public String fDetail(Model model, @PathVariable Integer id) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.setEntity(new CpGoods().setId(id));
        updateWrapper.setSql("view_amount = view_amount + 1");
        service.update(updateWrapper);
        CpGoods record = service.parse(ListUtil.getList(service.getById(id))).get(0);
        model.addAttribute("record",record);
        cpIndexController.getCommonItem(model);

        return "duohui/chanpin/procon";
    }

    //刷新
    @PutMapping("/refresh/{id}")
    @ResponseBody
    public SysResult refresh(@PathVariable Integer id) {
        return service.updateRecordById((CpGoods) new CpGoods().setRefreshTime(new Date()).setId(id));
    }

    //置顶
    @PutMapping("/stick/{id}")
    @ResponseBody
    public SysResult stick(@PathVariable Integer id) {
        CpGoods cpGoods = service.getById(id);
        Date stickDeadline;
        if(cpGoods.getStickDeadline()==null||TimeUtil.compareDate(new Date(),cpGoods.getStickDeadline())){
            stickDeadline = TimeUtil.getNextDay(new Date(),1);
        }else{
            stickDeadline = TimeUtil.getNextDay(cpGoods.getStickDeadline(),1);
        }
        return service.updateRecordById((CpGoods) new CpGoods().setStickDeadline(stickDeadline).setId(id));
    }


}
