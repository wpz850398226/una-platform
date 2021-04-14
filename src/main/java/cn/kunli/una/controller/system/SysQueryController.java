package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysQuery;
import cn.kunli.una.service.system.SysQueryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * (SysParamMap)表控制层
 *
 * @author Ponzio
 * @since 2020-05-08 16:19:03
 */
@Controller
@RequestMapping("/sys/query")
public class SysQueryController extends BaseController<SysQueryService, SysQuery> {

}
