package cn.kunli.una.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class EquipmentTask {


    private Logger logger = LoggerFactory.getLogger(EquipmentTask.class);


    @Scheduled(cron = "0 0 0 * * ?")//凌晨12点
    private void EquipmentSendTask(){
        logger.info("开始发送过期提醒");

    }
}
