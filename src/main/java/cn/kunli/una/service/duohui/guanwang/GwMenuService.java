package cn.kunli.una.service.duohui.guanwang;

import cn.kunli.una.mapper.GwMenuMapper;
import cn.kunli.una.pojo.duohui.guanwang.GwMenu;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 菜单(GwMenu)表服务类
 *
 * @author Ponzio
 * @since 2021-03-26 11:09:52
 */
@Service
public class GwMenuService extends BasicService<GwMenuMapper, GwMenu> {
    @Autowired
    private GwMenuService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

}
