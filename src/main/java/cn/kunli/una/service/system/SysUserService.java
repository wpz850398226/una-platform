package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysUserMapper;
import cn.kunli.una.pojo.system.SysUser;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.UnaMapUtil;
import org.apache.commons.collections4.CollectionUtils;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SysUserService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    //校验格式
    public SysResult validate(SysUser obj) {
        if (StrUtil.isNotBlank(obj.getMobile())) {
            List<SysUser> objList = thisProxy.getList(UnaMapUtil.getMap("mobile",obj.getMobile()));
            if (CollectionUtils.isNotEmpty(objList) && !objList.get(0).getId().equals(obj.getId())) {
                //通过新文件的名称查询到数据
                return SysResult.fail("手机号码重复，保存失败:" + obj.getMobile());
            }
        }

        if (StrUtil.isNotBlank(obj.getIdNumber())) {
            List<SysUser> objList = thisProxy.getList(UnaMapUtil.getMap("idNumber",obj.getIdNumber()));
            if (CollectionUtils.isNotEmpty(objList) && !objList.get(0).getId().equals(obj.getId())) {
                //通过新文件的名称查询到数据
                return SysResult.fail("证件号重复，保存失败:" + obj.getIdNumber());
            }
        }


        if (StrUtil.isNotBlank(obj.getEmail())) {
            List<SysUser> objList = thisProxy.getList(UnaMapUtil.getMap("email",obj.getEmail()));
            if (CollectionUtils.isNotEmpty(objList) && !objList.get(0).getId().equals(obj.getId())) {
                //通过新文件的名称查询到数据
                return SysResult.fail("邮箱重复，保存失败:" + obj.getEmail());
            }
        }

        //如果通过全部格式验证，则设置code=0，表示通过验证；
        return SysResult.success();
    }
}
