package cn.kunli.una.controller.tim;

import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.utils.common.UserUtil;
import cn.kunli.una.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tim")
public class TimController {
    @Autowired
    private RedisUtil<Integer> redisUtil;
    @Value("${tim.appid}")
    private String appId;
    @Value("${tim.key}")
    private String key;


    /**
     * 打开主页
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String open(Model model,Integer regionId) {

        return "tim/index";
    }

    /**
     * 打开主页
     * @param model
     * @return
     */
    @RequestMapping("/userSig")
    @ResponseBody
    public SysResult userSig() {
        SysLoginAccountDetails loginAccount = UserUtil.getLoginAccount();
        TLSSigAPIv2 tlsSigAPIv2 = new TLSSigAPIv2(Long.valueOf(appId), key);
        String s = tlsSigAPIv2.genUserSig(String.valueOf(loginAccount.getId()   ), 86400);
        return new SysResult().success("获取成功",s);
    }

}
