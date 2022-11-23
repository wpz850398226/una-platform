package cn.kunli.una.controller.sys;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysException;
import cn.kunli.una.service.sys.SysExceptionService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * (SysException)表控制层
 *
 * @author Ponzio
 * @since 2020-07-21 11:04:55
 */
@Controller
@Api(tags = "系统-异常记录")
@RequestMapping("/sys/exception")
public class SysExceptionController extends BaseController<SysExceptionService, SysException> {

}
