package cn.kunli.una.service.sys;

import cn.kunli.una.mapper.SysCompanyMapper;
import cn.kunli.una.pojo.sys.SysCompany;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

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

        if(obj.getId()==null){
            obj.setCode(UUID.randomUUID().toString().replace("-",""));
            obj.setRefreshTime(new Date());
            obj.setStickDeadline(new Date());
        }else{
            if(obj.getIsAudit()==null)obj.setIsAudit(false);    //如果修改，默认改为未审核
        }

        return obj;
    }
}
