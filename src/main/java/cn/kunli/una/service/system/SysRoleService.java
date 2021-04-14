package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysRoleMapper;
import cn.kunli.una.pojo.system.SysRole;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * (SysRole)表服务实现类
 *
 * @author Ponzio
 * @since 2020-05-08 18:04:54
 */
@Service
public class SysRoleService extends BaseService<SysRoleMapper, SysRole> {
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    /**
     * 插入数据,只操作record中的非空属性
     *
     * @param record
     * @return
     */
    @Override
    @Transactional
    public SysResult insertSelective(SysRole record) {
        SysResult validationResult = this.validation(record);
        if (validationResult.getCode() != 200) return validationResult;
        int insertNum = this.mapper.insertSelective(this.saveFormat(record));
        if (insertNum > 0) {
            //通过角色id匹配所有权限，新增rolePermission
            sysRolePermissionService.insertByRoleId(record.getId());
            return SysResult.success();
        } else {
            return SysResult.fail();
        }
    }


    //格式化保存实例
    @Override
    public SysRole saveFormat(SysRole obj) {
        if (StringUtils.isBlank(obj.getCompanyTypeDcode())) obj.setCompanyTypeDcode("0");
        super.saveFormat(obj);
        return obj;
    }

}
