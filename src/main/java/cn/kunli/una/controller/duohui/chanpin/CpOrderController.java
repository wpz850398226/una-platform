package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.chanpin.CpModel;
import cn.kunli.una.pojo.chanpin.CpOrder;
import cn.kunli.una.pojo.chanpin.CpOrderItem;
import cn.kunli.una.pojo.system.SysConfiguration;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.service.duohui.chanpin.CpModelService;
import cn.kunli.una.service.duohui.chanpin.CpOrderService;
import cn.kunli.una.utils.common.ListUtil;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.UserUtil;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * 商城-订单类(CpOrder)表控制层
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:02
 */
@Controller
@RequestMapping("/cp/order")
public class CpOrderController extends BaseController<CpOrderService, CpOrder> {

    @Autowired
    private CpModelService cpModelService;

    /**
     * 打开前端 商品详情
     * @param model
     * @return
     */
    @RequestMapping("/zhifu")
    public String fDetail(Model model, String jsonStr) {
        List<Map> paramMapList = JSONArray.parseArray(jsonStr, Map.class);
        Map<String, List<Map>> mapListMap = new LinkedHashMap<>();
        //店铺总价
        Map<String,Double> shopTotalMap = new HashMap<>();
        //订单总价
        Double orderTotal = 0D;
        if(CollectionUtils.isNotEmpty(paramMapList)){
            for (Map paramMap : paramMapList) {
                if(paramMap.containsKey("modelId")){
                    String modelId = paramMap.get("modelId").toString();
                    CpModel cpModel = cpModelService.getById(modelId);
                    cpModel = cpModelService.parse(ListUtil.getList(cpModel)).get(0);

                    String shopName = cpModel.getShopName();
                    Integer num = Integer.valueOf(paramMap.get("num").toString());
                    Double modelTotal = num*cpModel.getSellingPrice();
                    orderTotal = orderTotal + modelTotal;

                    Map<String, Object> build = MapUtil.buildHashMap().put("volume", num)
                            .put("cpModel", cpModel).put("totalAmount",modelTotal).build();
                    if(mapListMap.containsKey(shopName)){
                        mapListMap.get(shopName).add(build);
                        shopTotalMap.put(shopName,shopTotalMap.get(shopName)+modelTotal);
                    }else{
                        mapListMap.put(shopName,ListUtil.getList(build));
                        shopTotalMap.put(shopName,modelTotal);
                    }
                }
            }
        }


        model.addAttribute("mapListMap",mapListMap);
        model.addAttribute("shopTotalMap",shopTotalMap);
        model.addAttribute("orderTotal",orderTotal);
        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
        SysConfiguration systemTitle = sysConfigurationService.selectOne(MapUtil.getMap("code","systemTitle"));
        model.addAttribute("systemName", systemTitle);
        model.addAttribute("activeUser", loginUser);

        return "duohui/chanpin/zhifu";
    }

    /**
     * 打开前端 商品详情
     * @param model
     * @return
     */
    @RequestMapping("/zhifu/{modelId}/{num}")
    public String fDetail(Model model, @PathVariable Integer modelId, @PathVariable Integer num) {
        Map<String, List<CpOrderItem>> mapListMap = new LinkedHashMap<>();
//        for (CpOrderItem item : items) {
//            if(item.getModelId()!=null){
//                CpModel cpModel = cpModelService.getById(item.getModelId());
//                cpModel = cpModelService.parse(ListUtil.getList(cpModel)).get(0);
//                String shopName = cpModel.getShopName();
//                item.setCpModel(cpModel);
//                if(mapListMap.containsKey(shopName)){
//                    mapListMap.get(shopName).add(item);
//                }else{
//                    mapListMap.put(shopName,ListUtil.getList(item));
//                }
//            }
//        }

        CpModel cpModel = cpModelService.getById(modelId);
        cpModel = cpModelService.parse(ListUtil.getList(cpModel)).get(0);
        String shopName = cpModel.getShopName();
        CpOrderItem cpOrderItem = new CpOrderItem().setModelId(modelId).setCpModel(cpModel);
        if(mapListMap.containsKey(shopName)){
            mapListMap.get(shopName).add(cpOrderItem);
        }else{
            mapListMap.put(shopName,ListUtil.getList(cpOrderItem));
        }

        model.addAttribute("mapListMap",mapListMap);
        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
        SysConfiguration systemTitle = sysConfigurationService.selectOne(MapUtil.getMap("code","systemTitle"));
        model.addAttribute("systemName", systemTitle);
        model.addAttribute("activeUser", loginUser);

        return "duohui/chanpin/zhifu";
    }
}
