package cn.kunli.una.controller.geneticAlgorithm;


import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.geneticAlgorithm.GaEvolver;
import cn.kunli.una.service.geneticAlgorithm.GaEvolverService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 遗传算法-进化者/最优解 前端控制器
 * </p>
 *
 * @author Ponzio
 * @since 2022-01-14
 */
@Controller
@RequestMapping("/ga/evolver")
public class GaEvolverController extends BaseController<GaEvolverService, GaEvolver> {

}
