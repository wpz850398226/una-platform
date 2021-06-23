package cn.kunli.una.controller.system;

import cn.kunli.una.pojo.chanpin.CpSpecification;
import cn.kunli.una.service.system.CpSpecificationService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商城-规格表(CpSpecification)表控制层
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:04
 */
@Controller
@RequestMapping("/cpSpecification")
public class CpSpecificationController extends BaseController<CpSpecificationService, CpSpecification> {
}
