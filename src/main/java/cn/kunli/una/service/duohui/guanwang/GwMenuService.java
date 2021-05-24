package cn.kunli.una.service.duohui.guanwang;

import cn.kunli.una.mapper.GwMenuMapper;
import cn.kunli.una.pojo.duohui.guanwang.GwMenu;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.MapUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单(GwMenu)表服务类
 *
 * @author Ponzio
 * @since 2021-03-26 11:09:52
 */
@Service
public class GwMenuService extends BasicService<GwMenuMapper, GwMenu> {
    @Autowired
    private GwMenuService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }


    @Override
    public List<GwMenu> resultFormat(List<GwMenu> list) {
        if(CollectionUtils.isEmpty(list))return list;
        list = super.resultFormat(list);
        for (GwMenu record : list) {
            if(record.getLevel()<2){
                List<GwMenu> subList = thisProxy.list(wrapperUtil.mapToWrapper(MapUtil.getMap("parentId", record.getId())));
                if(CollectionUtils.isNotEmpty(subList)){
                    this.resultFormat(subList);
                }
                record.setChildren(subList);
            }
        }
        return list;
    }

    /**
     * 保存实例格式化
     *
     * @param obj
     * @return
     */
    @Override
    public GwMenu saveFormat(GwMenu obj) {

        if (obj.getId() == null) {
            if (obj.getParentId() != null) {
                if (obj.getSortOrder() == null)
                    obj.setSortOrder(this.count(wrapperUtil.mapToWrapper(MapUtil.getMap("parentId",obj.getParentId()))) + 1);
                obj.setLevel(thisProxy.getById(obj.getParentId()).getLevel() + 1);
            }
            if (StringUtils.isBlank(obj.getRoute())) obj.setRoute("SysManage");
            if (StringUtils.isBlank(obj.getType())) obj.setType("链接");
        }


        super.saveFormat(obj);

        return obj;
    }
}
