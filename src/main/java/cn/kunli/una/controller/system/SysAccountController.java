package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.chanpin.CpShop;
import cn.kunli.una.pojo.system.SysAccount;
import cn.kunli.una.pojo.system.SysCompany;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.duohui.chanpin.CpShopService;
import cn.kunli.una.service.system.SysAccountService;
import cn.kunli.una.service.system.SysCompanyService;
import cn.kunli.una.utils.common.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * (SysAccount)表控制层
 *
 * @author Ponzio
 * @since 2020-05-08 09:16:54
 */
@Slf4j
@Controller
@RequestMapping("/sys/account")
public class SysAccountController extends BaseController<SysAccountService, SysAccount> {

    @Autowired
    private SysCompanyService sysCompanyService;
    @Autowired
    private CpShopService cpShopService;

    //token获取用户信息
    @GetMapping("/getInfo")
    @ResponseBody
    public SysResult getInfo() {
        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
        return new SysResult().success(loginUser);
    }

    //注册
    @PostMapping("/register")
    @ResponseBody
    public SysResult register(SysAccount obj) {
        obj.setOriginDcode("account_origin_register");
        SysResult sysResult = service.saveRecord(obj);
        if(sysResult.getIsSuccess()){
            service.updateRecordById((SysAccount) new SysAccount().setModifierId(obj.getId()).setModifierName(obj.getName())
                    .setCreatorId(obj.getId()).setCreatorName(obj.getName()).setId(obj.getId()));
        }
        return sysResult;
    }

    //审核
    @PutMapping("/audit/{id}/{isAudit}")
    @ResponseBody
    public SysResult audit(@PathVariable Integer id,@PathVariable Integer isAudit) {
        SysAccount targetAccount = (SysAccount) new SysAccount().setId(id);
        if(isAudit==1){//审核通过
            SysAccount sysAccount = service.getById(id);
            CpShop cpShop = (CpShop) new CpShop().setName(sysAccount.getName() + "的店铺");
            //创建店铺
            cpShopService.saveRecord(cpShop);
            targetAccount.setRoleId("100003").setStatusDcode("account_status_auditSuccess").setShopId(cpShop.getId());//免费会员

            //创建企业
            if(sysAccount.getTypeDcode().equals("account_type_company")){
                SysCompany sysCompany = (SysCompany)new SysCompany().setIndustryDcode(sysAccount.getIndustryTypeDcode()).setName(sysAccount.getName());
                sysCompanyService.saveRecord(sysCompany);
                targetAccount.setCompanyId(sysCompany.getId());
            }
        }else{
            targetAccount.setStatusDcode("account_status_auditFail");
        }
        //修改账号信息
        return service.updateRecordById(targetAccount);

    }

}
