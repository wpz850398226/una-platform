package cn.kunli.una.controller.sys;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysSort;
import cn.kunli.una.service.sys.SysSortService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ponzio
 * @version 2020年5月4日19:00:16
 * 排序管理
 */
@Controller
@RequestMapping("/sys/sort")
public class SysSortController extends BaseController<SysSortService, SysSort> {

}
