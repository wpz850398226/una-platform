package cn.kunli.una.service.sys;

import cn.kunli.una.pojo.sys.SysForm;
import cn.kunli.una.mapper.SysFormMapper;
import org.springframework.stereotype.Service;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangpz
 * @since 2023-01-13
 */
@Service
public class SysFormService extends BasicService<SysFormMapper, SysForm> {

    @Autowired
    private SysFormService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
