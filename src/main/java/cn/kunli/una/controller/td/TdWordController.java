package cn.kunli.una.controller.td;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.td.TdWord;
import cn.kunli.una.service.td.TdWordService;
import io.swagger.annotations.Api;

/**
 * <p>
 * 单词 控制器
 * </p>
 *
 * @author wangpz
 * @since 2022-04-20
 */
@RestController
@Api(value="单词前端控制器")
@RequestMapping("/td/word")
public class TdWordController extends BaseController<TdWordService, TdWord> {
}
