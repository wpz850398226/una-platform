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
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> {


    @Autowired
    IAuditCommonRecordsService iAuditCommonRecordsService;

    @Override
    public ${entity} save${entity}(${entity} obj, Integer optionType) {
        LocalDateTime  now  = LocalDateTime.now();
        obj.setCreateTime(now);
        obj.setUpdateTime(now);
        obj.setUpdateUser(obj.getCreateUser());
        obj.setUpdateUserName(obj.getCreateUserName());
        obj.setApplyUser(obj.getCreateUserName());//填报人
        obj.setApplyUserName(obj.getCreateUser());
        obj.setApplyTime(now.toLocalDate());
        obj.setFlowId(IdGenerator.getUniqueId());//流程id  对应日志表中的流程id

        switch (optionType) {
            case 1://草稿
                obj.setStatus(${entity}Status.draft.getCode());
                break;
            case 2://提交
                obj.setStatus(${entity}Status.submit.getCode());
                obj.setSubmitTime(now.toLocalDate());
                obj.setSubmitUser(obj.getUpdateUser());
                obj.setSubmitUserName(obj.getUpdateUserName());
                break;
            default:
        }
        this.save(obj);
        if(optionType!=1)saveAuditRecord(obj);//操作类型不为草稿时记录审核日志
        return obj;
    }


    @Override
    public ${entity} update${entity}(${entity} obj, Integer optionType) {
        ${entity}  old = this.getById(obj.getId());
        obj.setFlowId(old.getFlowId());
        LocalDateTime  now  = LocalDateTime.now();
        obj.setUpdateTime(now);
        obj.setUpdateUser(obj.getUpdateUser());
        obj.setUpdateUserName(obj.getUpdateUserName());
        switch (optionType) {
            case 1://草稿

                break;
            case 2://提交
                obj.setStatus(${entity}Status.submit.getCode());
                obj.setSubmitTime(now.toLocalDate());
                obj.setSubmitUser(obj.getUpdateUser());
                obj.setSubmitUserName(obj.getUpdateUserName());
                break;
            default:
        }
        this.updateById(obj);
        if(optionType!=1)saveAuditRecord(obj);//操作类型不为草稿时记录审核日志
        return obj;
    }

    @Override
    public ${entity} audit${entity}(${entity} obj, Integer optionType) {
        ${entity}  old = this.getById(obj.getId());
        obj.setFlowId(old.getFlowId());
        LocalDateTime  now  = LocalDateTime.now();
        obj.setUpdateTime(now);
        obj.setUpdateUser(obj.getUpdateUser());
        obj.setUpdateUserName(obj.getUpdateUserName());
        switch (optionType) {
            case 1://退回
                obj.setStatus(${entity}Status.reject.getCode());
                break;
            case 2://审核
                obj.setStatus(${entity}Status.effect.getCode());
                break;
            default:
        }
        obj.setAuditTime(now.toLocalDate());
        obj.setAuditUser(obj.getUpdateUser());
        obj.setAuditUserName(obj.getUpdateUserName());
        this.updateById(obj);
        saveAuditRecord(obj);
        return obj;
    }

    @Override
    public ${entity} find${entity}ById(Integer id) {
        ${entity} obj  =this.getById(id);
        obj.parse();
        return obj;
    }

    @Override
    public ${entity} delete${entity}ById(Integer id) {
        ${entity} obj = this.getById(id);
        iAuditCommonRecordsService.removeByFlowId(obj.getFlowId());//删审核日志
        this.removeById(id);
        return obj;
    }

    @Override
    public IPage<${entity}> query(${entity}SearchBean sb) {
        LambdaQueryWrapper<${entity}> qb = sb.lambdaQueryWrapper();
        Page<${entity}> page = new Page<>(sb.getPageIndex(), sb.getPageSize());
        IPage<${entity}> ps = this.page(page, qb);
        ParseHandle.parsePage(ps);
        return ps;
    }

    /**
     * 日志记录
     * @param v
     */
    public void saveAuditRecord(${entity} v) {
        AuditCommonRecords acr = new AuditCommonRecords();
        acr.setAuditId(v.getFlowId());//流程ID
        acr.setStatus(v.getStatus());
        String auditName = ${entity}Status.getOptionType(v.getStatus());
        acr.setStatusName(auditName);
        acr.setAuditLink(auditName);
        acr.setOperatorstatus(auditName);
        acr.setUpdateTime(LocalDateTime.now());
        acr.setOperatorTime(LocalDateTime.now());
        acr.setOperatingOpinion(v.getAuditOpinion());
        switch (v.getStatus()) {
            case "01"://草稿不记录
                return;
            case "02"://提交
                acr.setOperator(v.getSubmitUserName());
                break;
            case "03"://审核
                acr.setOperator(v.getAuditUserName());
                acr.setOperatingOpinion(v.getAuditOpinion());
                break;
            case "04"://退回
                acr.setOperator(v.getAuditUserName());
                acr.setOperatingOpinion(v.getAuditOpinion());
                break;
            default:
                break;
        }

        iAuditCommonRecordsService.saveOrUpdate(acr);
    }
}
</#if>
