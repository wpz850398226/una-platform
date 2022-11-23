package cn.kunli.una.controller.sys;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysCompany;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.sys.SysCompanyService;
import cn.kunli.una.utils.common.DateUtil;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * (SysCompany)表控制层
 *
 * @author Ponzio
 * @since 2020-06-03 15:58:32
 */
@Controller
@Api(tags = "系统-公司/组织")
@RequestMapping("/sys/company")
public class SysCompanyController extends BaseController<SysCompanyService, SysCompany> {

    //刷新
    @PutMapping("/refresh/{id}")
    @ResponseBody
    public SysResult refresh(@PathVariable Integer id) {
        return service.updateRecordById((SysCompany) new SysCompany().setRefreshTime(new Date()).setId(id));
    }

    //置顶
    @PutMapping("/stick/{id}")
    @ResponseBody
    public SysResult stick(@PathVariable Integer id) {
        SysCompany SysCompany = service.getById(id);
        Date stickDeadline;
        if(SysCompany.getStickDeadline()==null|| DateUtil.compareDate(new Date(),SysCompany.getStickDeadline())){
            stickDeadline = DateUtil.getNextDay(new Date(),1);
        }else{
            stickDeadline = DateUtil.getNextDay(SysCompany.getStickDeadline(),1);
        }
        return service.updateRecordById((SysCompany) new SysCompany().setStickDeadline(stickDeadline).setId(id));
    }
}
