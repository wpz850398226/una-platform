package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpGoods;
import cn.kunli.una.pojo.system.SysDictionary;
import cn.kunli.una.service.duohui.chanpin.CpGoodsService;
import cn.kunli.una.controller.BaseController;
import cn.kunli.una.utils.common.MapUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 账号(CpGoods)表控制层
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:00
 */
@Controller
@RequestMapping("/cp/goods")
public class CpGoodsController extends BaseController<CpGoodsService, CpGoods> {

    /**
     * 打开主体框架
     *
     * @param model
     * @return
     */
    @RequestMapping("/detail/{id}")
    public String detail(Model model, @PathVariable Integer id) {
        CpGoods record = service.getById(id);
        model.addAttribute("record",record);

        return "duohui/chanpin/procon";
    }

}
