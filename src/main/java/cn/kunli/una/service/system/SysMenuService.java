package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysMenuMapper;
import cn.kunli.una.pojo.system.SysMenu;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.MapUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenuService extends BasicService<SysMenuMapper, SysMenu> {

    @Autowired
    private SysMenuService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    @Override
    public List<SysMenu> parse(List<SysMenu> list) {
        if(CollectionUtils.isEmpty(list))return list;
        list = super.parse(list);
        for (SysMenu record : list) {
            if(record.getLevel()<2){
                List<SysMenu> subList = thisProxy.list(wrapperUtil.mapToWrapper(MapUtil.getMap("parentId", record.getId())));
                if(CollectionUtils.isNotEmpty(subList)){
                    this.parse(subList);
                }
                record.setChildren(subList);
            }
        }
        return list;
    }

    //通过用户id查询所有菜单，并按层级排序
    public List<SysMenu> selectTreeBySelective(SysMenu obj) {
        List<SysMenu> menuList = new ArrayList<>();
        if (obj.getId() == 100000) {
            menuList = thisProxy.list();
        } else {
            menuList = mapper.selectTreeBySelective(obj);
        }

        List<SysMenu> firstMenuList = new ArrayList<>();
        List<SysMenu> secondMenuList = new ArrayList<>();
        List<SysMenu> thirdMenuList = new ArrayList<>();

        for (SysMenu sysMenu : menuList) {
            switch (sysMenu.getLevel()) {
                case 1:
                    firstMenuList.add(sysMenu);
                    break;
                case 2:
                    secondMenuList.add(sysMenu);
                    break;
                case 3:
                    thirdMenuList.add(sysMenu);
                    break;

                default:
                    break;
            }
        }

        for (SysMenu secondMenu : secondMenuList) {
            for (SysMenu thirdMenu : thirdMenuList) {
                if (thirdMenu.getParentId().equals(secondMenu.getId())) {
                    secondMenu.getChildren().add(thirdMenu);
                }
            }
        }

        for (SysMenu firstMenu : firstMenuList) {
            for (SysMenu secondMenu : secondMenuList) {
                if (secondMenu.getParentId().equals(firstMenu.getId())) {
                    firstMenu.getChildren().add(secondMenu);
                }
            }
        }

        return firstMenuList;
    }

    /**
     * 保存实例格式化
     *
     * @param obj
     * @return
     */
    @Override
    public SysMenu initialize(SysMenu obj) {

        if (obj.getId() == null) {
            if (obj.getParentId() != null) {
                if (obj.getSortOrder() == null)
                    obj.setSortOrder(this.count(wrapperUtil.mapToWrapper(MapUtil.getMap("parentId",obj.getParentId()))) + 1);
                obj.setLevel(thisProxy.getById(obj.getParentId()).getLevel() + 1);
            }
            if (StringUtils.isBlank(obj.getRoute())) obj.setRoute("SysManage");
            if (StringUtils.isBlank(obj.getType())) obj.setType("链接");
        }


        super.initialize(obj);

        return obj;
    }
}
