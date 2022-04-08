package cn.kunli.una.service.duohui.chanpin;

import cn.kunli.una.mapper.CpModelMapper;
import cn.kunli.una.pojo.chanpin.CpGoods;
import cn.kunli.una.pojo.chanpin.CpModel;

import cn.kunli.una.pojo.sys.SysCompany;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private CpGoodsService cpGoodsService;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }


    @Override
    public CpModel initialize(CpModel obj) {
        obj = super.initialize(obj);

        if(obj.getGoodsId()!=null){
            CpGoods cpGoods = cpGoodsService.getById(obj.getGoodsId());
            obj.setCompanyId(cpGoods.getCompanyId());
        }

        return obj;
    }

    @Override
    public List<CpModel> parse(List<CpModel> list) {
        list = super.parse(list);

        for (CpModel cpModel : list) {
            if(cpModel.getGoodsId()!=null){
                CpGoods cpGoods = cpGoodsService.getById(cpModel.getGoodsId());
                if(cpGoods!=null){
                    cpModel.setGoodsName(cpGoods.getName());
                    SysCompany sysCompany = sysCompanyService.getById(cpGoods.getCompanyId());
                    if(sysCompany!=null){
                        cpModel.setShopName(sysCompany.getName());
                    }
                }
            }
        }

        return list;
    }
}
