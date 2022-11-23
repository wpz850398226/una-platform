package cn.kunli.una.controller.sys;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysAccount;
import cn.kunli.una.pojo.sys.SysMessage;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.sys.SysAccountService;
import cn.kunli.una.service.sys.SysMessageService;
import cn.kunli.una.utils.common.UnaMapUtil;
import cn.kunli.una.utils.redis.RedisUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * (SysMessage)表控制层
 *
 * @author Ponzio
 * @since 2020-06-05 11:38:30
 */
@Controller
@Api(tags = "系统-消息")
@RequestMapping("/sys/message")
public class SysMessageController extends BaseController<SysMessageService, SysMessage> {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SysAccountService sysAccountService;

    //发送短信验证码
    @PostMapping("/sms")
    @ResponseBody
    public SysResult sms (String mobile) {
        if(StrUtil.isBlank(mobile)){
            return SysResult.fail("发送失败，手机号为空");
        }

        if(redisUtil.hasKey("captcha:"+mobile)){
            return SysResult.fail("发送失败，5分钟内不可重复发送，您上次的验证码是"+redisUtil.get("captcha:"+mobile));
        }

        SysAccount sysAccount = sysAccountService.selectOne(UnaMapUtil.getMap("username", mobile));
        if(sysAccount!=null){
            int captcha = (int)(Math.random()*1000000);
            String captchaString = String.format("%06d", captcha);
            boolean save = service.save((SysMessage) new SysMessage().setMobile(mobile).setCaptcha(captchaString).setTypeDcode("message_type_captcha").setName("captcha"+new Date().getTime()));
            if(save){
                //保存缓存
                redisUtil.set("captcha:"+mobile,captchaString,300);
                return SysResult.success("发送成功，您的短信验证码是："+captchaString);
            }
        }else{
            return SysResult.fail("手机号号不存在");
        }
        return SysResult.fail();
    }


    //验证短信验证码
    @PostMapping("/verify")
    @ResponseBody
    public SysResult verify (String mobile,String captcha) {

        if(redisUtil.hasKey("captcha:"+mobile)){
            if(redisUtil.get("captcha:"+mobile).equals(captcha)){
                return SysResult.success("验证通过");
            }else{
                return SysResult.fail("验证码错误");
            }
        }else{
            return SysResult.fail("验证码不存在");
        }
    }

}
