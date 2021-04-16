package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysUserMapper;
import cn.kunli.una.pojo.system.SysUser;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.MapUtil;
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
public class SysUserService extends BasicService<SysUserMapper, SysUser> {

    //校验格式
    public SysResult validation(SysUser obj) {
        if (StringUtils.isNotBlank(obj.getMobile())) {
            List<SysUser> objList = this.selectList(MapUtil.getMap("mobile",obj.getMobile()));
            if (objList.size() > 0 && !objList.get(0).getId().equals(obj.getId())) {
                //通过新文件的名称查询到数据
                return SysResult.fail("手机号码重复，保存失败:" + obj.getMobile());
            }
        }

        if (StringUtils.isNotBlank(obj.getIdNumber())) {
            List<SysUser> objList = this.selectList(MapUtil.getMap("idNumber",obj.getIdNumber()));
            if (objList.size() > 0 && !objList.get(0).getId().equals(obj.getId())) {
                //通过新文件的名称查询到数据
                return SysResult.fail("证件号重复，保存失败:" + obj.getIdNumber());
            }
        }


        if (StringUtils.isNotBlank(obj.getEmail())) {
            List<SysUser> objList = this.selectList(MapUtil.getMap("email",obj.getEmail()));
            if (objList.size() > 0 && !objList.get(0).getId().equals(obj.getId())) {
                //通过新文件的名称查询到数据
                return SysResult.fail("邮箱重复，保存失败:" + obj.getEmail());
            }
        }

        //如果通过全部格式验证，则设置code=0，表示通过验证；
        return SysResult.success();
    }
}
