package cn.kunli.una.controller.ali;

import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.ali.AlipayService;
import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/*支付宝功能控制*/
@Controller
@Api(tags = "阿里-支付宝")
@RequestMapping("/alipay")
public class AlipayController {

    /*@Autowired
    private AlipayClient alipayClient;
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    @Value("${alipay.notifyUrl}")
    private String notify_url;
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    @Value("${alipay.returnUrl}")
    private String return_url;*/
    @Autowired
    private AlipayService alipayService;

    /**
     * 打开支付页面
     * @return
     */
    @RequestMapping("/toPay")
    @SneakyThrows
    public SysResult toPay(Map<String,String> paramMap) {
        return alipayService.toPay(paramMap);
    }
}
