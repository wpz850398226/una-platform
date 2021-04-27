package cn.kunli.una.redis;

import cn.kunli.una.service.system.SysConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPubSub;

/*
* 公车申请超时监听
* */
@Component
public class UserVehicleDispatchExpiredListener extends JedisPubSub {

    @Autowired
    SysConfigurationService sysConfigurationService;

    //订阅发布监听
    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPSubscribeForUserVehicle " + pattern + " " + subscribedChannels);
    }

    //消息发布监听
    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println("onPMessageForUserVehicle pattern " + pattern + " " + channel + " " + message);

        //如果过期的redis键名内包含“userVehicle_”，则说明是公车申请过期
        if(message.indexOf("userVehicle_")!=-1){
            //获取公车申请记录id
            String applyId = message.replace("userVehicle_","");
            //调用通过公车申请记录id触发公车申请超时逻辑
            //objService.vehicleApplyOutTime(applyId);
        }
    }

}
