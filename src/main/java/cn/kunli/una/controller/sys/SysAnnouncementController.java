package cn.kunli.una.controller.sys;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysAnnouncement;
import cn.kunli.una.service.sys.SysAnnouncementService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * (SysAnnouncement)表控制层
 *
 * @author Ponzio
 * @since 2020-05-06 14:37:02
 */
@Controller
@Api(tags = "系统-公告")
@RequestMapping("/sys/announcement")
public class SysAnnouncementController extends BaseController<SysAnnouncementService, SysAnnouncement> {
}
