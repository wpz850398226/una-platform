package cn.kunli.una.service.system;

import cn.kunli.una.annotation.MyCacheEvict;
import cn.kunli.una.mapper.SysRoleMapper;
import cn.kunli.una.pojo.system.SysRole;
import cn.kunli.una.service.BasicService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (SysRole)表服务实现类
 *
 * @author Ponzio
 * @since 2020-05-08 18:04:54
 */
@Service
public class SysRoleService extends BasicService<SysRoleMapper, SysRole> {
    @Autowired
    private SysRolePermissionService sysRolePermissionService;
    @Autowired
    private SysRoleService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    /**
     * 插入数据,只操作record中的非空属性
     *
     * @param record
     * @return
     */
    @Override
    @MyCacheEvict(value = "list")
    public boolean save(SysRole entity) {
        boolean saveResult = super.save(entity);
        if(saveResult){
            //通过角色id匹配所有权限，新增rolePermission
            sysRolePermissionService.insertByRoleId(entity.getId());
        }
        return saveResult;
    }

    //格式化保存实例
    @Override
    public SysRole saveFormat(SysRole obj) {
        if (StringUtils.isBlank(obj.getCompanyTypeDcode())) obj.setCompanyTypeDcode("0");
        super.saveFormat(obj);
        return obj;
    }

}
