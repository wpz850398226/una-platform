package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysFieldMapper;
import cn.kunli.una.pojo.BasePojo;
import cn.kunli.una.pojo.system.SysButton;
import cn.kunli.una.pojo.system.SysDictionary;
import cn.kunli.una.pojo.system.SysField;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BaseService;
import cn.kunli.una.utils.common.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysFieldService extends BaseService<SysFieldMapper, SysField> {

    @Autowired
    private SysDictionaryService sysDictionaryService;
    @Autowired
    private SysAccountService sysAccountService;

    @Override
    public SysResult insertSelective(SysField record) {
        SysResult sysResult = super.insertSelective(record);
        if(sysResult.getCode()!=200)return sysResult;
        if(record.getEntityId()!=null){
            sysEntityService.deleteFromCacheByCode(record.getEntityId());
        }
        return sysResult;
    }

    public SysResult getDisplayValue(String assignmentCode, String value, BaseService bs) {
        if (StringUtils.isBlank(assignmentCode) || StringUtils.isBlank(value)) return SysResult.fail("查询失败：赋值编码或值为空");
        //字段赋值编码
        String substring = assignmentCode.substring(assignmentCode.length() - 5);
        BasePojo target = null;
        switch (substring) {
            case "Dcode"://字典
                target = sysDictionaryService.queryFromRedis(value);
                break;
            case "ityId"://实体 entityId
                target = sysEntityService.selectByPrimaryKey(Integer.valueOf(value));
                break;
            case "untId"://账号 accountId
                target = sysAccountService.selectByPrimaryKey(Integer.valueOf(value));
                break;
            case "eldId"://字段 fieldId
                target = sysFieldService.selectByPrimaryKey(Integer.valueOf(value));
                break;
            case "entId"://父id parentId
                target = bs.selectByPrimaryKey(Integer.valueOf(value));
                break;
            default:
                return SysResult.fail("查询失败：赋值编码 未识别");
        }
        if (target != null) return new SysResult().success("查询成功", target.getName());
        return SysResult.fail("查询失败：赋值编码 未识别");
    }

    /**
     * 保存实例格式化
     *
     * @param obj
     * @return
     */
    public SysField saveFormat(SysField obj) {
        super.saveFormat(obj);
        if (StringUtils.isNotBlank(obj.getRadioOptions())) obj.setRadioOptions(obj.getRadioOptions().replace("，", ","));
        if (obj.getId() == null) {
            if (StringUtils.isBlank(obj.getOptionNameFieldCode())) obj.setOptionNameFieldCode("name");
            if (StringUtils.isBlank(obj.getOptionValueFieldCode())) obj.setOptionValueFieldCode("id");
            if (StringUtils.isBlank(obj.getDqlName()))
                obj.setDqlName("record." + StringUtil.upperCharToUnderLine(obj.getAssignmentCode()));
        }

        return obj;
    }

    /**
     * 查询结果格式化
     *
     * @param list
     * @return
     */
    public List<SysField> resultFormat(List<SysField> list) {
        super.resultFormat(list);
        for (SysField obj : list) {
            if (StringUtils.isNotBlank(obj.getRadioOptions()))
                obj.setRadioOptionArray(StringUtils.split(obj.getRadioOptions(), ","));
        }
        return list;
    }
}
