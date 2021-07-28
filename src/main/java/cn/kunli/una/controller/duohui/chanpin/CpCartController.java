package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpCart;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.service.duohui.chanpin.CpCartService;
import cn.kunli.una.controller.BaseController;
import cn.kunli.una.utils.common.ListUtil;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商城-购物车(CpCart)表控制层
 *
 * @author Ponzio
 * @since 2021-06-23 23:39:02
 */
@Controller
@RequestMapping("/cp/cart")
public class CpCartController extends BaseController<CpCartService, CpCart> {

    /**
     * 打开前端 商品详情
     * @param model
     * @return
     */
    @RequestMapping("/fDetail")
    public String fDetail(Model model) {
        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
        List<CpCart> cpCartList = service.parse(service.selectList(MapUtil.getMap("creatorId",loginUser.getId())));
        List<List<CpCart>> recordList = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();

        for (CpCart cpCart : cpCartList) {
            String shopName = cpCart.getCpModel().getShopName();
            if(map.containsKey(shopName)){
                Integer index = map.get(shopName);
                recordList.get(index).add(cpCart);
            }else{
                map.put(shopName,recordList.size());
                recordList.add(ListUtil.getList(cpCart));
            }
        }

        model.addAttribute("recordList",recordList);

        return "duohui/chanpin/gwc";
    }

}
