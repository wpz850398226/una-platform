package cn.kunli.una.controller.sys;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysLog;
import cn.kunli.una.service.sys.SysLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 日志(SysLog)表控制层
 *
 * @author Ponzio
 * @since 2021-01-15 16:44:55
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController extends BaseController<SysLogService, SysLog> {

}
