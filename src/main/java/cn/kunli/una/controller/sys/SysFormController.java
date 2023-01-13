package cn.kunli.una.controller.sys;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysForm;
import cn.kunli.una.service.sys.SysFormService;
import io.swagger.annotations.Api;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author wangpz
 * @since 2023-01-13
 */
@RestController
@Api(value="前端控制器")
@RequestMapping("/sys/form")
public class SysFormController extends BaseController<SysFormService, SysForm> {
}
