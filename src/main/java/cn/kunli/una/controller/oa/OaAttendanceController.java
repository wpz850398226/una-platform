package cn.kunli.una.controller.oa;

import cn.kunli.una.pojo.oa.OaAttendance;
import cn.kunli.una.service.oa.OaAttendanceService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 办公-考勤(OaAttendance)表控制层
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:20
 */
@Controller
@RequestMapping("/oa/attendance")
public class OaAttendanceController extends BaseController<OaAttendanceService, OaAttendance> {
}
