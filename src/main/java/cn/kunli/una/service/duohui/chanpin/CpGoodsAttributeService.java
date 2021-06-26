package cn.kunli.una.service.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpGoodsAttribute;
import cn.kunli.una.mapper.CpGoodsAttributeMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商城-商品规格表(CpGoodsAttribute)表服务类
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:01
 */
@Service
public class CpGoodsAttributeService extends BasicService<CpGoodsAttributeMapper, CpGoodsAttribute> {
    @Autowired
    private CpGoodsAttributeService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
