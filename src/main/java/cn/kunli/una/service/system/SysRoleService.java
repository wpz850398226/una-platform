package cn.kunli.una.service.system;

import cn.kunli.una.annotation.MyCacheEvict;
import cn.kunli.una.mapper.SysRoleMapper;
import cn.kunli.una.pojo.system.SysRole;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.hutool.core.util.StrUtil;
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
    public SysResult saveRecord(SysRole entity) {
        SysResult sysResult = super.saveRecord(entity);
        if(sysResult.getIsSuccess()){
            //通过角色id匹配所有权限，新增rolePermission
            sysRolePermissionService.insertByRoleId(entity.getId());
        }
        return sysResult;
    }

    //格式化保存实例
    @Override
    public SysRole initialize(SysRole obj) {
        if (StrUtil.isBlank(obj.getCompanyTypeDcode())) obj.setCompanyTypeDcode("0");
        super.initialize(obj);
        return obj;
    }

}
