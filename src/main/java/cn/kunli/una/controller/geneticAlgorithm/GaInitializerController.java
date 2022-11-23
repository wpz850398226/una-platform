package cn.kunli.una.controller.geneticAlgorithm;


import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.geneticAlgorithm.GaInitializer;
import cn.kunli.una.service.geneticAlgorithm.GaInitializerService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 遗传算法-初始群体/祖先 前端控制器
 * </p>
 *
 * @author Ponzio
 * @since 2022-01-14
 */
@Controller
@Api(tags = "遗传算法-初始群体/祖先")
@RequestMapping("/ga/initializer")
public class GaInitializerController extends BaseController<GaInitializerService, GaInitializer> {

}
