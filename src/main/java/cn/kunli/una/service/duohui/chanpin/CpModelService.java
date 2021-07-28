package cn.kunli.una.service.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpModel;
import cn.kunli.una.mapper.CpModelMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商城-商品规格表(CpModel)表服务类
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:01
 */
@Service
public class CpModelService extends BasicService<CpModelMapper, CpModel> {
    @Autowired
    private CpModelService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
