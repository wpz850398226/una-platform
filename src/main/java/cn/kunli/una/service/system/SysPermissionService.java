package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysPermissionMapper;
import cn.kunli.una.pojo.system.SysDictionary;
import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.pojo.system.SysPermission;
import cn.kunli.una.service.BasicService;
import org.apache.commons.lang3.StringUtils;
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
    private SysDictionaryService sysDictionaryService;

    /**
     * 插入数据,只操作record中的非空属性
     *
     * @param entity
     * @return
     */
    @Override
    public boolean save(SysPermission entity) {
        boolean saveResult = super.save(entity);
        if(saveResult){
            //通过权限id匹配所有角色，新增roleFunction
            sysRolePermissionService.insertByPermissionId(entity.getId());
        }
        return saveResult;
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
    public SysPermission saveFormat(SysPermission obj) {
        //如果权限名称为空，自动拼接名称
        if (StringUtils.isBlank(obj.getName())) {
            SysEntity sysEntity = sysEntityService.selectById(obj.getEntityId());
            if (sysEntity != null) {
                SysDictionary typeDictionary = sysDictionaryService.queryFromRedis(obj.getTypeDcode());
                SysDictionary platformDictionary = sysDictionaryService.queryFromRedis(sysEntity.getPlatformDcode());
                obj.setName(typeDictionary.getName() + sysEntity.getName()+"-"+platformDictionary.getName());
            }
        }
        super.saveFormat(obj);
        return obj;
    }
}
