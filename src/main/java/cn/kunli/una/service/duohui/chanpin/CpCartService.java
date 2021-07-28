package cn.kunli.una.service.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpCart;
import cn.kunli.una.mapper.CpCartMapper;
import cn.kunli.una.pojo.chanpin.CpGoods;
import cn.kunli.una.pojo.chanpin.CpModel;
import cn.kunli.una.pojo.chanpin.CpShop;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商城-购物车(CpCart)表服务类
 *
 * @author Ponzio
 * @since 2021-06-23 23:39:02
 */
@Service
public class CpCartService extends BasicService<CpCartMapper, CpCart> {
    @Autowired
    private CpCartService thisProxy;
    @Autowired
    private CpModelService cpModelService;
    @Autowired
    private CpGoodsService cpGoodsService;
    @Autowired
    private CpShopService cpShopService;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    @Override
    public List<CpCart> parse(List<CpCart> list) {
        list = super.parse(list);
        for (CpCart cpCart : list) {
            if(cpCart.getModelId()!=null){
                CpModel cpModel = cpModelService.getById(cpCart.getModelId());
                cpModel = cpModelService.parse(ListUtil.getList(cpModel)).get(0);

                if(cpModel.getGoodsId()!=null){
                    CpGoods cpGoods = cpGoodsService.getById(cpModel.getGoodsId());
                    if(cpGoods!=null){
                        Map<String, Object> map = cpCart.getMap();
                        if(map==null)map = new HashMap<>();
                        map.put("modelName", cpGoods.getName()+"("+ cpModel.getName()+")");
                        cpCart.setMap(map);

                        cpModel.setGoodsName(cpGoods.getName());
                        CpShop cpShop = cpShopService.getById(cpGoods.getShopId());
                        if(cpShop!=null){
                            cpModel.setShopName(cpShop.getName());
                        }
                    }
                }
                cpCart.setCpModel(cpModel);
            }
        }

        return list;
    }
}
