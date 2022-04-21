package cn.kunli.una.controller.app;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.app.AppStock;
import cn.kunli.una.service.app.AppStockService;
import io.swagger.annotations.Api;

/**
 * <p>
 * 股票 控制器
 * </p>
 *
 * @author wangpz
 * @since 2022-04-21
 */
@RestController
@Api(value="股票前端控制器")
@RequestMapping("/app/stock")
public class AppStockController extends BaseController<AppStockService, AppStock> {
}
