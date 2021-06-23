package cn.kunli.una.service.system;

import cn.kunli.una.pojo.chanpin.CpOrder;
import cn.kunli.una.mapper.CpOrderMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商城-订单类(CpOrder)表服务类
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:02
 */
@Service
public class CpOrderService extends BasicService<CpOrderMapper, CpOrder> {
    @Autowired
    private CpOrderService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
