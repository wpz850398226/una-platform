package cn.kunli.una.controller.sys;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysRelation;
import cn.kunli.una.service.sys.SysRelationService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * (SysRelation)表控制层
 *
 * @author Ponzio
 * @since 2020-05-08 14:37:22
 */
@Controller
@Api(tags = "系统-关系")
@RequestMapping("/sys/relation")
public class SysRelationController extends BaseController<SysRelationService, SysRelation> {

}
