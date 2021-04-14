package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.BasePojo;
import cn.kunli.una.pojo.system.SysAccount;
import cn.kunli.una.pojo.system.SysMenu;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.system.SysMenuService;
import cn.kunli.una.utils.common.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController<SysMenuService, SysMenu> {

    @Autowired
    private SysMenuService objService;

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
		SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
		BasePojo sample = new SysMenu().setId(loginUser.getId());
		List<SysMenu> list = baseService.selectTreeBySelective((SysMenu) sample);
		return new SysResult().setData(list);
	}

}
