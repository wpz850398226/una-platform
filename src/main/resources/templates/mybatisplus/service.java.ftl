package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import ${cfg.searchbeanPage}.${entity}SearchBean;
import ${cfg.dictPage}.${entity}Status;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glodon.pcop.supervisemodel.IdGenerator;
import com.glodon.pcop.supervisesvc.bussiness.audit.entity.AuditCommonRecords;
import com.glodon.pcop.supervisesvc.bussiness.audit.service.IAuditCommonRecordsService;
import com.glodon.pcop.supervisesvc.common.handles.ParseHandle;
import java.time.LocalDateTime;
/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceName} : ${superServiceClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceName} extends ${superServiceClass}<${table.mapperName}, ${entity}> {

    @Autowired
    private ${table.serviceName} thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
</#if>
