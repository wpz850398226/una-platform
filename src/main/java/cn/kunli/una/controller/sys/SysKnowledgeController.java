package cn.kunli.una.controller.sys;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysKnowledge;
import cn.kunli.una.service.sys.SysKnowledgeService;
import io.swagger.annotations.ApiModel;

/**
 * <p>
 * 知识 控制器
 * </p>
 *
 * @author wangpz
 * @since 2022-04-08
 */
@RestController
@ApiModel(value="系统-知识", description="知识")
@RequestMapping("/sys/knowledge")
public class SysKnowledgeController extends BaseController<SysKnowledgeService, SysKnowledge> {
}
