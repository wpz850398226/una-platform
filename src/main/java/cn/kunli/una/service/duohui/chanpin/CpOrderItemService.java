package cn.kunli.una.service.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpOrderItem;
import cn.kunli.una.mapper.CpOrderItemMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商城-订单明细表(CpOrderDetail)表服务类
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:03
 */
@Service
public class CpOrderItemService extends BasicService<CpOrderItemMapper, CpOrderItem> {
    @Autowired
    private CpOrderItemService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
