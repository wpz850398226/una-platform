package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.chanpin.CpDelivery;
import cn.kunli.una.pojo.chanpin.CpModel;
import cn.kunli.una.pojo.chanpin.CpOrder;
import cn.kunli.una.pojo.system.SysConfiguration;
import cn.kunli.una.pojo.system.SysDictionary;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.duohui.chanpin.CpDeliveryService;
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
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private CpDeliveryService cpDeliveryService;

    /**
     * 订单页
     * @param model
     * @return
     */
    @RequestMapping("/zhifu")
    public String zhifu(Model model, String jsonStr) {
        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
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

        //收货地址
        List<CpDelivery> deliveryList = cpDeliveryService.parse(cpDeliveryService.selectList(MapUtil.getMap("creatorId", loginUser.getId())));

        //送货方式
        List<SysDictionary> deliveryTypeList = sysDictionaryService.selectList(MapUtil.getMap("parentCode", "dh_deliveryType"));
        //付款方式
        List<SysDictionary> paymentTypeList = sysDictionaryService.selectList(MapUtil.getMap("parentCode", "dh_paymentType"));


        model.addAttribute("mapListMap",mapListMap);
        model.addAttribute("shopTotalMap",shopTotalMap);
        model.addAttribute("orderTotal",orderTotal);
        model.addAttribute("deliveryList",deliveryList);
        model.addAttribute("deliveryTypeList",deliveryTypeList);
        model.addAttribute("paymentTypeList",paymentTypeList);
        SysConfiguration systemTitle = sysConfigurationService.selectOne(MapUtil.getMap("code","systemTitle"));
        model.addAttribute("systemName", systemTitle);
        model.addAttribute("activeUser", loginUser);

        return "duohui/chanpin/zhifu";
    }

    //订单结算
    public SysResult settle(Integer orderId) {
        if(orderId==null)return SysResult.fail("订单ID为空，结算失败");
        CpOrder cpOrder = service.getById(orderId);
        if(cpOrder == null) return SysResult.fail("订单查询失败，结算失败");
        //如果生成订单成功，打开支付页
        return service.settle(ListUtil.getList(cpOrder));
    }

}
