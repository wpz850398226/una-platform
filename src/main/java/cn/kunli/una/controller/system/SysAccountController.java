package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;

import cn.kunli.una.pojo.system.SysAccount;
import cn.kunli.una.pojo.system.SysCompany;
import cn.kunli.una.pojo.system.SysDictionary;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysResult;

import cn.kunli.una.service.system.SysAccountService;
import cn.kunli.una.service.system.SysCompanyService;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.UserUtil;
import lombok.extern.slf4j.Slf4j;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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



    //token获取用户信息
    @GetMapping("/getInfo")
    @ResponseBody
    public SysResult getInfo() {
        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
        if(StrUtil.isNotBlank(loginUser.getStatusDcode())){
            SysDictionary sysDictionary = sysDictionaryService.selectOne(MapUtil.getMap("code", loginUser.getStatusDcode()));
            if(sysDictionary!=null){
                loginUser.setStatusDname(sysDictionary.getName());
            }
        }
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

    //注册
    @PostMapping("/setPassword")
    @ResponseBody
    public SysResult setPassword(Integer id,String password) {

        if(id==null||password==null)return SysResult.fail();

        SysResult sysResult = service.updateRecordById((SysAccount) new SysAccount().setPassword(password).setId(id));

        return sysResult;
    }


}
