package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import com.baomidou.mybatisplus.core.metadata.IPage;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import ${cfg.searchbeanPage}.${entity}SearchBean;
import com.glodon.pcop.supervisesvc.common.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
@Api(tags = "${table.comment!}")
@Slf4j
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>


    @Autowired
    I${entity}Service i${entity}Service;

    @PostMapping("/search")
    @ApiOperation(value = "查列表", notes = "")
    public WebResult<IPage<${entity}>> search(@RequestBody ${entity}SearchBean sb){

        IPage<${entity}>  rp = i${entity}Service.query(sb);
        return new WebResult(rp);
    }

    @PostMapping("")
    @ApiOperation(value = "填报", notes = "optionType 1 草稿 ，2 提交")
    @Transactional
    public WebResult<${entity}> saveById(@RequestBody ${entity} obj ,@RequestParam Integer optionType) {//1 暂存 2 提交

        if(obj.invaild()){
            return this.bindErr(obj.getErrorMsg());
//            throw new UniformException(CodeMsg.SERVER_ERROR.getCode(),err2);
        }

        obj = i${entity}Service.save${entity}(obj,optionType);
        return success(obj);
    }

    @PutMapping("")
    @ApiOperation(value = "编辑", notes = "optionType 1 草稿 ，2 提交")
    @Transactional
    public WebResult<${entity}> updateById(@RequestBody ${entity} obj,@RequestParam Integer optionType) {
        if(obj.invaild()){
            return this.bindErr(obj.getErrorMsg());
        }
        return success(i${entity}Service.update${entity}(obj,optionType));
    }

    @PutMapping("/audit")
    @ApiOperation(value = "审核", notes = "optionType  1 退回  2 同意")
    @Transactional
    public WebResult<${entity}> audit${entity}(@RequestBody ${entity} obj,@RequestParam Integer optionType) {
        return success(i${entity}Service.audit${entity}(obj,optionType));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询，根据id", notes = "")
    public WebResult<${entity}> findById(@PathVariable Integer id) {
        ${entity} obj = i${entity}Service.find${entity}ById(id);
        return success(obj);
    }


    @DeleteMapping("/{id}")
    public WebResult<${entity}> deleteById(@PathVariable Integer id){
        return success(i${entity}Service.delete${entity}ById(id));
    }
}
</#if>
