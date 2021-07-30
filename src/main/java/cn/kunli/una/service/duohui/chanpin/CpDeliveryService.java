package cn.kunli.una.service.duohui.chanpin;

import cn.kunli.una.mapper.CpDeliveryMapper;
import cn.kunli.una.pojo.chanpin.CpDelivery;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商城-收件人(CpDelivery)表服务类
 *
 * @author Ponzio
 * @since 2021-06-23 23:39:59
 */
@Service
public class CpDeliveryService extends BasicService<CpDeliveryMapper, CpDelivery> {
    @Autowired
    private CpDeliveryService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    /*@Override
    public CpDelivery initialize(CpDelivery obj) {
        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
        obj = super.initialize(obj);
        List<CpDelivery> deliveryList = thisProxy.selectList(MapUtil.getMap("creatorId", loginUser.getId()));
        if(CollectionUtils.isEmpty(deliveryList)){
            //如果这是第一条收货地址，则设置为默认
            obj.setIsDefault()
        }
        if(obj.getIsDefault()){
            //如果保存为默认地址，修改其他地址为非默认

        }

        return obj;
    }*/
}
