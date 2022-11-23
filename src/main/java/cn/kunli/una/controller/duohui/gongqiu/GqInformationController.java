package cn.kunli.una.controller.duohui.gongqiu;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.gongqiu.GqInformation;
import cn.kunli.una.service.duohui.gongqiu.GqInformationService;
import cn.kunli.una.utils.common.UnaListUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 供求信息(GqInformation)表控制层
 *
 * @author Ponzio
 * @since 2021-07-21 21:17:31
 */
@Controller
@Api(tags = "供求-信息")
@RequestMapping("/gq/information")
public class GqInformationController extends BaseController<GqInformationService, GqInformation> {

    @Autowired
    private GqIndexController gqIndexController;

    /**
     * 打开前端 商品详情
     * @param model
     * @return
     */
    @RequestMapping("/fDetail/{id}")
    public String fDetail(Model model, @PathVariable Integer id, String attributeName) {
        //增加点击量
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.setEntity(new GqInformation().setId(id));
        updateWrapper.setSql("view_amount = view_amount + 1");
        service.update(updateWrapper);

        //查询商品
        GqInformation record = service.parse(UnaListUtil.getList(service.getById(id))).get(0);

        model.addAttribute("record",record);
        gqIndexController.getCommonItem(model);

        return "duohui/gongqiu/gongqiucon";
    }

}
