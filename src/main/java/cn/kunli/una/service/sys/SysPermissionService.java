package cn.kunli.una.service.sys;

import cn.kunli.una.annotation.MyCacheEvict;
import cn.kunli.una.mapper.SysPermissionMapper;
import cn.kunli.una.pojo.sys.SysDictionary;
import cn.kunli.una.pojo.sys.SysEntity;
import cn.kunli.una.pojo.sys.SysPermission;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.UnaMapUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * (SysPermission)表服务实现类
 *
 * @author Ponzio
 * @since 2020-05-10 06:46:06
 */
@Service
@Transactional
public class SysPermissionService extends BasicService<SysPermissionMapper, SysPermission> {
    @Autowired
    private SysRolePermissionService sysRolePermissionService;
    @Autowired
    private SysPermissionService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    /**
     * 插入数据,只操作record中的非空属性
     *
     * @param entity
     * @return
     */
    @Override
    @MyCacheEvict(value = "list")
    public SysResult saveRecord(SysPermission entity) {
        SysResult sysResult = super.saveRecord(entity);
        if(sysResult.getIsSuccess()){
            //通过权限id匹配所有角色，新增roleFunction
            sysRolePermissionService.insertByPermissionId(entity.getId());
        }
        return sysResult;
    }

    /**
     * 通过用户id查询权限代码
     *
     * @param userId
     * @return
     */
    public List<String> selectCodeByUserIdCollection(Integer userId) {
        return this.mapper.selectCodeByUserIdCollection(userId);
    }

    /**
     * 保存实例格式化
     *
     * @param obj
     * @return
     */
    public SysPermission initialize(SysPermission obj) {
        //如果权限名称为空，自动拼接名称
        if (StrUtil.isBlank(obj.getName())) {
            SysEntity sysEntity = sysEntityService.getById(obj.getEntityId());
            if (sysEntity != null) {
                SysDictionary typeDictionary = sysDictionaryService.selectOne(UnaMapUtil.getMap("code",obj.getTypeDcode()));
                SysDictionary platformDictionary = sysDictionaryService.selectOne(UnaMapUtil.getMap("code",sysEntity.getPlatformDcode()));
                obj.setName(typeDictionary.getName() + sysEntity.getName()+"-"+platformDictionary.getName());
            }
        }
        super.initialize(obj);
        return obj;
    }
}
