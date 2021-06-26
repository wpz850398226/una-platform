package cn.kunli.una.service.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpAttribute;
import cn.kunli.una.mapper.CpAttributeMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商城-规格属性表(CpAttribute)表服务类
 *
 * @author Ponzio
 * @since 2021-06-23 23:33:41
 */
@Service
public class CpAttributeService extends BasicService<CpAttributeMapper, CpAttribute> {
    @Autowired
    private CpAttributeService thisProxy;
    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
