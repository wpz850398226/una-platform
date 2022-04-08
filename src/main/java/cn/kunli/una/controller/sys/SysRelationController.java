package cn.kunli.una.controller.sys;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysRelation;
import cn.kunli.una.service.sys.SysRelationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * (SysRelation)表控制层
 *
 * @author Ponzio
 * @since 2020-05-08 14:37:22
 */
@Controller
@RequestMapping("/sys/relation")
public class SysRelationController extends BaseController<SysRelationService, SysRelation> {

}
