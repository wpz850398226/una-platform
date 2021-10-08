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
        //移除没有权限的菜单 与不展示的菜单isEffect = false
        List<SysMenu> formatMenuList = filterByPermission(menuList, permissionCodeList);
        //移除空菜单
        formatMenuList = filterWithNull(formatMenuList);
        return formatMenuList;
    }

    //移除无权限查看的菜单
    private List<SysMenu> filterByPermission(List<SysMenu> menuList,List<String> codeList){
        Iterator<SysMenu> iterator = menuList.iterator();
        while (iterator.hasNext()) {
            SysMenu sysMenu = iterator.next();
            if(!sysMenu.getIsEffect()){
                iterator.remove();
                continue;
            }
            if(sysMenu.getType().equals("列表")){
                //如果是列表，则遍历子菜单
                if(CollectionUtils.isNotEmpty(sysMenu.getChildren())){
                    List<SysMenu> formatMenuList = filterByPermission(sysMenu.getChildren(), codeList);
                    sysMenu.setChildren(formatMenuList);
                }
            }else{
                //链接型菜单，判断菜单权限
                if(sysMenu.getPermissionId()!=null){
                    String permissionCode = "";
                    SysPermission sysPermission = sysPermissionService.getById(sysMenu.getPermissionId());
                    if(sysPermission!=null){
                        String typeDcode = sysPermission.getTypeDcode();
                        permissionCode = sysMenu.getCode()+":"+typeDcode.substring(typeDcode.lastIndexOf("_")+1);
                        //判断是否有该菜单权限，没有则移除
                        if(!codeList.contains(permissionCode)){
                            iterator.remove();
                        }
                    }
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

    @Override
    public SysResult validate(SysMenu obj) {
        if(StringUtils.isNotBlank(obj.getName())){
            List<SysMenu> objList = thisProxy.selectList(MapUtil.buildHashMap().put("name", obj.getName().trim()).put("parentId",obj.getParentId()).build());
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

        if(obj.getType().equals("列表")){
            obj.setRoute("Layout");
        }else{
            obj.setRoute("SysManage");
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
                List<SysMenu> subList = thisProxy.selectList(MapUtil.getMap("parentId", record.getId()));
                if(CollectionUtils.isNotEmpty(subList)){
                    this.parse(subList);
                }
                record.setChildren(subList);
            }
        }
        return list;
    }
}
