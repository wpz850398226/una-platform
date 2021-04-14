package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysPermissionMapper;
import cn.kunli.una.pojo.system.SysDictionary;
import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.pojo.system.SysPermission;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BaseService;
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
public class SysPermissionService extends BaseService<SysPermissionMapper, SysPermission> {
    @Autowired
    private SysRolePermissionService sysRolePermissionService;
    @Autowired
    private SysDictionaryService sysDictionaryService;

    /**
     * 插入数据,只操作record中的非空属性
     *
     * @param record
     * @return
     */
    @Override
    public SysResult insertSelective(SysPermission record) {
        SysResult validationResult = this.validation(record);
        if (validationResult.getCode() != 200) return validationResult;
        int insertNum = this.mapper.insertSelective(this.saveFormat(record));
        if (insertNum > 0) {
            //删除所属实体缓存
            if(record.getEntityId()!=null){
                sysEntityService.deleteFromCacheByCode(record.getEntityId());
            }
            //通过权限id匹配所有角色，新增roleFunction
            sysRolePermissionService.insertByPermissionId(record.getId());
            return SysResult.success();
        } else {
            return SysResult.fail();
        }
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
            SysEntity sysEntity = sysEntityService.selectByPrimaryKey(obj.getEntityId());
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
