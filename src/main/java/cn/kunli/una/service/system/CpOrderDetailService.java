package cn.kunli.una.service.system;

import cn.kunli.una.pojo.chanpin.CpOrderDetail;
import cn.kunli.una.mapper.CpOrderDetailMapper;
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
public class CpOrderDetailService extends BasicService<CpOrderDetailMapper, CpOrderDetail> {
    @Autowired
    private CpOrderDetailService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
