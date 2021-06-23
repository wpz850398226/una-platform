package cn.kunli.una.service.system;

import cn.kunli.una.pojo.chanpin.CpMenu;
import cn.kunli.una.mapper.CpMenuMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 菜单(CpMenu)表服务类
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:02
 */
@Service
public class CpMenuService extends BasicService<CpMenuMapper, CpMenu> {
    @Autowired
    private CpMenuService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
