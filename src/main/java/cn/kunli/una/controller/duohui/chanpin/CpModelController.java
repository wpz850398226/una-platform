package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpModel;
import cn.kunli.una.service.duohui.chanpin.CpModelService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商城-商品规格表(CpModel)表控制层
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:01
 */
@Controller
@RequestMapping("/cp/model")
public class CpModelController extends BaseController<CpModelService, CpModel> {
}
