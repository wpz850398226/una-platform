package cn.kunli.una.service.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpShop;
import cn.kunli.una.mapper.CpShopMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商城-店铺类(CpShop)表服务类
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:04
 */
@Service
public class CpShopService extends BasicService<CpShopMapper, CpShop> {
    @Autowired
    private CpShopService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
