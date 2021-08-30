package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.chanpin.CpGoods;
import cn.kunli.una.pojo.chanpin.CpModel;
import cn.kunli.una.pojo.chanpin.CpSpecification;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.duohui.chanpin.CpGoodsService;
import cn.kunli.una.service.duohui.chanpin.CpModelService;
import cn.kunli.una.utils.common.DateUtil;
import cn.kunli.una.utils.common.ListUtil;
import cn.kunli.una.utils.common.MapUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 账号(CpGoods)表控制层
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:00
 */
@Controller
@RequestMapping("/cp/goods")
public class CpGoodsController extends BaseController<CpGoodsService, CpGoods> {

    @Autowired
    private CpIndexController cpIndexController;
    @Autowired
    private CpModelService cpModelService;

    /**
     * 打开前端 商品详情
     * @param model
     * @return
     */
    @RequestMapping("/fDetail/{id}")
    public String fDetail(Model model, @PathVariable Integer id,String attributeName) {
        //增加点击量
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.setEntity(new CpGoods().setId(id));
        updateWrapper.setSql("view_amount = view_amount + 1");
        service.update(updateWrapper);

        //查询商品
        CpGoods record = service.parse(ListUtil.getList(service.getById(id))).get(0);
        //查询规格
        CpModel cpModel;
        if(StringUtils.isBlank(attributeName)){
            //查询默认规格属性
            List<CpSpecification> specificationList = record.getSpecificationList();
            List<String> attributeNameList = new ArrayList<>();
            if(CollectionUtils.isNotEmpty(specificationList)){
                for (CpSpecification cpSpecification : specificationList) {
                    String attributeNames = cpSpecification.getAttributeNames();
                    if(attributeNames.contains(",")){
                        attributeNameList.add(attributeNames.substring(0,attributeNames.indexOf(",")));
                    }else{
                        attributeNameList.add(attributeNames);
                    }

                }
            }
            cpModel = cpModelService.selectOne(
                    MapUtil.buildHashMap().put("goodsId",record.getId()).put("name",ListUtil.listToStr(attributeNameList)).build());
        }else{
            //查询指定规格
            cpModel = cpModelService.selectOne(
                    MapUtil.buildHashMap().put("goodsId",record.getId()).put("name",attributeName).build());
        }

        if(cpModel !=null){
            cpModel.setNameList(ListUtil.strToList(cpModel.getName()));
            record.setCheckedModel(cpModel);
        }
        model.addAttribute("record",record);
        cpIndexController.getCommonItem(model);

        return "duohui/chanpin/procon";
    }

    //刷新
    @PutMapping("/refresh/{id}")
    @ResponseBody
    public SysResult refresh(@PathVariable Integer id) {
        return service.updateRecordById((CpGoods) new CpGoods().setRefreshTime(new Date()).setId(id));
    }

    //置顶
    @PutMapping("/stick/{id}")
    @ResponseBody
    public SysResult stick(@PathVariable Integer id) {
        CpGoods cpGoods = service.getById(id);
        Date stickDeadline;
        if(cpGoods.getStickDeadline()==null||DateUtil.compareDate(new Date(),cpGoods.getStickDeadline())){
            stickDeadline = DateUtil.getNextDay(new Date(),1);
        }else{
            stickDeadline = DateUtil.getNextDay(cpGoods.getStickDeadline(),1);
        }
        return service.updateRecordById((CpGoods) new CpGoods().setStickDeadline(stickDeadline).setId(id));
    }


}
