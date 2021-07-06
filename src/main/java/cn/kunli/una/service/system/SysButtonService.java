package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysButtonMapper;
import cn.kunli.una.pojo.system.SysButton;
import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.pojo.system.SysPermission;
import cn.kunli.una.service.BasicService;
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
