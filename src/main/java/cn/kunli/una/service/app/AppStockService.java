package cn.kunli.una.service.app;

import cn.kunli.una.pojo.app.AppStock;
import cn.kunli.una.mapper.AppStockMapper;
import org.springframework.stereotype.Service;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * <p>
 * 股票 服务类
 * </p>
 *
 * @author wangpz
 * @since 2022-04-21
 */
@Service
public class AppStockService extends BasicService<AppStockMapper, AppStock> {

    @Autowired
    private AppStockService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
