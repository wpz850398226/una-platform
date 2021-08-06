package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysCompanyMapper;
import cn.kunli.una.pojo.system.SysCompany;
import cn.kunli.una.pojo.system.SysDictionary;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.MapUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (SysCompany)表服务实现类
 *
 * @author Ponzio
 * @since 2020-06-03 15:58:32
 */
@Service
public class SysCompanyService extends BasicService<SysCompanyMapper, SysCompany> {
    @Autowired
    private SysCompanyService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    //格式化实体类
    public SysCompany initialize(SysCompany obj) {
        super.initialize(obj);
        //赋值行业类型
        if(StringUtils.isNotBlank(obj.getThirdryIndustryDcode())){
            if(StringUtils.isBlank(obj.getSecondryIndustryDcode())){
                SysDictionary thirdryIndustryDic = sysDictionaryService.selectOne(MapUtil.getMap("code", obj.getThirdryIndustryDcode()));
                obj.setSecondryIndustryDcode(thirdryIndustryDic.getParentCode());
                if(StringUtils.isBlank(obj.getPrimaryIndustryDcode())){
                    SysDictionary secondryIndustryDic = sysDictionaryService.getById(thirdryIndustryDic.getParentId());
                    obj.setPrimaryIndustryDcode(secondryIndustryDic.getParentCode());
                }

            }
        }

        return obj;
    }
}
