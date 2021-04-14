package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysVersion;
import cn.kunli.una.pojo.vo.Constant;
import cn.kunli.una.pojo.vo.SysParamMap;
import cn.kunli.una.service.system.SysVersionService;
import cn.kunli.una.utils.common.FileUtils;
import cn.kunli.una.utils.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @Autowired
    private SysVersionService objService;

    /**
     * 安卓端下载最新版本
     *
     * @return
     */
    @RequestMapping("/downloadNewVersion")
    @ResponseBody
    public String downloadNewVersion(HttpServletResponse response, @RequestParam Map<String, Object> params) {
        try {
            List<SysVersion> sysVersions = objService.selectBySelective(new SysParamMap(params));
            if (sysVersions != null && sysVersions.size() > 0) {
                SysVersion sysVersion = sysVersions.get(0);
                if (!StringUtil.isBlank(sysVersion.getFileUrl()) && !StringUtil.isBlank(sysVersion.getInternalVersion())) {
                    String fileName = sysVersion.getInternalVersion();
                    String filePath = Constant.UPLOAD_FILE_PATH + sysVersion.getFileUrl().substring(6);
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
