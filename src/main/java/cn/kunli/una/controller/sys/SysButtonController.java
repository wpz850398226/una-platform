package cn.kunli.una.controller.sys;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysButton;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.sys.SysButtonService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * (SysButton)表控制层
 *
 * @author Ponzio
 * @since 2020-05-07 08:10:24
 */
@Controller
@RequestMapping("/sys/button")
public class SysButtonController extends BaseController<SysButtonService, SysButton> {

    @GetMapping("/test/{id}")
    @ResponseBody
    @ApiOperation(value = "按钮测试",notes = "")
    public SysResult test(@PathVariable Integer id) {
        SysButton byId = service.getById(id);
        return new SysResult().success("点击按钮了："+byId.getName());
    }

}
