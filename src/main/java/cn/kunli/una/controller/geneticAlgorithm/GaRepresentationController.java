package cn.kunli.una.controller.geneticAlgorithm;


import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.geneticAlgorithm.GaRepresentation;
import cn.kunli.una.service.geneticAlgorithm.GaRepresentationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 遗传算法-表示/染色体 前端控制器
 * </p>
 *
 * @author Ponzio
 * @since 2022-01-14
 */
@Controller
@RequestMapping("/ga/representation")
public class GaRepresentationController extends BaseController<GaRepresentationService, GaRepresentation> {

}
