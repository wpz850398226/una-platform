package cn.kunli.una.controller.sys;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysRegion;
import cn.kunli.una.service.sys.SysRegionService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * (SysRegion)表控制层
 *
 * @author Ponzio
 * @since 2020-06-29 15:18:55
 */
@Controller
@Api(tags = "系统-地区")
@RequestMapping("/sys/region")
public class SysRegionController extends BaseController<SysRegionService, SysRegion> {

}
