package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysAccountMapper;
import cn.kunli.una.pojo.chanpin.CpShop;
import cn.kunli.una.pojo.system.SysAccount;
import cn.kunli.una.pojo.system.SysCompany;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.service.duohui.chanpin.CpShopService;
import cn.kunli.una.utils.common.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (SysAccount)表服务实现类
 *
 * @author Ponzio
 * @since 2020-05-08 09:16:54
 */
@Slf4j
@Service
public class SysAccountService extends BasicService<SysAccountMapper, SysAccount> {
    @Override
    public BasicService getThisProxy() {
        return sysAccountService;
    }
    @Autowired
    private CpShopService cpShopService;
    @Autowired
    private SysCompanyService sysCompanyService;

    @Override
    public SysResult updateRecordById(SysAccount entity) {
        if(entity.getIsAudit()!=null&&entity.getIsAudit()){
            //审核通过

            //创建企业
            SysAccount sysAccount = sysAccountService.getById(entity.getId());
            if(sysAccount.getTypeDcode().equals("account_type_company")){

                SysCompany sysCompany = (SysCompany)new SysCompany().setThirdryIndustryDcode(sysAccount.getIndustryTypeDcode()).setName(entity.getName());
                SysResult companyResult = sysCompanyService.saveRecord(sysCompany);
                if(companyResult.getIsSuccess()){
                    entity.setCompanyId(sysCompany.getId());
                }else{
                    entity.setRemark(entity.getRemark()+"|"+companyResult.getMessage());
                }
            }
            CpShop cpShop = (CpShop) new CpShop().setName(entity.getName() + "的店铺").setCreatorId(entity.getId()).setCompanyId(entity.getCompanyId());
            //创建店铺
            SysResult shopResult = cpShopService.saveRecord(cpShop);
            if(shopResult.getIsSuccess()){
                entity.setShopId(cpShop.getId());
            }else{
                entity.setRemark(entity.getRemark()+"|"+shopResult.getMessage());
            }
        }
        return super.updateRecordById(entity);
    }

    //校验格式
    @Override
    public SysResult validate(SysAccount obj) {
        SysResult validate = super.validate(obj);
        if(!validate.getIsSuccess())return validate;
        if (StringUtils.isNotBlank(obj.getUsername())) {
            List<SysAccount> objList = getThisProxy().selectList(MapUtil.getMap("username", obj.getUsername().trim()));
            if (CollectionUtils.isNotEmpty(objList) && !objList.get(0).getId().equals(obj.getId())) {
                return SysResult.fail("账号重复，保存失败:" + obj.getUsername());
            }
        }

        //如果通过全部格式验证，则设置code=0，表示通过验证；
        return SysResult.success();
    }

    //格式化实体类
    public SysAccount initialize(SysAccount obj) {
        super.initialize(obj);
        if (obj.getId() == null) {
            //如果id为空，新增数据
            if(StringUtils.isBlank(obj.getStatusDcode()))obj.setStatusDcode("account_status_normal");
            //默认密码123456
            if (StringUtils.isBlank(obj.getPassword())) {
                obj.setPassword(new BCryptPasswordEncoder().encode("123456"));
            }
            //如果账号来源是自行注册，则状态为待提交
            if(StringUtils.isNotBlank(obj.getOriginDcode())&&obj.getOriginDcode().equals("account_origin_register")){
                obj.setStatusDcode("account_status_toSubmit").setRoleId("100002");//未认证会员角色
            }
        }else{
            if(obj.getIsAudit()!=null){
                if(obj.getIsAudit()){
                    obj.setRoleId("100003").setStatusDcode("account_status_auditSuccess");//免费会员
                }else{
                    obj.setStatusDcode("account_status_auditFail");
                }
            }
        }

        //格式化账号，姓名（去空格）
        if (StringUtils.isNotBlank(obj.getUsername())) obj.setUsername(obj.getUsername().replace(" ", ""));
        if (StringUtils.isNotBlank(obj.getName())) obj.setName(obj.getName().replace(" ", ""));
        if (StringUtils.isNotBlank(obj.getPassword())) obj.setPassword(new BCryptPasswordEncoder().encode(obj.getPassword()));

        return obj;
    }

}
