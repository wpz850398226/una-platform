package cn.kunli.una.controller.sys;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysFilter;
import cn.kunli.una.service.sys.SysFilterService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * (SysFilter)表控制层
 *
 * @author Ponzio
 * @since 2020-05-08 16:15:04
 */
@Controller
@Api(tags = "系统-过滤器")
@RequestMapping("/sys/filter")
public class SysFilterController extends BaseController<SysFilterService, SysFilter> {

}
