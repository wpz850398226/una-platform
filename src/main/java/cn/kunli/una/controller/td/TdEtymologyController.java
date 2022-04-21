package cn.kunli.una.controller.td;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.td.TdEtymology;
import cn.kunli.una.service.td.TdEtymologyService;
import io.swagger.annotations.Api;

/**
 * <p>
 * 词源 控制器
 * </p>
 *
 * @author wangpz
 * @since 2022-04-21
 */
@RestController
@Api(value="词源前端控制器")
@RequestMapping("/td/etymology")
public class TdEtymologyController extends BaseController<TdEtymologyService, TdEtymology> {
}
