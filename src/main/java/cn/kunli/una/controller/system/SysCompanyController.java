package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysCompany;
import cn.kunli.una.service.system.SysCompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * (SysCompany)表控制层
 *
 * @author Ponzio
 * @since 2020-06-03 15:58:32
 */
@Controller
@RequestMapping("/sys/company")
public class SysCompanyController extends BaseController<SysCompanyService, SysCompany> {
}
