package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysRegion;
import cn.kunli.una.service.system.SysRegionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * (SysRegion)表控制层
 *
 * @author Ponzio
 * @since 2020-06-29 15:18:55
 */
@Controller
@RequestMapping("/sys/region")
public class SysRegionController extends BaseController<SysRegionService, SysRegion> {

}
