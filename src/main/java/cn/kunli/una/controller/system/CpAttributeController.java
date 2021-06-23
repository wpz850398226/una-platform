package cn.kunli.una.controller.system;

import cn.kunli.una.pojo.chanpin.CpAttribute;
import cn.kunli.una.service.system.CpAttributeService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商城-规格属性表(CpAttribute)表控制层
 *
 * @author Ponzio
 * @since 2021-06-23 23:33:41
 */
@Controller
@RequestMapping("/sys/cpAttribute")
public class CpAttributeController extends BaseController<CpAttributeService, CpAttribute> {
}
