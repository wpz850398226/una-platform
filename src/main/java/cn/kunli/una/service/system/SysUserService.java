package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysUserMapper;
import cn.kunli.una.pojo.system.SysUser;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户(SysUser)表服务类
 *
 * @author Ponzio
 * @since 2021-03-14 21:01:36
 */
@Service
public class SysUserService extends BaseService<SysUserMapper, SysUser> {

    //校验格式
    public SysResult validation(SysUser obj) {
        if (obj.getId() == null || obj.getId().equals("")) {
            //如果是新增
            if (StringUtils.isBlank(obj.getMobile())) {
                return SysResult.fail("手机号码不能为空，保存失败，请刷新页面重试");
            }
			/*if(StringUtils.isBlank(obj.getUsername())){
				return SysResult.fail("账号不能为空，保存失败，请刷新页面重试",obj.getMobile());
			}*/
        }
        if (StringUtils.isNotBlank(obj.getMobile())) {
            List<SysUser> objList = this.select((SysUser) new SysUser().setMobile(obj.getMobile().trim()).setIsDelete(0));
            if (objList.size() > 0 && !objList.get(0).getId().equals(obj.getId())) {
                //通过新文件的名称查询到数据
                return SysResult.fail("手机号码重复，保存失败:" + obj.getMobile());
            }
        }

        if (StringUtils.isNotBlank(obj.getIdNumber())) {
            List<SysUser> objList = this.select((SysUser) new SysUser().setIdNumber(obj.getIdNumber().trim()).setIsDelete(0));
            if (objList.size() > 0 && !objList.get(0).getId().equals(obj.getId())) {
                //通过新文件的名称查询到数据
                return SysResult.fail("证件号重复，保存失败:" + obj.getIdNumber());
            }
        }


        if (StringUtils.isNotBlank(obj.getEmail())) {
            List<SysUser> objList = this.select((SysUser) new SysUser().setEmail(obj.getEmail().trim()).setIsDelete(0));
            if (objList.size() > 0 && !objList.get(0).getId().equals(obj.getId())) {
                //通过新文件的名称查询到数据
                return SysResult.fail("邮箱重复，保存失败:" + obj.getEmail());
            }
        }

        //如果通过全部格式验证，则设置code=0，表示通过验证；
        return SysResult.success();
    }
}
