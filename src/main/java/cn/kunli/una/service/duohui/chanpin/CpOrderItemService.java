package cn.kunli.una.service.duohui.chanpin;

import cn.kunli.una.mapper.CpOrderItemMapper;
import cn.kunli.una.pojo.chanpin.CpModel;
import cn.kunli.una.pojo.chanpin.CpOrder;
import cn.kunli.una.pojo.chanpin.CpOrderItem;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商城-订单明细表(CpOrderDetail)表服务类
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:03
 */
@Service
public class CpOrderItemService extends BasicService<CpOrderItemMapper, CpOrderItem> {
    @Autowired
    private CpOrderItemService thisProxy;
    @Autowired
    private CpModelService cpModelService;
    @Autowired
    private CpOrderService cpOrderService;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    @Override
    public SysResult validate(CpOrderItem obj) {
        SysResult validate = super.validate(obj);
        if(!validate.getIsSuccess())return validate;

        if(obj.getBargainPrice()!=null&&obj.getId()!=null){
            //议价
            CpOrderItem target = thisProxy.getById(obj.getId());
            CpModel cpModel = cpModelService.getById(target.getModelId());
            if(obj.getBargainPrice()>cpModel.getCeilingPrice()||obj.getBargainPrice()<cpModel.getFloorPrice()){
                return SysResult.fail("议价不在范围内，最低限价："+cpModel.getFloorPrice()+";最高限价："+cpModel.getCeilingPrice());
            }
        }

        return SysResult.success();
    }

    @Override
    public CpOrderItem initialize(CpOrderItem obj) {
        obj = super.initialize(obj);

        if(obj.getId()==null){
            if(obj.getModelId()!=null){
                CpModel cpModel = cpModelService.getById(obj.getModelId());
                obj.setGoodsId(cpModel.getGoodsId()).setBargainPrice(cpModel.getSellingPrice());//新增订单项默认成交价为原价，议价后再改成交价
            }
        }else{
            if(obj.getBargainPrice()!=null){
                CpOrderItem target = thisProxy.getById(obj.getId());
                if(target.getBargainPrice()!=obj.getBargainPrice()){
                    //新成交价与原成交价不等，议价成功，修改订单成交金额
                    Double difference = (obj.getBargainPrice()- target.getBargainPrice())*target.getVolume();   //差额
                    CpOrder cpOrder = cpOrderService.getById(target.getOrderId());
                    cpOrderService.updateRecordById((CpOrder) new CpOrder().setBargainAmount(cpOrder.getBargainAmount()+difference)
                            .setId(cpOrder.getId()));
                }
            }
        }

        return obj;
    }

    @Override
    public List<CpOrderItem> parse(List<CpOrderItem> list) {
        list = super.parse(list);

        for (CpOrderItem cpOrderItem : list) {
            if(cpOrderItem.getModelId()!=null){
                CpModel cpModel = cpModelService.getById(cpOrderItem.getModelId());
                if(cpModel.getFileId()!=null){
                    cpModel = cpModelService.parse(ListUtil.getList(cpModel)).get(0);
                    cpOrderItem.setModelFileUrl(String.valueOf(cpModel.getMap().get("fileUrl")));
                }
            }
        }


        return list;
    }
}
