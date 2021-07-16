package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysAccountMapper;
import cn.kunli.una.pojo.system.SysAccount;
import cn.kunli.una.pojo.system.SysRole;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.ListUtil;
import cn.kunli.una.utils.common.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private SysRoleService sysRoleService;
    @Override
    public BasicService getThisProxy() {
        return sysAccountService;
    }


    //校验格式
    public SysResult validate(SysAccount obj) {
        if (StringUtils.isNotBlank(obj.getUsername())) {
            List<SysAccount> objList = getThisProxy().selectList(MapUtil.getMap("username", obj.getUsername().trim()));
            if (objList.size() > 0 && !objList.get(0).getId().equals(obj.getId())) {
                return SysResult.fail("账号重复，保存失败:" + obj.getUsername());
            }
        }
        if (StringUtils.isNotBlank(obj.getCompanyName())) {
            List<SysAccount> objList = getThisProxy().selectList(MapUtil.getMap("companyName", obj.getCompanyName().trim()));
            if (objList.size() > 0 && !objList.get(0).getId().equals(obj.getId())) {
                return SysResult.fail("公司名称重复，保存失败:" + obj.getCompanyName());
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
            //默认密码123456
            if (StringUtils.isBlank(obj.getPassword())) {
                obj.setPassword(new BCryptPasswordEncoder().encode("123456"));
            }
            //如果账号来源是自行注册，则状态为呆提交
            if(StringUtils.isNotBlank(obj.getOriginDcode())&&obj.getOriginDcode().equals("account_origin_register")){
                obj.setStatusDcode("account_status_toSubmit");
            }

        }

        //格式化账号，姓名（去空格）
        if (StringUtils.isNotBlank(obj.getUsername())) obj.setUsername(obj.getUsername().replace(" ", ""));
        if (StringUtils.isNotBlank(obj.getName())) obj.setName(obj.getName().replace(" ", ""));
        if (StringUtils.isNotBlank(obj.getPassword())) obj.setPassword(new BCryptPasswordEncoder().encode(obj.getPassword()));
        if(StringUtils.isBlank(obj.getStatusDcode()))obj.setStatusDcode("account_status_normal");
        if(StringUtils.isNotBlank(obj.getCompanyName())&&StringUtils.isBlank(obj.getName()))obj.setName(obj.getCompanyName());

        return obj;
    }

}
