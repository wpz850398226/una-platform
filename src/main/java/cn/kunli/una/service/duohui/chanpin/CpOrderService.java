package cn.kunli.una.service.duohui.chanpin;

import cn.kunli.una.annotation.MyCacheEvict;
import cn.kunli.una.mapper.CpOrderMapper;
import cn.kunli.una.pojo.chanpin.CpDelivery;
import cn.kunli.una.pojo.chanpin.CpModel;
import cn.kunli.una.pojo.chanpin.CpOrder;
import cn.kunli.una.pojo.chanpin.CpOrderItem;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.service.ali.AlipayService;
import cn.kunli.una.utils.BaseUtil;
import cn.kunli.una.utils.common.ListUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 商城-订单类(CpOrder)表服务类
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:02
 */
@Service
public class CpOrderService extends BasicService<CpOrderMapper, CpOrder> {
    @Autowired
    private CpOrderService thisProxy;
    @Autowired
    private CpModelService cpModelService;
    @Autowired
    private CpDeliveryService cpDeliveryService;
    @Autowired
    private CpOrderItemService cpOrderItemService;
    @Autowired
    private AlipayService alipayService;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    @Override
    @MyCacheEvict(value = "list")
    public SysResult saveRecord(CpOrder entity) {
        if(CollectionUtils.isNotEmpty(entity.getModelIdList())&&CollectionUtils.isNotEmpty(entity.getVolumeList())){
            //保存新的收件人信息
            if(entity.getIsNewDelivery()!=null&&entity.getIsNewDelivery()){
                CpDelivery delivery = entity.getDelivery();
                SysResult sysResult = cpDeliveryService.saveRecord(delivery);
                if(!sysResult.getIsSuccess())return sysResult;
                entity.setDeliveryId(delivery.getId());
            }

            List<Integer> modelIdList = entity.getModelIdList();
            List<Integer> volumeList = entity.getVolumeList();

            //一个店铺对应一个订单
            Map<Integer, CpOrder> shopOrderMap = new LinkedHashMap<>();
            for (int i = 0; i < modelIdList.size(); i++) {
                //查询店铺id
                CpModel cpModel = cpModelService.getById(modelIdList.get(i));
                Integer companyId = cpModel.getCompanyId();
                CpOrderItem cpOrderItem = new CpOrderItem().setModelId(modelIdList.get(i)).setVolume(volumeList.get(i));
                if(shopOrderMap.containsKey(companyId)){
                    shopOrderMap.get(companyId).getCpOrderItemList().add(cpOrderItem);
                }else{
                    shopOrderMap.put(companyId, (CpOrder) new CpOrder().setDeliveryId(entity.getDeliveryId())
                            .setDeliveryTypeDcode(entity.getDeliveryTypeDcode())
                            .setPaymentTypeDcode(entity.getPaymentTypeDcode())
                            .setCpOrderItemList(ListUtil.getList(cpOrderItem)).setCompanyId(companyId));
                }
            }

            //创建订单集合，一起结算
            List<CpOrder> orderList = new ArrayList<>();

            if(MapUtils.isNotEmpty(shopOrderMap)){
                for (Map.Entry<Integer, CpOrder> shopOrderEntry : shopOrderMap.entrySet()) {
                    CpOrder cpOrder = shopOrderEntry.getValue();
                    //保存订单
                    SysResult sysResult = super.saveRecord(cpOrder);
                    if(sysResult.getIsSuccess()){
                        //加入订单集合
                        orderList.add(cpOrder);
                        //订单保存成功，保存订单项
                        for (CpOrderItem cpOrderItem : cpOrder.getCpOrderItemList()) {
                            SysResult orderItemResult = cpOrderItemService.saveRecord(cpOrderItem.setOrderId(cpOrder.getId()));
                            if(!orderItemResult.getIsSuccess())return orderItemResult;
                        }
                        //删除对应购物车记录

                    }else {
                        //订单保存失败
                        return sysResult;
                    }

                }

                //批量结算
                return this.settle(orderList);

            }

        }

        return SysResult.fail();
    }

    @Override
    public CpOrder initialize(CpOrder obj) {
        obj = super.initialize(obj);
        if(obj.getId()==null){
            //订单编号，订单状态
            obj.setCode(BaseUtil.getPrimaryId()).setStatusDcode("dh_orderStatus_unpaid");//新建订单，状态为待支付
            //订单名称
            obj.setName(obj.getCreatorName()+ obj.getCode());
            if(CollectionUtils.isNotEmpty(obj.getCpOrderItemList())){
                //设置订单商品总件数
                Integer totalVolume = 0;
                Double orderAmount = 0D;
                for (CpOrderItem orderItem : obj.getCpOrderItemList()) {
                    totalVolume = totalVolume + orderItem.getVolume();
                    CpModel cpModel = cpModelService.getById(orderItem.getModelId());
                    orderAmount = orderAmount + cpModel.getSellingPrice()*orderItem.getVolume();
                }
                obj.setVolume(totalVolume)
                        .setOrderAmount(orderAmount)
                        .setBargainAmount(orderAmount);//新增订单默认成交价就是原价，议价后再改成交价
            }

        }
        return obj;
    }

    //订单（一或多）结算
    public SysResult settle(List<CpOrder> objList){
        CpOrder sample = new CpOrder();
        if(objList.size()==1){
            sample = objList.get(0);
        }else{
            for (CpOrder obj : objList) {
                sample.setCode(sample.getCode() + obj.getCode());
                sample.setBargainAmount(sample.getBargainAmount() + obj.getBargainAmount());
                sample.setName(sample.getName() + obj.getName());
            }
        }

        Map<String, String> orderParam = new HashMap<>();
        if(StringUtils.isBlank(sample.getCode())) return SysResult.fail("订单编号异常，结算失败");
        orderParam.put("WIDout_trade_no", sample.getCode());    //订单编号
        orderParam.put("WIDsubject", sample.getCode());    //订单名称
        if(sample.getBargainAmount()==null) return SysResult.fail("订单成交价异常，结算失败");
        orderParam.put("WIDtotal_amount", String.valueOf(sample.getBargainAmount()));    //成交金额
        if(StringUtils.isNotBlank(sample.getRemark()))orderParam.put("WIDout_trade_no", sample.getRemark());    //商品描述，可空
        SysResult toPayResult = alipayService.toPay(orderParam);
        return toPayResult;
    }
}
