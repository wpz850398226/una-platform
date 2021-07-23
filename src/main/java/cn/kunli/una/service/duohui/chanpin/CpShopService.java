package cn.kunli.una.service.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpShop;
import cn.kunli.una.mapper.CpShopMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

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

    @Override
    public CpShop initialize(CpShop obj) {
        obj = super.initialize(obj);
        if(obj.getId()==null){
            obj.setCode(UUID.randomUUID().toString().replace("-",""));
            obj.setRefreshTime(new Date());
            obj.setStickDeadline(new Date());
        }else{
            if(obj.getIsAudit()==null)obj.setIsAudit(false);    //如果修改，默认改为未审核
        }
        return obj;
    }
}
