package ${package.Service};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
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
