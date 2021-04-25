package cn.kunli.una.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class EquipmentTask {


    @Scheduled(cron = "0 0 0 * * ?")//凌晨12点
    private void EquipmentSendTask(){
        log.info("开始发送过期提醒");
    }
}
