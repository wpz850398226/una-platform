package cn.kunli.una.controller.duohui.toubiao;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.bid.BidBidder;
import cn.kunli.una.pojo.bid.BidProject;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.duohui.toubiao.BidBidderService;
import cn.kunli.una.service.duohui.toubiao.BidProjectService;
import cn.kunli.una.utils.common.UnaListUtil;
import cn.kunli.una.utils.common.UnaMapUtil;
import cn.kunli.una.utils.common.UserUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @Autowired
    private BidBidderService bidBidderService;

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

        //查询项目
        BidProject bidProject = service.parse(UnaListUtil.getList(service.getById(id))).get(0);

        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
        if(loginUser!=null){
            BidBidder bidBidder = bidBidderService.selectOne(UnaMapUtil.buildHashMap().put("projectId", bidProject.getId()).put("creatorId", loginUser.getId()).build());
            if(bidBidder!=null){
                bidProject.setIsApplyed(true);
            }
        }

        model.addAttribute("record",bidProject);

        bidIndexController.getCommonItem(model);

        return "duohui/toubiao/view";
    }

    /**
     * 打开前端 详情页面
     * @param model
     * @return
     */
    @RequestMapping("/collect/{id}/{isCollect}")
    @ResponseBody
    public SysResult collect(@PathVariable Integer id, @PathVariable Boolean isCollect) {
        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
        //增加点击量
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.setEntity(new BidProject().setId(id));

        String message = "";
        if(isCollect){
            updateWrapper.setSql("collect_account_ids = CONCAT(collect_account_ids,'"+loginUser.getId()+",')");
            message = "已收藏";
        }else{
            updateWrapper.setSql("collect_account_ids = replace (collect_account_ids,'"+loginUser.getId()+",','')");
            message = "已取消收藏";
        }

        boolean updateResult = service.update(updateWrapper);

        if(updateResult){
            return SysResult.success(message);
        }else{
            return SysResult.fail();
        }
    }

    /**
     * 打开前端 详情页面
     * @param model
     * @return
     */
    @RequestMapping("/download/{id}")
    public String download(Model model, @PathVariable Integer id, String attributeName) {

        return "duohui/toubiao/download";
    }

}
