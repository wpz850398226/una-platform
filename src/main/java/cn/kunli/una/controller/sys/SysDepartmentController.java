package cn.kunli.una.controller.sys;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysDepartment;
import cn.kunli.una.service.sys.SysDepartmentService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * (SysDepartment)表控制层
 *
 * @author Ponzio
 * @since 2020-06-05 11:36:05
 */
@Controller
@Api(tags = "系统-部门")
@RequestMapping("/sys/department")
public class SysDepartmentController extends BaseController<SysDepartmentService, SysDepartment> {
}
