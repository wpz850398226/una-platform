package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysButtonMapper;
import cn.kunli.una.pojo.system.SysButton;
import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.pojo.system.SysMenu;
import cn.kunli.una.pojo.system.SysPermission;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.MapUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (SysButton)表服务实现类
 *
 * @author Ponzio
 * @since 2020-05-07 08:09:05
 */
@Service
public class SysButtonService extends BasicService<SysButtonMapper, SysButton> {
    @Override
    public BasicService getThisProxy() {
        return sysButtonService;
    }
    @Autowired
    private SysPermissionService sysPermissionService;

    @Override
    public SysResult validate(SysButton obj) {
        SysResult validate = super.validate(obj);
        if(!validate.getIsSuccess())return validate;
        if(StringUtils.isNotBlank(obj.getEvent())){
            List<SysButton> objList = getThisProxy().selectList(MapUtil.getMap("event", obj.getEvent().trim()));
            if (CollectionUtils.isNotEmpty(objList) && !objList.get(0).getId().equals(obj.getId())) {
                return SysResult.fail("事件名重复，保存失败:" + obj.getEvent());
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
    public SysButton initialize(SysButton obj) {
        super.initialize(obj);
        if(obj.getPermissionId()!=null){
            SysPermission sysPermission = sysPermissionService.getById(obj.getPermissionId());
            if(sysPermission!=null&&sysPermission.getEntityId()!=null){
                SysEntity sysEntity = sysEntityService.getById(sysPermission.getEntityId());
                String typeDcode = sysPermission.getTypeDcode();
                obj.setPermissionCode(sysEntity.getCode()+":"+typeDcode.substring(typeDcode.lastIndexOf("_")+1));
            }
        }

        return obj;
    }

    @Override
    public List<SysButton> parse(List<SysButton> list) {
        list = super.parse(list);

        for (SysButton sysButton : list) {
            if(sysButton.getPermissionId()!=null){
                SysPermission sysPermission = sysPermissionService.getById(sysButton.getPermissionId());
                SysEntity sysEntity = sysEntityService.getById(sysPermission.getEntityId());
                String typeDcode = sysPermission.getTypeDcode();
                if(sysEntity!=null&& StringUtils.isNotBlank(typeDcode)){
                    String substring = typeDcode.substring(typeDcode.lastIndexOf("_")+1);
                    Map<String, Object> map = sysButton.getMap();
                    if(map==null)map = new HashMap<>();
                    map.put("permissionCode", sysEntity.getCode()+":"+substring);
                    sysButton.setMap(map);
                }

            }
        }

        return list;
    }
}
