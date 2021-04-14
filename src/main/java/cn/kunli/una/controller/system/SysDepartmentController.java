package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysDepartment;
import cn.kunli.una.service.system.SysDepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * (SysDepartment)表控制层
 *
 * @author Ponzio
 * @since 2020-06-05 11:36:05
 */
@Controller
@RequestMapping("/sys/department")
public class SysDepartmentController extends BaseController<SysDepartmentService, SysDepartment> {
}
