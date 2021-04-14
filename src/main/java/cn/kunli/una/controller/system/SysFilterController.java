package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysFilter;
import cn.kunli.una.service.system.SysFilterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * (SysFilter)表控制层
 *
 * @author Ponzio
 * @since 2020-05-08 16:15:04
 */
@Controller
@RequestMapping("/sys/filter")
public class SysFilterController extends BaseController<SysFilterService, SysFilter> {

}
