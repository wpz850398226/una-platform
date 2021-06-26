package cn.kunli.una.service.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpGoodsParameter;
import cn.kunli.una.mapper.CpGoodsParameterMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商城-商品参数表(CpGoodsParameter)表服务类
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:01
 */
@Service
public class CpGoodsParameterService extends BasicService<CpGoodsParameterMapper, CpGoodsParameter> {
    @Autowired
    private CpGoodsParameterService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
