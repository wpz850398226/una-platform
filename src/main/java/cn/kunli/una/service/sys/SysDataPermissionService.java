package cn.kunli.una.service.sys;

import cn.kunli.una.pojo.sys.SysDataPermission;
import cn.kunli.una.mapper.SysDataPermissionMapper;
import org.springframework.stereotype.Service;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * <p>
 * 数据权限 服务类
 * </p>
 *
 * @author wangpz
 * @since 2022-09-08
 */
@Service
public class SysDataPermissionService extends BasicService<SysDataPermissionMapper, SysDataPermission> {

    @Autowired
    private SysDataPermissionService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
