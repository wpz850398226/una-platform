package cn.kunli.una.service.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpCart;
import cn.kunli.una.mapper.CpCartMapper;
import cn.kunli.una.pojo.chanpin.CpGoods;
import cn.kunli.una.pojo.chanpin.CpGoodsAttribute;
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
    private CpGoodsAttributeService cpGoodsAttributeService;
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
            if(cpCart.getGoodsAttributeId()!=null){
                CpGoodsAttribute cpGoodsAttribute = cpGoodsAttributeService.getById(cpCart.getGoodsAttributeId());
                cpGoodsAttribute = cpGoodsAttributeService.parse(ListUtil.getList(cpGoodsAttribute)).get(0);

                if(cpGoodsAttribute.getGoodsId()!=null){
                    CpGoods cpGoods = cpGoodsService.getById(cpGoodsAttribute.getGoodsId());
                    if(cpGoods!=null){
                        Map<String, Object> map = cpCart.getMap();
                        if(map==null)map = new HashMap<>();
                        map.put("goodsAttributeName", cpGoods.getName()+"("+cpGoodsAttribute.getName()+")");
                        cpCart.setMap(map);

                        cpGoodsAttribute.setGoodsName(cpGoods.getName());
                        CpShop cpShop = cpShopService.getById(cpGoods.getShopId());
                        if(cpShop!=null){
                            cpGoodsAttribute.setShopName(cpShop.getName());
                        }
                    }
                }
                cpCart.setCpGoodsAttribute(cpGoodsAttribute);
            }
        }

        return list;
    }
}
