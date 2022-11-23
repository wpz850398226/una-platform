package cn.kunli.una.controller.oa;

import cn.kunli.una.pojo.oa.OaSalary;
import cn.kunli.una.service.oa.OaSalaryService;
import cn.kunli.una.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 办公-薪资(OaSalary)表控制层
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:24
 */
@Controller
@Api(tags = "办公-薪资")
@RequestMapping("/oa/salary")
public class OaSalaryController extends BaseController<OaSalaryService, OaSalary> {
}
