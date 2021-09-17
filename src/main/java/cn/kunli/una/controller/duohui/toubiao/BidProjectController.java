package cn.kunli.una.controller.duohui.toubiao;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.bid.BidProject;
import cn.kunli.una.service.duohui.toubiao.BidProjectService;
import cn.kunli.una.utils.common.ListUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 招投标-项目表表(BidProject)表控制层
 *
 * @author Ponzio
 * @since 2021-07-17 13:19:47
 */
@Controller
@RequestMapping("/bid/project")
public class BidProjectController extends BaseController<BidProjectService, BidProject> {

    @Autowired
    private BidIndexController bidIndexController;

    /**
     * 打开前端 详情页面
     * @param model
     * @return
     */
    @RequestMapping("/fDetail/{id}")
    public String fDetail(Model model, @PathVariable Integer id, String attributeName) {
        //增加点击量
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.setEntity(new BidProject().setId(id));
        updateWrapper.setSql("browse_count = browse_count + 1");
        service.update(updateWrapper);

        //查询商品
        BidProject bidProject = service.parse(ListUtil.getList(service.getById(id))).get(0);
        model.addAttribute("record",bidProject);

        bidIndexController.getCommonItem(model);

        return "duohui/toubiao/view";
    }

}
