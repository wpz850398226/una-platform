package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysMenuMapper;
import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.pojo.system.SysMenu;
import cn.kunli.una.pojo.system.SysPermission;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.UserUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysMenuService extends BasicService<SysMenuMapper, SysMenu> {

    @Autowired
    private SysMenuService thisProxy;
    @Autowired
    private SysPermissionService sysPermissionService;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    //通过用户id查询所有菜单，并按层级排序
    public List<SysMenu> selectByAccount() {
        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
        List<SysMenu> menuList = thisProxy.parse(thisProxy.selectList(MapUtil.getMap("level",1)));
        List<String> permissionCodeList = loginUser.getPermissionCodeList();
        List<String> retriveList = permissionCodeList.stream().filter((String pc) -> pc.indexOf(":retrieve") != -1).collect(Collectors.toList());

        //移除没有权限的菜单
        List<SysMenu> formatMenuList = filterByPermission(menuList, retriveList);
        //移除空菜单
        formatMenuList = filterWithNull(formatMenuList);
        return formatMenuList;
    }

    //移除无权限查看的菜单
    private List<SysMenu> filterByPermission(List<SysMenu> menuList,List<String> codeList){
        Iterator<SysMenu> iterator = menuList.iterator();
        while (iterator.hasNext()) {
            SysMenu parentMenu = iterator.next();
            if(parentMenu.getType().equals("列表")){
                //如果是列表，则遍历子菜单
                if(CollectionUtils.isNotEmpty(parentMenu.getChildren())){
                    List<SysMenu> formatMenuList = filterByPermission(parentMenu.getChildren(), codeList);
                    parentMenu.setChildren(formatMenuList);
                }
            }else{
                //判断是否有该菜单权限，没有则移除
                if(StringUtils.isBlank(parentMenu.getCode())||!codeList.contains(parentMenu.getCode()+":retrieve")){
                    iterator.remove();
                }
            }
        }

        return menuList;
    }

    //移除空菜单
    private List<SysMenu> filterWithNull(List<SysMenu> menuList){
        Iterator<SysMenu> iterator = menuList.iterator();
        while (iterator.hasNext()) {
            SysMenu parentMenu = iterator.next();
            if(parentMenu.getType().equals("列表")){
                if(CollectionUtils.isEmpty(parentMenu.getChildren())){
                    iterator.remove();
                }else{
                    List<SysMenu> children = filterWithNull(parentMenu.getChildren());
                    parentMenu.setChildren(children);
                }
            }
        }

        return menuList;
    }

    /*public List<SysMenu> selectTreeBySelective(SysMenu obj) {
        List<SysMenu> menuList = new ArrayList<>();
        if (obj.getId() == 100000) {
            menuList = thisProxy.selectList(null);
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
    }*/

    @Override
    public SysResult validate(SysMenu obj) {
        if(StringUtils.isNotBlank(obj.getName())){
            List<SysMenu> objList = thisProxy.selectList(MapUtil.getMap("name", obj.getName().trim()));
            if (CollectionUtils.isNotEmpty(objList) && !objList.get(0).getId().equals(obj.getId())) {
                return SysResult.fail("名称重复，保存失败:" + obj.getName());
            }
        }
        return SysResult.success();
    }

    /**
     * 保存实例格式化
     *
     * @param obj
     * @return
     */
    @Override
    public SysMenu initialize(SysMenu obj) {
        super.initialize(obj);
        if (obj.getId() == null) {
            if (obj.getParentId() != null) {
                if (obj.getSortOrder() == null)
                    obj.setSortOrder(this.count(getWrapper(MapUtil.getMap("parentId",obj.getParentId()))) + 1);
                obj.setLevel(thisProxy.getById(obj.getParentId()).getLevel() + 1);
            }
            if (StringUtils.isBlank(obj.getRoute())) obj.setRoute("SysManage");
            if (StringUtils.isBlank(obj.getType())) obj.setType("链接");
        }

        if(obj.getPermissionId()!=null){
            SysPermission sysPermission = sysPermissionService.getById(obj.getPermissionId());
            SysEntity sysEntity = sysEntityService.getById(sysPermission.getEntityId());
            obj.setCode(sysEntity.getCode());
        }

        return obj;
    }

    @Override
    public List<SysMenu> parse(List<SysMenu> list) {
        if(CollectionUtils.isEmpty(list))return list;
        list = super.parse(list);
        for (SysMenu record : list) {
            if(record.getLevel()<2){
                List<SysMenu> subList = thisProxy.list(getWrapper(MapUtil.getMap("parentId", record.getId())));
                if(CollectionUtils.isNotEmpty(subList)){
                    this.parse(subList);
                }
                record.setChildren(subList);
            }
        }
        return list;
    }
}
