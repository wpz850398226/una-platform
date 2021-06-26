package cn.kunli.una.service.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpCart;
import cn.kunli.una.mapper.CpCartMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
