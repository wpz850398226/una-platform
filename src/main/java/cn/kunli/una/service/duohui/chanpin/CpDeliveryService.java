package cn.kunli.una.service.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpDelivery;
import cn.kunli.una.mapper.CpDeliveryMapper;
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
}
