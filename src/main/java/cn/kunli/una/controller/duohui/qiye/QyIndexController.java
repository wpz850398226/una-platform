package cn.kunli.una.controller.duohui.qiye;

import cn.kunli.una.pojo.system.SysCompany;
import cn.kunli.una.service.system.SysCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/duohui/qiye")
public class QyIndexController {
    @Autowired
    private SysCompanyService sysCompanyService;

    /**
     * 打开主体框架
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {

        List<SysCompany> companyList = sysCompanyService.selectList(null);
        model.addAttribute("companyList",companyList);

        return "duohui/qiye/index";
    }
}
