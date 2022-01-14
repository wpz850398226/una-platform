package cn.kunli.una.controller.geneticAlgorithm;


import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.geneticAlgorithm.GaEvaluation;
import cn.kunli.una.service.geneticAlgorithm.GaEvaluationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 遗传算法-适应度/评价函数 前端控制器
 * </p>
 *
 * @author Ponzio
 * @since 2022-01-14
 */
@Controller
@RequestMapping("/ga/evaluation")
public class GaEvaluationController extends BaseController<GaEvaluationService, GaEvaluation> {

}
