package cn.kunli.una.controller.sys;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysAccount;
import cn.kunli.una.pojo.sys.SysMenu;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.sys.SysMenuService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Ponzio
 * @version 2019年6月5日 下午5:25:30
 * 菜单管理
 */
@Controller
@Api(tags = "系统-菜单")
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController<SysMenuService, SysMenu> {

    /**
     * 跳转管理页
     *
     * @param model
     * @return
     */
    @RequestMapping("menuTab")
    public String labelView(HttpSession session, Model model, SysMenu obj) {
        SysAccount activeUser = (SysAccount) session.getAttribute("activeUser");
		/*List<SysMenu> primaryMenuList = activeUser.getPrimaryMenuList();
		if(obj.getId()!=null&&!obj.getId().equals("")){
			for(SysMenu sysMenu:primaryMenuList){
				if(sysMenu.getId().equals(obj.getId())){
					obj = sysMenu;
				}
			}
		}*/

        model.addAttribute("record", obj);
        return "una/system/menuTab";
    }

    //token获取用户信息
	@GetMapping("/getByToken")
	@ResponseBody
	public SysResult getByToken() {
		List<SysMenu> list = service.selectByAccount();
		return new SysResult().success(list);
	}

}
