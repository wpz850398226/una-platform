package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysAccountMapper;
import cn.kunli.una.pojo.system.SysAccount;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

    //校验格式
    public SysResult validation(SysAccount obj) {
        if (obj.getMap() != null && obj.getMap().get("confirmPassword") != null) {
            if (obj.getPassword() != null && !obj.getPassword().equals("")) {
                if (!obj.getPassword().trim().equals(obj.getMap().get("confirmPassword").toString().trim())) {
                    return SysResult.fail("密码不一致，保存失败:" + obj.getPassword());
                }
            } else {
                return SysResult.fail("密码不能为空，保存失败");
            }
        }

        if (StringUtils.isNotBlank(obj.getUsername())) {
            List<SysAccount> objList = sysAccountService.list(wrapperUtil.mapToWrapper(MapUtil.getMap("username",obj.getUsername().trim())));
            if (objList.size() > 0 && !objList.get(0).getId().equals(obj.getId())) {
                //通过新文件的名称查询到数据
                return SysResult.fail("账号重复，保存失败:" + obj.getUsername());
            }
        }

        //如果通过全部格式验证，则设置code=0，表示通过验证；
        return SysResult.success();
    }

    //格式化实体类
    public SysAccount saveFormat(SysAccount obj) {
        if (obj.getId() == null) {
            //如果id为空，新增数据
            if (obj.getRoleIdArray() != null && obj.getRoleIdArray().length > 0) {
                obj.setRoleIds(StringUtils.join(obj.getRoleIdArray(), ","));
            }
            //新增保存
            if (StringUtils.isBlank(obj.getPassword())) {
                obj.setPassword(new BCryptPasswordEncoder().encode("123456"));
            }
            obj.setPageSize(10);
            //如果新增账户账号为空，自动填充手机号
            if (obj.getUsername() == null || obj.getUsername().equals(""))
                obj.setUsername(obj.getSysUser().getMobile());
        } else {
            //如果id不为空，修改数据
            if (obj.getRemark() != null && obj.getRemark().equals("提交认证")) {
                obj.setStatusDcode("未审核");
                obj.setRemark(null);
            }
        }

        //格式化账号，姓名（去空格）
        if (obj.getUsername() != null) obj.setUsername(obj.getUsername().replace(" ", ""));
        if (obj.getName() != null) obj.setName(obj.getName().replace(" ", ""));
        if (obj.getRoleIdArray() != null && obj.getRoleIdArray().length > 0)
            obj.setRoleIds(StringUtils.join(obj.getRoleIdArray(), ","));

        super.saveFormat(obj);
        return obj;
    }

}
