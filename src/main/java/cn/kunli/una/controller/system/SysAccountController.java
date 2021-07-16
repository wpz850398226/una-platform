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
        return service.saveRecord(obj);
    }

    //审核
    @PutMapping("/audit/{id}/{isAudit}")
    @ResponseBody
    public SysResult audit(@PathVariable Integer id,@PathVariable Integer isAudit) {
        if(isAudit==1){//审核通过
            SysAccount sysAccount = service.getById(id);
            SysCompany sysCompany = (SysCompany)new SysCompany().setIndustryDcode(sysAccount.getIndustryTypeDcode()).setName(sysAccount.getCompanyName());
            CpShop cpShop = (CpShop) new CpShop().setName(sysAccount.getName() + "的店铺");
            //创建企业
            sysCompanyService.saveRecord(sysCompany);
            //创建店铺
            cpShopService.saveRecord(cpShop);
            //修改账号信息
            service.updateRecordById((SysAccount) new SysAccount().setRoleId("100002")  //免费企业会员
                    .setStatusDcode("account_status_normal")
                    .setShopId(cpShop.getId())
                    .setCompanyId(sysCompany.getCompanyId())
                    .setId(id));
        }


        return SysResult.fail();

    }

}
