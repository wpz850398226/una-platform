package cn.kunli.una.service.system;

import cn.kunli.una.pojo.chanpin.CpGoods;
import cn.kunli.una.mapper.CpGoodsMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 账号(CpGoods)表服务类
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:00
 */
@Service
public class CpGoodsService extends BasicService<CpGoodsMapper, CpGoods> {
    @Autowired
    private CpGoodsService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
