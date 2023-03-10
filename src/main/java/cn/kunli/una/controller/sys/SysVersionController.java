package cn.kunli.una.controller.sys;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysVersion;
import cn.kunli.una.pojo.vo.Constant;
import cn.kunli.una.service.sys.SysVersionService;
import cn.kunli.una.utils.common.FileUtils;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Controller;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * (SysVersion)表控制层
 *
 * @author Ponzio
 * @since 2020-07-08 11:41:50
 */
@Controller
@RequestMapping("/sys/version")
public class SysVersionController extends BaseController<SysVersionService, SysVersion> {

    /**
     * 安卓端下载最新版本
     *
     * @return
     */
    @RequestMapping("/downloadNewVersion")
    @ResponseBody
    public String downloadNewVersion(HttpServletResponse response, @RequestParam Map<String, Object> params) {
        try {
            List<SysVersion> list = service.selectList(params);
            if (CollectionUtils.isNotEmpty(list)) {
                SysVersion sysVersion = list.get(0);
                if (StrUtil.isNotBlank(sysVersion.getPath()) && StrUtil.isNotBlank(sysVersion.getInternalVersion())) {
                    String fileName = sysVersion.getInternalVersion();
                    String filePath = Constant.UPLOAD_FILE_PATH + sysVersion.getPath().substring(6);
                    FileUtils.downloadFile(response, fileName, filePath);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
}
