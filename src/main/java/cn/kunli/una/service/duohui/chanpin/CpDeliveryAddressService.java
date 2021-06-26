package cn.kunli.una.service.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpDeliveryAddress;
import cn.kunli.una.mapper.CpDeliveryAddressMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商城-收件人(CpDeliveryAddress)表服务类
 *
 * @author Ponzio
 * @since 2021-06-23 23:39:59
 */
@Service
public class CpDeliveryAddressService extends BasicService<CpDeliveryAddressMapper, CpDeliveryAddress> {
    @Autowired
    private CpDeliveryAddressService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
