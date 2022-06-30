package cn.kunli.una.service.sys;

import cn.kunli.una.handler.UnaResponseException;
import cn.kunli.una.mapper.SysButtonMapper;
import cn.kunli.una.pojo.sys.SysButton;
import cn.kunli.una.pojo.sys.SysEntity;
import cn.kunli.una.pojo.sys.SysField;
import cn.kunli.una.pojo.sys.SysPermission;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.UnaMapUtil;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import cn.hutool.core.util.StrUtil;
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

    /*@Override
    @SneakyThrows
    public void saveValidate(SysButton obj) {
        super.saveValidate(obj);
        if(StrUtil.isNotBlank(obj.getEvent())){
            List<SysButton> objList = sysButtonService.selectList(UnaMapUtil.getMap("event", obj.getEvent().trim()));
            if (CollectionUtils.isNotEmpty(objList) && !objList.get(0).getId().equals(obj.getId())) {
                throw new UnaResponseException("事件名重复，保存失败:" + obj.getEvent());
            }
        }
    }*/

    @Override
    public List<SysButton> parse(List<SysButton> list) {
        list = super.parse(list);

        for (SysButton sysButton : list) {
            if(sysButton.getPermissionId()!=null){
                SysPermission sysPermission = sysPermissionService.getById(sysButton.getPermissionId());
                SysEntity sysEntity = sysEntityService.getById(sysPermission.getEntityId());
                String typeDcode = sysPermission.getTypeDcode();
                if(sysEntity!=null&& StrUtil.isNotBlank(typeDcode)){
                    String substring = typeDcode.substring(typeDcode.lastIndexOf("_")+1);
                    Map<String, Object> map = sysButton.getMap();
                    if(map==null)map = new HashMap<>();
                    map.put("permissionCode", sysEntity.getCode()+":"+substring);
                    sysButton.setMap(map);
                }

            }

            if(sysButton.getFormFieldId()!=null){
                SysField formField = sysFieldService.getById(sysButton.getFormFieldId());
                if(formField!=null)sysButton.setFormFieldCode(formField.getAssignmentCode());
            }
        }

        return list;
    }
}
